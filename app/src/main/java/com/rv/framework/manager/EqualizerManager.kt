package com.rv.framework.manager

import android.content.Context

import com.rv.framework.cmd.EqualizerCtrlInterface
import com.rv.framework.em.EqFieldMode
import com.rv.framework.utils.AppLogUtil
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import io.flutter.plugin.common.EventChannel.StreamHandler

/**
 * EqualizerManager
 * 负责管理均衡器相关的 EventChannel，向 Flutter 发送音量、EQ 参数和模式
 */
object EqualizerManager {

    private const val EQUALIZER_EVENT_CHANNEL = "equalizer_event"
    private var mEventSink: EventSink? = null
    private var equalizerCtrl : EqualizerCtrlInterface? = null

    fun init(e : EqualizerCtrlInterface?){
        this.equalizerCtrl = e
    }
    /**
     * 初始化均衡器 EventChannel
     */
    fun initEqualizerChannel(flutterEngine: io.flutter.embedding.engine.FlutterEngine, context: Context) {
        EventChannel(flutterEngine.dartExecutor.binaryMessenger, EQUALIZER_EVENT_CHANNEL).apply {
            setStreamHandler(object : StreamHandler {
                override fun onListen(arguments: Any?, events: EventSink?) {
                    mEventSink = events
                    AppLogUtil.i("Equalizer EventChannel connected")
                }

                override fun onCancel(arguments: Any?) {
                    mEventSink = null
                    AppLogUtil.i("Equalizer EventChannel disconnected")
                }
            })
        }
    }

    /**
     * 发送均衡器数据到 Flutter
     * @param volume 音量 (0.0 - 1.0)
     * @param treble 高音 (-10.0 - +10.0)
     * @param mid 中音 (-10.0 - +10.0)
     * @param bass 低音 (-10.0 - +10.0)
     * @param mode 模式 (pop, rock, classic, jazz, custom)
     */
    fun sendEqualizerData(
        volume: Double,
        treble: Double,
        mid: Double,
        bass: Double,
        mode: String
    ) {
        mEventSink?.let { sink ->
            try {
                val data = mapOf(
                    "volume" to volume,
                    "maxVolume" to 36,
                    "treble" to treble,
                    "mid" to mid,
                    "bass" to bass,
                    "mode" to mode
                )
                sink.success(data)
                AppLogUtil.i("Equalizer data sent: volume=$volume, treble=$treble, mid=$mid, bass=$bass, mode=$mode")
            } catch (e: Exception) {
                AppLogUtil.e("Failed to send equalizer data: ${e.message}")
            }
        } ?: run {
            AppLogUtil.w("Equalizer EventSink is null, cannot send data")
        }
    }

    /**
     * 发送音量更新
     */
    fun sendVolumeUpdate(volume: Double) {
        mEventSink?.let { sink ->
            try {
                sink.success(mapOf(
                    "volume" to volume,
                    "maxVolume" to 36,
                ))
                AppLogUtil.i("Volume update sent: $volume")
            } catch (e: Exception) {
                AppLogUtil.e("Failed to send volume update: ${e.message}")
            }
        }
    }

    /**
     * 发送 EQ 参数更新
     */
    fun sendEqUpdate(treble: Double, mid: Double, bass: Double) {
        mEventSink?.let { sink ->
            try {
                val data = mapOf(
                    "treble" to treble,
                    "mid" to mid,
                    "bass" to bass
                )
                sink.success(data)
                AppLogUtil.i("EQ update sent: treble=$treble, mid=$mid, bass=$bass")
            } catch (e: Exception) {
                AppLogUtil.e("Failed to send EQ update: ${e.message}")
            }
        }
    }

    /**
     * 发送模式更新
     */
    fun sendModeUpdate(mode: EqFieldMode) {
        mEventSink?.let { sink ->
            try {
                sink.success(mapOf("mode" to mode.code))
                AppLogUtil.i("Mode update sent: ${mode.code}")
            } catch (e: Exception) {
                AppLogUtil.e("Failed to send mode update: ${e.message}")
            }
        }
    }

    /**
     * 监听来自 Flutter 的音量设置请求
     */
    fun handleVolumeRequest(volume: Double) {
        AppLogUtil.i("Received volume request from Flutter: $volume")
        // 这里可以添加实际的硬件控制逻辑
        // 例如：发送到 MCU 或音频硬件
        equalizerCtrl?.handleVolumeRequest(volume);
    }

    /**
     * 监听来自 Flutter 的 EQ 参数设置请求
     */
    fun handleEqParametersRequest(treble: Double?, mid: Double?, bass: Double?) {
        AppLogUtil.i("Received EQ parameters request from Flutter: treble=$treble, mid=$mid, bass=$bass")
        // 这里可以添加实际的硬件控制逻辑
        // 例如：发送到 MCU 或音频硬件
        equalizerCtrl?.handleEqParametersRequest(treble,mid,bass)
    }

    /**
     * 监听来自 Flutter 的模式设置请求
     */
    fun handleModeRequest(mode: Int) {
        AppLogUtil.i("Received mode request from Flutter: $mode")
        // 这里可以添加实际的硬件控制逻辑
        // 例如：发送到 MCU 或音频硬件
        equalizerCtrl?.handleModeRequest(mode)
    }

    fun handleEqualizerInfo(){
        equalizerCtrl?.handleEqualizerInfo()
    }
}
