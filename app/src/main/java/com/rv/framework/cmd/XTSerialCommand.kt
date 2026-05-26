package com.rv.framework.cmd

import com.rv.framework.cmd.base.AndroidDevice

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