package com.rv.framwork.manager

import com.rv.framwork.cmd.SerialPortManager.Companion.spm
import com.rv.framwork.cmd.base.BaseCommand
import com.rv.framwork.utils.AppLogUtil
import com.rv.framwork.utils.HexUtil
import com.rv.framwork.utils.StringUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream

class MCUUpGradeManager private constructor(
    private val productId: Int = 0,
    private val file : File? = null,
    private val onUpGradeListener:OnUpGradeListener?=null,
){

    interface OnUpGradeListener {
        fun onReady()
        fun onStart()
        fun onProgress(progress: Float)
        fun onFinish(filePath: String)
        fun onError(error: String)
    }

    class Builder {
        var productId: Int = 0
        var file : File? = null
        var onUpGradeListener:OnUpGradeListener?=null
        fun build() = MCUUpGradeManager(productId, file,onUpGradeListener)
    }

    override fun toString(): String {
        return "MCUUpGradeManager(productId=$productId, file=${file?.path})"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val head = 0x2E.toByte()
    private val common = byteArrayOf(0x41, 0x42, 0x43, 0x44, 0x12, 0x34, 0x56, 0x78)
    private var fileLength = -1L
    private var fileMaxProgress = 0f
    private val fileBuffer : MutableList<ByteArray> = ArrayList()

    private var isLoadFinish = false
    private var currentCrc = -1
    private var isBoteSend = false

    private var localBuffer: MutableList<Byte> = ArrayList()

    private fun verifyData(data: ByteArray,data2: ((ByteArray)->Unit)? = null){
        localBuffer.addAll(data.toList())
        AppLogUtil.e( "verifyData: ${HexUtil.byteArrayToHexStr(localBuffer.toByteArray())}")
        // 按照起始标识符0x2E拆分数据包
        while (localBuffer.isNotEmpty()){
            val headIndex = localBuffer.indexOf(head)
            AppLogUtil.e("headIndex: $headIndex")
            if(headIndex == -1){
                // 缓冲区中没有找到起始标识符，丢弃缓冲区中的数据
                localBuffer.clear()
                break
            }
            // 检查剩余数据长度是否足够解析一个完整的数据包
            if(localBuffer.size - headIndex < 7){
                HexUtil.byteArrayToHexStr(localBuffer.toByteArray()).apply {
                    AppLogUtil.e("数据包长度不足，等待下次接收更多数据1: $this")
                }
                break
            }
            // 解析数据包
            val dataType: Int = localBuffer[headIndex + 1].convInt()
            val length: Int = localBuffer[headIndex + 2].convInt()
            if(localBuffer.size - headIndex < length + 6){
                // 数据包长度不足，等待下次接收更多数据
                HexUtil.byteArrayToHexStr(localBuffer.toByteArray()).apply {
                    AppLogUtil.e("数据包长度不足，等待下次接收更多数据2: $this")
                }
                break
            }
            val newData = localBuffer.subList(headIndex,headIndex+length + 6).toByteArray()
            if(!isRight(newData)){
                AppLogUtil.e("检查校验和失败")
                break
            }
            AppLogUtil.e("得到数据: ${HexUtil.byteArrayToHexStr(newData)}")
            data2?.let {
                it(newData)
            }
            // 从缓冲区中移除已解析的数据包'
            if (headIndex + length + 6 <= localBuffer.size) {
                localBuffer = localBuffer.subList(headIndex + length + 6, localBuffer.size).toMutableList()
            }
        }
    }
    private fun isRight(data: ByteArray): Boolean {
        val headIndex = data.indexOf(head)
        AppLogUtil.e(("isRight ：${HexUtil.byteArrayToHexStr(data)} " +
                "- headIndex ：$headIndex " +
                "- data.size ：${data.size}"))
        if (headIndex != -1 && data.size >= headIndex + 2) { // Ensure enough data for further checks
            var len = 0
            try {
                len = andBitFF(data[headIndex + 2]) + 6 // 数据格式正确的标准长度
            }catch (e: ArrayIndexOutOfBoundsException){
                AppLogUtil.e("${e.printStackTrace()}格式错误")
                return false
            }
            //len.toString().loge("长度")
            ByteArray(data.size - headIndex).apply {
                System.arraycopy(data, headIndex, this, 0, data.size - 1)
                if (this.size == len) {
                    val dataType = andBitFF(this[headIndex + 1])
                    val length = andBitFF(this[headIndex + 2])
                    if (checkAck(this, headIndex,length,dataType)) {
                        AppLogUtil.e("格式正确")
                        return true
                    }
                }
            }
        }
        AppLogUtil.e("格式错误")
        return false
    }

    private fun dispatch(buffer: ByteArray) {
        AppLogUtil.e("dispatch ： ${buffer.toHexString()}")
        if(buffer[0].toInt() == 0xff){
            sendFF()
        }
        if(buffer[0].toInt() == 0x2e){
            if(!isRight(buffer)){
                return
            }
            when(buffer[1]){
                0xE1.toByte()->{
                    if(buffer[3] ==  0x81.toByte()){
                        if(fileBuffer.size > 0) {
                            sendFF().apply {
                                AppLogUtil.e("数据包进度 ：${fileBuffer.size}")
                                spm.sendCmdUpDataCanBus(checkSum(fileBuffer[0]))
                                fileBuffer.removeAt(0)
                            }

                            ((fileMaxProgress - fileBuffer.size.toFloat()) / fileMaxProgress  * 100f).apply {
                                AppLogUtil.e("setProgress : ${this}  ${fileBuffer.size} ${fileMaxProgress}}")
                                onUpGradeListener?.onProgress(this)
                            }

                        }
                    }else if(buffer[3] ==  0x0A.toByte()){
                        spm.isCanBusUpdate(false )
                        onUpGradeListener?.onFinish(file?.name?:"")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x02.toByte()){
                        onUpGradeListener?.onError("Code exceeds decoder board capacity.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x03.toByte()){
                        onUpGradeListener?.onError("Burning error, burning process terminated.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x05.toByte()){
                        onUpGradeListener?.onError("No SPI-FLASH detected.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x0B.toByte()){
                        onUpGradeListener?.onError("Incorrect fixed code.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x0C.toByte()){
                        onUpGradeListener?.onError("CRC error.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x0D.toByte()){
                        onUpGradeListener?.onError("Password error.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x0E.toByte()){
                        onUpGradeListener?.onError("Decoder board is in IAP standby mode.")
                        isBoteSend = false
                    }else if(buffer[3] ==  0x0F.toByte()){
                        onUpGradeListener?.onError("Private key error.")
                        isBoteSend = false
                    }

                    if(buffer[3].toInt() == 0x00){
                        //ToastUtil.showShort("send crc")
                        sendFF()
                        if(!isBoteSend) matchCode(currentCrc)
                    }
                }
            }
        }
    }

    private fun loadFile(onLoaded: (() -> Unit)? = null) {
        fileBuffer.clear()
        file?.let { f ->
            scope.launch {
                if (f.exists()) {
                    fileLength = getFileLength(f)
                    AppLogUtil.e("UPDE文件大小 ：$fileLength")

                    FileInputStream(f).use { input ->
                        val buffer = ByteArray(64)
                        var bytesRead: Int
                        var index = 0

                        while (input.read(buffer).also { bytesRead = it } != -1) {
                            val chunk = buffer.copyOfRange(0, bytesRead)
                            val type = if (input.available() == 0) 0x04 else 0x03
                            val packet = byteArrayOf(0x2E, 0xE0.toByte(), (chunk.size + 1).toByte(), type.toByte()) + chunk
                            fileBuffer.add(packet)
                            index++
                        }
                    }

                    fileMaxProgress = fileBuffer.size.toFloat()

                    // 计算 CRC
                    matchCodeCrc { crc ->
                        isLoadFinish = true
                        setCrc(crc)
                        AppLogUtil.e("setCrc")
                        //withContext(Dispatchers.Main) {
                            onUpGradeListener?.onStart()
                            onLoaded?.invoke()
                        //}
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onUpGradeListener?.onError("The upgrade file does not exist")
                    }
                }
            }
        } ?: run {
            onUpGradeListener?.onError("No upgrade file specified")
        }
    }

    private fun sendFF() {
        spm.sendCmdUpDataCanBus(byteArrayOf(0xff.toByte()))
    }

    private fun setCrc(crc: Int) {
        currentCrc = crc
    }

    fun start() {
        localBuffer.clear()
        scope.launch {
            if (isLoadFinish && currentCrc != -1 && fileMaxProgress.toInt() == fileBuffer.size) {
                withContext(Dispatchers.Main) {
                    onUpGradeListener?.onStart()
                }
                switchIAP()
            } else {
                loadFile {
                    AppLogUtil.e("文件加载完后自动继续")
                    start() // 文件加载完后自动继续
                }
            }
        }
    }

    fun dispatchMCUUpGradeData(buffer: ByteArray?){
        buffer?.let {
            val headIndex = it.indexOf(head)
            if(it.size == 1 && it[0] == 0xff.toByte()){
                dispatch(it)
            }else{
                verifyData(it) { newData ->
                    dispatch(newData)
                }
            }
        }
    }

    /**
     * 切换到 IAP 模式（支持传统 / RVC / OTA）
     */
    private fun switchIAP() {
        AppLogUtil.e("切换到 IAP 模式")

        // 1️⃣ 提取波特率的4个字节（大端序）
        val baudRate = BaseCommand.ibaudRate
        val baudBytes = byteArrayOf(
            (baudRate ushr 24).toByte(),
            (baudRate ushr 16).toByte(),
            (baudRate ushr 8).toByte(),
            baudRate.toByte()
        )

        // 2️⃣ 产品ID拆分成高低位
        val productHigh = ((productId shr 8) and 0xFF).toByte()
        val productLow = (productId and 0xFF).toByte()

        // 3️⃣ 模式字节（0x0D = 传统；0x10 = RVC/OTA）
        val mode = 0x10.toByte()

        // 4️⃣ 构建完整命令
        val cmdHeader = byteArrayOf(0xE0.toByte(), mode, 0x01)
        val cmdTail = byteArrayOf(productHigh, productLow, 0x01)

        // common 通常是协议头的通用部分，这里安全拼接
        val fullCmd = cmdHeader + baudBytes + common + cmdTail

        // 5️⃣ 发送并打印日志
        AppLogUtil.e("IAP 命令长度: ${fullCmd.size} 内容: ${fullCmd.joinToString(" ") { "%02X".format(it) }}")
        sendData(fullCmd)
    }

    private fun sendData(bytes: ByteArray) {
        byteArrayOf(0x2e).apply {
            this.plus(bytes).apply {
                spm.sendCmdUpDataCanBus(checkSum(this))
            }
        }
    }

    private fun matchCode(it: Int){//匹配代码
        val byte1: Byte = (fileLength ushr 24).toByte()
        val byte2: Byte = (fileLength ushr 16).toByte()
        val byte3: Byte = (fileLength ushr 8).toByte()
        val byte4: Byte = fileLength.toByte()

        val crcByte1: Byte = (it ushr 24).toByte()
        val crcByte2: Byte = (it ushr 16).toByte()
        val crcByte3: Byte = (it ushr 8).toByte()
        val crcByte4: Byte = it.toByte()

        byteArrayOf(0xE0.toByte(), 0x09, 0x02, byte1, byte2, byte3, byte4, crcByte1, crcByte2, crcByte3, crcByte4).apply {
            sendData(this)
        }
        isBoteSend = true
    }

    private fun matchCodeCrc(onResult: (Int) -> Unit) {
        file?.let { f ->
            scope.launch {
                if (f.exists()) {
                    var sum = 0
                    FileInputStream(f).use { stream ->
                        var byteRead: Int
                        while (stream.read().also { byteRead = it } != -1) {
                            sum += byteRead
                        }
                    }
                    withContext(Dispatchers.Main) {
                        onResult(sum)
                    }
                    AppLogUtil.e("Sum of bytes: $sum")
                } else {
                    withContext(Dispatchers.Main) {
                        onUpGradeListener?.onError("文件未找到")
                    }
                }
            }
        }
    }

    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02x".format(it) }
    }
    //校验码 检测
    private fun checkAck(ints: ByteArray, startIndex: Int, len: Int, dataType: Int,  u: ((ints: String)-> Unit) ?= null): Boolean {
        val data = ints.copyOfRange(startIndex + 3, startIndex + 3 + len)

        var sum = dataType + len
        for (element in data) {
            sum += element
        }
        var checksum = sum xor 0xFF
        checksum = checksum and 0xFF
        u?.let {
            it(
                StringUtils.bytes2HexString(ints, ints.size) + "\n" +
                        StringUtils.bytes2HexString(data, data.size) + "\n" +
                        "startIndex: " + startIndex + "\n" +
                        "len       : " + StringUtils.hexString(len) + "\n" +
                        "dataType  : " + StringUtils.hexString(dataType) + "\n" +
                        "checkResult : " + (ints[startIndex + 3 + len].convInt() == checksum) + "\n" +
                        "checksum : " + checksum + "\n" +
                        "checker : " + (ints[startIndex + 3 + len]).convInt() + "\n"
            )
        }
        return ((ints[startIndex + 3 + len]).convInt() == checksum)
    }
    //校验码计算，添加
    private fun checkSum(ints: ByteArray): ByteArray {
        var temp = 0
        val ins = ByteArray(ints.size + 1)
        ins[0] = ints[0]
        for (i in 1 until ints.size) {
            ins[i] = ints[i]
            temp += ints[i]
        }
        temp = temp xor 0xFF
        ins[ints.size] = temp.toByte()
        return ins
    }

    fun cancel() {
        scope.cancel()
        AppLogUtil.e("MCU升级任务已取消")
    }
}

fun mcuUpGradeManager(block: MCUUpGradeManager.Builder.() -> Unit): MCUUpGradeManager {
    return MCUUpGradeManager.Builder().apply(block).build()
}

fun isFile(file: File?): Boolean {
    return file != null && file.exists() && file.isFile()
}
fun getFileLength(file: File?): Long {
    if (!isFile(file)) return -1
    return file!!.length()
}
fun Byte.convInt(): Int{
    return andBitFF(this)
}

fun andBitFF(data: Byte) : Int{
    return data.toInt().and(0xff)
}