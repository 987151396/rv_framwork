package com.rv.framwork.cmd

import com.rv.framwork.cmd.base.AndroidDevice

interface DeviceCtrlInterface {
    fun sendSquareCtrl(keyValue: Int, action: Int)
    fun getSwcBtKeys():Array<IntArray>
    fun intAndroidDeviceType(): AndroidDevice;

    fun musicPrevious();
    fun musicNext();
    fun musicPlayPause();
}