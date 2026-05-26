package com.rv.framework.cmd


interface EqualizerCtrlInterface {
    /**
     * 监听来自 Flutter 的音量设置请求
     */
    fun handleVolumeRequest(volume: Double);

    /**
     * 监听来自 Flutter 的 EQ 参数设置请求
     */
    fun handleEqParametersRequest(treble: Double?, mid: Double?, bass: Double?)

    /**
     * 监听来自 Flutter 的模式设置请求
     */
    fun handleModeRequest(mode: Int)

    fun handleEqualizerInfo()
}