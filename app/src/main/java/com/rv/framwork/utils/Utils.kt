package com.rv.framwork.utils

import android.app.Application

internal object Utils {
    private var app: Application? = null

    fun init(application: Application) {
        app = application
    }

    fun getApp(): Application {
        return app ?: throw RuntimeException("Utils not initialized")
    }

    fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
