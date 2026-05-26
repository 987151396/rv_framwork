package com.rv.framework.utils

import android.text.TextUtils
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.math.RoundingMode
import java.net.URLEncoder
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import java.util.regex.Pattern
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

/**
 * 与字符串操作相关的工具类
 * Created by chenmingzhen on 16-6-7.
 */
internal object StringUtils {
    const val EMPTY = ""
    private val TAG = StringUtils::class.java.simpleName
    private const val EMAIL_PATTERN = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
    private const val USERNAME_PATTERN = "^[A-Za-z0-9_]+$"
    private const val PHONE_PATTERN = "^\\+?\\d{4,20}$"

    /**
     * 查看一个字符是否为空
     *
     * @param str 需要检查的字条
     * @return 是否为空
     */
    fun isEmpty(str: String?): Boolean {
        return if (str == null || str.trim { it <= ' ' } == "") {
            true
        } else {
            false
        }
    }

    /**
     * 判断是否引用为空
     *
     * @param str
     * @return
     */
    fun checkNotNull(str: String?): String {
        return str ?: EMPTY
    }

    fun getWordCount(str: String): Int {
        var str = str
        str = str.replace("[^\\x00-\\xff]".toRegex(), "**") //汉字或全角的情况
        return str.length
    }

    /**
     * 生成中间省略的字符串
     * 这个方法是为了使得字符串过长的时候进行中间截取成
     * XXX..XXX.DOC 为什么不用系统的ellipse=middle，使用这个属性在某种命名的文件下会导致系统崩溃，这是Android系统的bug.
     *
     * @param content           字符串长度
     * @param max               允许字符串最大值
     * @param allowChineseCount 允许中文最大值
     * @param start             省略开始于
     * @param end               省略结束于
     * @return
     */
    fun middleEllipse(
        content: String,
        max: Int,
        allowChineseCount: Int,
        start: Int,
        end: Int
    ): String {
        var index = 0
        var chineseCount = 0
        for (i in 0 until content.length) {
            val retContent = content.substring(i, i + 1)
            // 生成一个Pattern,同时编译一个正则表达式
            val isChina = retContent.matches("[\u4E00-\u9FA5]".toRegex())
            if (isChina) {
                index = index + 2
                chineseCount++
            } else {
                index = index + 1
            }
        }
        if (index < max) {
            return content
        }
        val sb = StringBuffer()
        if (chineseCount > allowChineseCount) {
            sb.append(content.substring(0, start + 2))
        } else {
            sb.append(content.substring(0, start + 5))
        }
        sb.append("...")
        sb.append(content.substring(content.length - end, content.length))
        return sb.toString()
    }

    /**
     * 压缩字符串
     *
     * @param str
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun compress(str: String): String {
        val time1 = System.currentTimeMillis()
        if (isEmpty(str)) {
            return str
        }
        val out = ByteArrayOutputStream()
        val gzip = GZIPOutputStream(out)
        gzip.write(str.toByteArray())
        gzip.close()
        val rs = out.toString("ISO-8859-1")
        val time2 = System.currentTimeMillis()
        println("compress String coast time:" + (time2 - time1))
        return rs
    }

    /**
     * 解压缩字符串
     *
     * @param str
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun uncompress(str: String): String {
        val time1 = System.currentTimeMillis()
        if (isEmpty(str)) {
            return str
        }
        val out = ByteArrayOutputStream()
        val `in` = ByteArrayInputStream(str.toByteArray(charset("ISO-8859-1")))
        val ungzip = GZIPInputStream(`in`)
        val buffer = ByteArray(256)
        var n: Int
        while (ungzip.read(buffer).also { n = it } >= 0) {
            out.write(buffer, 0, n)
        }
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        val rs = out.toString()
        val time2 = System.currentTimeMillis()
        println("uncompress String coast time:" + (time2 - time1))
        return rs
    }

    @Throws(IOException::class)
    fun uncompress(bytes: ByteArray?): String? {
        if (bytes == null || bytes.size == 0) {
            return null
        }
        // System.out.println("bytes..length:"+bytes.length);
        val out = ByteArrayOutputStream()
        val `in` = ByteArrayInputStream(bytes)
        val gunzip = GZIPInputStream(`in`)
        val buffer = ByteArray(256)
        var n: Int
        while (gunzip.read(buffer).also { n = it } >= 0) {
            out.write(buffer, 0, n)
        }
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        return out.toString("GBK")
    }

    /**
     * 防止字符串编码错误
     *
     * @param value
     * @return
     */
    fun encode(value: String?): String {
        var result = ""
        try {
            result = URLEncoder.encode(value, "utf-8")
        } catch (e: Exception) {
            e.message?.let { AppLogUtil.d(TAG, it) }
        }
        return result
    }

    /**
     * 将字符串转化为int类型
     *
     * @param str
     * @param defaultValue
     * @return
     */
    @JvmOverloads
    fun toInt(str: String?, defaultValue: Int = 0): Int {
        if (isEmpty(str)) return defaultValue
        try {
            return Integer.valueOf(str)
        } catch (e: NumberFormatException) {
            e.message?.let { AppLogUtil.d(TAG, it) }
        }
        return defaultValue
    }

    /**
     * 将字符串转化为long类型
     *
     * @param str
     * @param defaultValue
     * @return
     */
    fun toLong(str: String?, defaultValue: Long): Long {
        if (isEmpty(str)) return defaultValue
        try {
            return java.lang.Long.valueOf(str)
        } catch (e: Exception) {
            e.message?.let { AppLogUtil.d(TAG, it) }
        }
        return defaultValue
    }

    /**
     * String转化为float
     *
     * @param value
     * @return
     */
    fun toFloat(value: String?): Float {
        if (isEmpty(value)) return 0f
        try {
            return java.lang.Float.valueOf(value)
        } catch (e: NumberFormatException) {
            e.message?.let { AppLogUtil.d(TAG, it) }
        }
        return 0f
    }

    /**
     * 字符在去除空格
     */
    fun trim(str: String?): String {
        return str?.trim { it <= ' ' } ?: ""
    }

    /**
     * 验证是否是邮箱
     */
    fun isEmail(email: String?): Boolean {
        return try {
            val p = Pattern.compile(EMAIL_PATTERN)
            val m = p.matcher(email)
            m.matches()
        } catch (e: Exception) {
            e.message?.let { AppLogUtil.d(TAG, it) }
            false
        }
    }

    /**
     * 验证是否手机号码
     */
    fun isPhone(phone: String?): Boolean {
        return try {
            val p = Pattern.compile(PHONE_PATTERN)
            val m = p.matcher(phone)
            m.matches()
        } catch (e: Exception) {
            e.message?.let { AppLogUtil.d(TAG, it) }
            false
        }
    }

    /**
     * 判断是否为固定电话，需要加入区号
     */
    fun isCall(call: String?): Boolean {
        return try {
            val regExp = "^\\d{3,4}-\\d{7,8}$"
            val p = Pattern.compile(regExp)
            val m = p.matcher(call)
            m.matches()
        } catch (e: Exception) {
            e.message?.let { AppLogUtil.d(TAG, it) }
            false
        }
    }

    /**
     * 判断是否为正确的邮政编码
     */
    fun isPostCode(postCode: String?): Boolean {
        val str = "^[1-9][0-9]{5}$"
        return Pattern.compile(str).matcher(postCode).matches()
    }

    /**
     * 有效用户名
     *
     * @param username
     * @return
     */
    fun validateUsername(username: String?): Boolean {
        return Pattern.matches(USERNAME_PATTERN, username)
    }

    /**
     * 有效的密码
     *
     * @param password
     * @return
     */
    fun validatePassword(password: String): Boolean {
        return password.length > 5
    }

    /**
     * 判断信息是否为空类型,默认返回不空  true为空,false为不空。如果是纯空字符组成的字符串也是返回true
     *
     * @author liaofucheng
     * @date 2016年6月12日
     */
    fun isNull(value: Any?): Boolean {
        try {
            return if (value == null || value.toString()
                    .trim { it <= ' ' }.length == 0
            ) true else false
        } catch (e: Exception) {
            AppLogUtil.e(TAG, e)
        }
        return false
    }

    /*
     * 将Object类型转换成字符串，如果为null，返回的是""
     * @return String
     * @param
     * @author liaofucheng
     * @date 2016/7/14 9:34
     */
    fun object2String(`object`: Any?): String {
        try {
            if (`object` != null) {
                return `object`.toString()
            }
        } catch (e: Exception) {
            AppLogUtil.e(TAG, e)
        }
        return ""
    }

    /**
     * 数字转中文数字
     *
     * @param d
     */
    fun numberToChinese(d: Int): String {
        val ss = arrayOf("元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿")
        val s = d.toString()
        var sb = StringBuffer()
        try {
            val sss = sb.toString()
            var i = 0
            for (j in sss.length downTo 1) {
                sb = sb.insert(j, ss[i++])
            }
            return sb.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 分钟转天或者转小时
     *
     * @param minutes
     * @param returnType 1=返回天带2位小数，2=返回小时+分钟，returnType不传则智能返回(未测试)
     * @returns {String}
     * @author konglingxian
     */
    fun minuteToStr(minutes: Double, returnType: Int): String {
        try {
            var str = ""
            if (minutes == 0.0) return "0分钟"
            val millisecond = minutes * 60 * 1000
            val day = millisecond / 86400000
            val hour = millisecond % 86400000 / 3600000
            val minute = millisecond % 86400000 % 3600000 / 60000
            val dcmFmt = DecimalFormat("0.0")
            if (day > 1) {
                str = day.toInt().toString() + "天"
                if (hour > 0) {
                    str += dcmFmt.format(hour) + "小时"
                }
            } else {
                if (hour > 1) {
                    str += hour.toInt().toString() + "小时"
                }
            }
            if (minute > 0) {
                str += minute.toInt().toString() + "分钟"
            }

            //返回*天*小时
            if (returnType == 1) {
                str = ""
                if (day > 1) {
                    str = day.toInt().toString() + "天"
                    if (hour > 0) {
                        str += dcmFmt.format(hour) + "小时"
                    }
                }
            }

            //返回*小时*分钟
            if (returnType == 2) {
                str = ""
                if (day > 0 && hour > 0) {
                    str += (hour + day.toInt() * 24).toInt().toString() + "小时"
                }
                if (minute > 0) {
                    str += minute.toInt().toString() + "分钟"
                }
            }
            return str
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 转全角和半角
     *
     * @param src
     * @return
     */
    fun toSemiangle(src: String): String {
        val c = src.toCharArray()
        for (index in c.indices) {
            if (c[index].code == 12288) { // 全角空格
                c[index] = 32.toChar()
            } else if (c[index].code > 65280 && c[index].code < 65375) { // 其他全角字符
                c[index] = (c[index].code - 65248).toChar()
            }
        }
        return c.toString()
    }

    fun getHostName(urlString: String): String {
        var urlString = urlString
        var head = ""
        var index = urlString.indexOf("://")
        if (index != -1) {
            head = urlString.substring(0, index + 3)
            urlString = urlString.substring(index + 3)
        }
        index = urlString.indexOf("/")
        if (index != -1) {
            urlString = urlString.substring(0, index + 1)
        }
        return head + urlString
    }

    fun getDataSize(var0: Long): String {
        val var2 = DecimalFormat("###.00")
        return if (var0 < 1024L) var0.toString() + "bytes" else if (var0 < 1048576L) (var2.format((var0.toFloat() / 1024.0f).toDouble())
                + "KB") else if (var0 < 1073741824L) (var2.format((var0.toFloat() / 1024.0f / 1024.0f).toDouble())
                + "MB") else if (var0 < 0L) (var2.format((var0.toFloat() / 1024.0f / 1024.0f / 1024.0f).toDouble())
                + "GB") else "error"
    }

    fun isNnull(value: String?): Boolean {
        return if (value == null || value.trim { it <= ' ' }.length == 0) true else false
    }

    /**
     * 有效的邮箱
     *
     * @param email
     * @return
     */
    fun validateEmail(email: String?): Boolean {
        val EMAIL_PATTERN = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
        val matcher = Pattern.compile(EMAIL_PATTERN).matcher(email)
        return matcher.matches()
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    fun isNumber(str: String?): Boolean {
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }

    // 判断一个字符是否是中文
    fun isChinese(c: Char): Boolean {
        return c.code >= 0x4E00 && c.code <= 0x9FA5 // 根据字节码判断
    }

    // 判断一个字符串是否含有中文
    fun isChinese(str: String?): Boolean {
        if (str == null) return false
        for (c in str.toCharArray()) {
            if (isChinese(c)) return true // 有一个中文字符就返回
        }
        return false
    }

    // 去除字符串中的空格、回车、换行符、制表符
    fun replaceBlank(str: String?): String {
        var dest = ""
        if (str != null) {
            val p = Pattern.compile("\\s*|\t|\r|\n")
            val m = p.matcher(str)
            dest = m.replaceAll("")
        }
        return dest
    }

    /**
     * 替换手机号码的中间字符
     *
     * @param replacement 替换者
     * @return
     */
    fun replaceMidChar(src: String, replacement: String): String {
        if (TextUtils.isEmpty(src)) {
            return ""
        }
        val length = src.length
        val exp: String
        exp = if (length <= 1) {
            return replacement
        } else if (length == 2) {
            "(?<=\\d)\\d(?=\\d{0})"
        } else if (length < 6) {
            "(?<=\\d)\\d(?=\\d)"
        } else {
            "(?<=\\d{2})\\d(?=\\d{2})"
        }
        return src.replace(exp.toRegex(), replacement)
    }

    /**
     * 替换银行卡号的中间字符
     *
     * @param replacement 替换者
     * @return
     */
    fun replaceCardMidChar(src: String, replacement: String?): String {
        if (TextUtils.isEmpty(src)) {
            return ""
        }
        val length = src.length
        val exp = "(?<=\\d{6})\\d(?=\\d{4})"
        /*if (length <= 1) {
            return replacement;
        } else if (length == 2) {
            exp = "(?<=\\d)\\d(?=\\d{0})";
        } else if (length < 6) {
            exp = "(?<=\\d)\\d(?=\\d)";
        } else {
            exp = "(?<=\\d{2})\\d(?=\\d{2})";
        }*/return src.replace(exp.toRegex(), replacement!!)
    }

    /**
     * 隐藏邮箱的中间部分
     *
     * @return
     */
    fun replaceMidEmail(src: String, replacement: String): String {
        if (TextUtils.isEmpty(src)) {
            return ""
        }
        if (!src.contains("@")) {
            return replaceMidChar(src, replacement)
        }

        //分割后会丢失@
        val parts = src.split("@".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val part1 = parts[0]
        var part2 = ""
        if (parts.size > 1) {
            part2 = parts[parts.size - 1] //截取到最后一个@
        }
        val part1Result = replaceMidChar(part1, replacement)
        return "$part1Result@$part2"
    }

    /**
     * 提取字符串中的数字。避免后台返回格式错误
     *
     * @param str
     * @return
     */
    fun getNumber(str: String?): String {
        if (TextUtils.isEmpty(str)) {
            return "0"
        }
        val regEx = "[^0-9.]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(str)
        val trim = m.replaceAll("").trim { it <= ' ' }
        return if (TextUtils.isEmpty(trim)) {
            "0"
        } else trim
    }

    /**
     * 四舍五入
     *
     * @param number
     * @param count  保留位数
     * @return
     */
    fun setRounding(number: Any?, count: Int): String {
        val integerInstance = NumberFormat.getIntegerInstance()
        integerInstance.maximumFractionDigits = count
        integerInstance.minimumFractionDigits = count
        integerInstance.roundingMode = RoundingMode.UP
        return integerInstance.format(number)
    }

    /**
     * 去除结尾字符
     *
     * @param original 源字符串
     * @param chr      去除字符
     * @return
     */
    fun removeEndStr(original: String, chr: String?): String {
        var address = ""
        if (!TextUtils.isEmpty(original)) {
            val substring = original.trim { it <= ' ' }
                .substring(
                    original.trim { it <= ' ' }.length - 1,
                    original.trim { it <= ' ' }.length
                )
            address = if (substring == ",") {
                val trim = original.trim { it <= ' ' }
                val indexlast = trim.lastIndexOf(chr!!)
                trim.substring(0, indexlast) + trim.substring(indexlast + 1, trim.length)
            } else {
                original.trim { it <= ' ' }
            }
        }
        return address
    }



    fun bytes2HexString(b: ByteArray, len: Int): String {
        var ret = ""
        for (i in 0 until len) {
            var hex = Integer.toHexString(b[i].toInt() and 0xFF)
            if (hex.length == 1) {
                hex = "0$hex"
            }
            ret += hex.uppercase(Locale.getDefault()) + "\t"
        }
        return ret
    }

    fun bytes2HexString(b: IntArray, len: Int): String {
        var ret = ""
        for (i in 0 until len) {
            var hex = Integer.toHexString(b[i] and 0xFF)
            if (hex.length == 1) {
                hex = "0$hex"
            }
            ret += hex.uppercase(Locale.getDefault()) + "\t"
        }
        return ret
    }

    fun hexString(i : Int): String {
        return "0x${Integer.toHexString(i)}"
    }

}