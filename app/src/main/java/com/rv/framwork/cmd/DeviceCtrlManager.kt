package com.rv.framwork.cmd

import com.rv.framwork.cmd.base.AndroidDevice
import com.rv.framwork.cmd.DeviceCtrlInterface

class DeviceCtrlManager {
    private var deviceCtrl : DeviceCtrlInterface? = null
    companion object {
        val dcm: DeviceCtrlManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            DeviceCtrlManager()
        }
    }
    fun init(d : DeviceCtrlInterface?){
        this.deviceCtrl = d
    }

    fun sendSquareCtrl(keyValue: Int, action: Int){
        deviceCtrl?.sendSquareCtrl(keyValue,action)
    }
    fun getSwcBtKeys(): Array<IntArray>? {
        return deviceCtrl?.getSwcBtKeys()
    }
    fun getAndroidDeviceType():AndroidDevice?{
        return deviceCtrl?.intAndroidDeviceType();
    }
    fun musicPrevious() {
        deviceCtrl?.musicPrevious()
    }

    fun musicNext() {
        deviceCtrl?.musicNext()
    }

    fun musicPlayPause() {
        deviceCtrl?.musicPlayPause()
    }
}