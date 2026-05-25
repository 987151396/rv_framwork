package com.rv.framwork.cmd

import com.rv.framwork.cmd.base.AndroidDevice

class XTSerialCommand : SerialCommand() {
    override fun getAndroidDeviceType(): AndroidDevice {
        return AndroidDevice.AndroidXT
    }

    override fun musicPrevious() {

    }

    override fun musicNext() {

    }

    override fun musicPlayPause() {

    }
}