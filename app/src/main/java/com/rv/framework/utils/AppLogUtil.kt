package com.rv.framework.utils

import android.text.TextUtils
import android.util.Log

internal object AppLogUtil {
    var customTagPrefix: String = "simple_"

    var allowD: Boolean = AppUtil.isDebug
    var allowE: Boolean = AppUtil.isDebug
    var allowI: Boolean = AppUtil.isDebug
    var allowV: Boolean = AppUtil.isDebug
    var allowW: Boolean = AppUtil.isDebug
    var allowWtf: Boolean = AppUtil.isDebug

    private fun generateTag(caller: StackTraceElement): String {
        var tag = "%s.%s(L:%d)"
        var callerClazzName = caller.getClassName()
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber())
        tag = if (TextUtils.isEmpty(customTagPrefix)) tag else customTagPrefix + ":" + tag
        return tag
    }

    // 重写 CustomLogger 可替换默认使用的 LOG 类
    var customLogger: CustomLogger? = null

    fun d(content: String) {
        if (!allowD) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.d(tag, content)
        } else {
            Log.d(tag, content)
        }
    }


    fun d(tag: String?, content: String) {
        if (!allowD) return
        if (customLogger != null) {
            customLogger!!.d(tag, content)
        } else {
            Log.d(tag, content)
        }
    }

    val callerStackTraceElement: StackTraceElement
        get() = Thread.currentThread().getStackTrace()[4]

    fun d(content: String?, tr: Throwable?) {
        if (!allowD) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.d(tag, content, tr)
        } else {
            Log.d(tag, content, tr)
        }
    }

    @JvmStatic
    fun e(content: String) {
        if (!allowE) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.e(tag, content)
        } else {
            Log.e(tag, content)
        }
    }

    fun e(e: Exception) {
        if (!allowE) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.e(tag, e.message, e)
        } else {
            Log.e(tag, e.message, e)
        }
    }

    fun e(content: String?, tr: Throwable?) {
        if (!allowE) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.e(tag, content, tr)
        } else {
            Log.e(tag, content, tr)
        }
    }

    fun i(content: String) {
        if (!allowI) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.i(tag, content)
        } else {
            Log.i(tag, content)
        }
    }

    fun i(content: String?, tr: Throwable?) {
        if (!allowI) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.i(tag, content, tr)
        } else {
            Log.i(tag, content, tr)
        }
    }

    fun v(content: String) {
        if (!allowV) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.v(tag, content)
        } else {
            Log.v(tag, content)
        }
    }

    fun v(content: String?, tr: Throwable?) {
        if (!allowV) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.v(tag, content, tr)
        } else {
            Log.v(tag, content, tr)
        }
    }

    fun w(content: String) {
        if (!allowW) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.w(tag, content)
        } else {
            Log.w(tag, content)
        }
    }

    fun w(content: String?, tr: Throwable?) {
        if (!allowW) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.w(tag, content, tr)
        } else {
            Log.w(tag, content, tr)
        }
    }

    fun w(tr: Throwable?) {
        if (!allowW) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.w(tag, tr)
        } else {
            Log.w(tag, tr)
        }
    }


    fun wtf(content: String?) {
        if (!allowWtf) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.wtf(tag, content)
        } else {
            Log.wtf(tag, content)
        }
    }

    fun wtf(content: String?, tr: Throwable?) {
        if (!allowWtf) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.wtf(tag, content, tr)
        } else {
            Log.wtf(tag, content, tr)
        }
    }

    fun wtf(tr: Throwable) {
        if (!allowWtf) return
        val caller: StackTraceElement = callerStackTraceElement
        val tag = generateTag(caller)

        if (customLogger != null) {
            customLogger!!.wtf(tag, tr)
        } else {
            Log.wtf(tag, tr)
        }
    }

    interface CustomLogger {
        fun d(tag: String?, content: String?)

        fun d(tag: String?, content: String?, tr: Throwable?)

        fun e(tag: String?, content: String?)

        fun e(tag: String?, content: String?, tr: Throwable?)

        fun i(tag: String?, content: String?)

        fun i(tag: String?, content: String?, tr: Throwable?)

        fun v(tag: String?, content: String?)

        fun v(tag: String?, content: String?, tr: Throwable?)

        fun w(tag: String?, content: String?)

        fun w(tag: String?, content: String?, tr: Throwable?)

        fun w(tag: String?, tr: Throwable?)

        fun wtf(tag: String?, content: String?)

        fun wtf(tag: String?, content: String?, tr: Throwable?)

        fun wtf(tag: String?, tr: Throwable?)
    }
}
