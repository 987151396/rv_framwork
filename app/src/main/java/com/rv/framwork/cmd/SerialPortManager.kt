package com.rv.framwork.cmd

import android.util.Log
import com.rv.framwork.cmd.base.BaseCommand
import com.rv.framwork.utils.AppLogUtil
import com.rv.framwork.utils.StringUtils
import com.vi.vioserial.NormalSerial
import kotlinx.coroutines.Delay
import java.util.concurrent.Executors

open class SerialPortManager{
    private var command : BaseCommand? = null
    companion object {
        val spm: SerialPortManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            SerialPortManager()
        }
    }
    fun init(com : BaseCommand?){
        this.command = com
    }
    fun exit(){
        this.command?.exit()
    }
    fun isCanBusUpdate(values : Boolean){
        command?.isCanBusUpdate(values)
    }
    fun setSerialDelay(delay: Int){
        NormalSerial.instance()?.setDelay(delay);
    }
    fun setSerialDelayDefault(){
        NormalSerial.instance()?.setDelayDefault();
    }
    fun addOnDataReceivedCallBack(dataHandler : BaseCommand.DataHandler){
        command?.dataHandler = dataHandler
    }


    fun sendCmd(data : ByteArray?){
        if(command?.isCanBusUpdate?.value == false) {
            check()
            data?.let {
                AppLogUtil.e(
                    "发送数据 ：${
                        StringUtils.bytes2HexString(
                            it,
                            it.size
                        )
                    } --- ${command == null}"
                )
                command?.sendCom2Port(it, it.size)
                command?.dataHandler?.onDataSend(data)
            }
        }
    }
    private val threadPool = Executors.newFixedThreadPool(1)
    /**
     * 只能给总线升级页面调用
     * */
    fun sendCmdUpDataCanBus(data : ByteArray?){
        check()
        WriteDataThread(data,command).apply {
            threadPool.execute(this)
        }
    }
    private class WriteDataThread(val data: ByteArray?, val command : BaseCommand?) : Thread(){
        override fun run() {
            data?.let {
                AppLogUtil.e("发送数据 ：${StringUtils.bytes2HexString(it, it.size)} --- ${command == null}"  )
                command?.sendCom2Port(it, it.size)
                command?.dataHandler?.onDataSend(data)
            }
            //sleep(10)
        }

    }
    private fun check(){
        if(command == null){
            throw RuntimeException("please init")
        }
    }
}