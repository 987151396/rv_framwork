package com.rv.framework.manager

//import com.google.android.things.bluetooth.BluetoothProfile
import android.content.Context
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel

object FlutterChannelManager {



    private val  FLUTTER_NATIVE_MUSIC_INFO = "flutter_native_music_info"

    private var mEventSinkMusic: EventChannel.EventSink? = null
    private var mOrientationEventSink: EventChannel.EventSink? = null
    private var isFlutterAlive: Boolean = false





    fun eventChannelFunction(flutterEngine: FlutterEngine,context: Context) {

        EventChannel(flutterEngine.dartExecutor.binaryMessenger, FLUTTER_NATIVE_MUSIC_INFO).apply {
            this.setStreamHandler(
                object : EventChannel.StreamHandler {
                    override fun onListen(o: Any?, eventSink: EventChannel.EventSink?) {
                        mEventSinkMusic = eventSink
                    }
                    override fun onCancel(o: Any?) {}
                }
            )
        }
    }


    fun sendMusicInfo(
        totalMin: Long,
        curMin: Long,
        musicName: String,
        authorName: String,
        state: Boolean,
        album: String,
    ){
        if (!isFlutterAlive) return
        mutableMapOf(
            "totalMin" to totalMin,
            "curMin" to curMin,
            "musicName" to musicName,
            "authorName" to authorName,
            "state" to state,
            "album" to album,
        ).apply {
            mEventSinkMusic?.success(this)
        }
    }

    fun isFlutterEngineAlive(): Boolean = isFlutterAlive
}
