package com.rv.framwork.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.rv.framwork.broadcast.BroadEventConst
import com.rv.framwork.broadcast.BroadEventConst.DREAM_EXTRA_KEY
import com.rv.framwork.broadcast.BroadEventConst.EXTRA_LAUNCHER_KEY_WORD
import com.rv.framwork.broadcast.BroadEventConst.EXTRA_VOICE_KEY_WORD
import com.rv.framwork.broadcast.BroadEventConst.EXTRA_VOICE_PARAM
import com.rv.framwork.broadcast.BroadEventConst.MCU_MSG_CAN_ALL_INFO
import com.rv.framwork.broadcast.BroadEventConst.MCU_MSG_CAN_ATADATA
import com.rv.framwork.broadcast.BroadEventConst.MCU_MSG_MAIL_VOL_VAL
import com.rv.framwork.broadcast.BroadEventConst.VALID_MODE_INFOR_CHANGE
import com.rv.framwork.broadcast.BroadEventConst.VOLUME_SHOW
import com.rv.framwork.broadcast.BroadEventConst.VOLUME_SHOW_EXTRA
import com.rv.framwork.broadcast.BroadEventConst.ZXW_SYS_KEY_EVT
import com.rv.framwork.broadcast.MediaInfoExtra
import com.rv.framwork.flowbus.postEvent
import com.rv.framwork.manager.FlutterChannelManager
import com.rv.framwork.utils.AppLogUtil

class ZxwMcuBroadcast : BroadcastReceiver() {
    var onDataReceivedCallBack : ((data: ByteArray?) -> Unit)? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        AppLogUtil.e("广播接受 ${intent?.action}")
        if (MCU_MSG_CAN_ALL_INFO == intent?.action || MCU_MSG_CAN_ATADATA == intent?.action ) {
            //AppLogUtil.e("ZXW广播数据${intent.action}")
            val byteArray = intent.getByteArrayExtra("EventUtils.CAR_AIR_DATA")
            byteArray?.let {
                val result: Result<ByteArray> = it.copyOfRange(1, it.size - 1).runCatching {
                    // 在这里执行可能会抛出异常的操作
                    // 这里可以放置你的代码逻辑，最终返回一个字符串
                    this
                }
                result.onSuccess {data->
                    onDataReceivedCallBack?.invoke(data)
                }
                //AppLogUtil.e("MyMcuBroadcast：广播接受 ${StringUtils.bytes2HexString(byteArray,byteArray.size)}")
            }
        }else if (BroadEventConst.ACTION_LAUNCHER_KEY_CTRL == intent?.action ){
            intent.getStringExtra(EXTRA_LAUNCHER_KEY_WORD).apply {
                //AppLogUtil.e("ZXW屏幕旋转: ${ScreenUtil.isPortrait()} - ${ScreenUtil.isLandscape()}")
            }
        }else if(BroadEventConst.ACTION_VOICE_CTRL == intent?.action){
            val strVoiceKeyWord = intent.getStringExtra(EXTRA_VOICE_KEY_WORD);
            val fVoiceParam = intent.getFloatExtra(EXTRA_VOICE_PARAM,0.0f);
            AppLogUtil.e("strVoiceKeyWord: $strVoiceKeyWord -- fVoiceParam:$fVoiceParam")
        }else if(BroadEventConst.DREAM_ACTION == intent?.action){
            val isEnable = intent.extras?.getBoolean(DREAM_EXTRA_KEY)?:false
            AppLogUtil.e("DREAM_EXTRA_KEY - isEnable: $isEnable")
            FlutterChannelManager.sendScreensaver(isEnable)
            postEvent(ScreensaverEvent(isEnable))
        }else if(BroadEventConst.MCU_MSG_MAIL_VOL == intent?.action){
            intent.getIntExtra(MCU_MSG_MAIL_VOL_VAL,0).apply {
                AppLogUtil.e("MCU_MSG_MAIL_VOL: $this")
                postEvent(SysVolumeEvent(this))
            }
        }else if(ZXW_SYS_KEY_EVT == intent?.action){

        }else if(VOLUME_SHOW == intent?.action){
            intent.getStringExtra(VOLUME_SHOW_EXTRA).apply {
                AppLogUtil.e("VOLUME_SHOW_EXTRA: $this")
                if(this == "VOLUME_SHOW")
                    postEvent(ShowVolumeEvent())
            }
        }else if (VALID_MODE_INFOR_CHANGE == intent?.action){
            val title = intent.getStringExtra(MediaInfoExtra.Title)
            val album = intent.getStringExtra(MediaInfoExtra.Album)
            val artist = intent.getStringExtra(MediaInfoExtra.Artist)
            val curTrack = intent.getIntExtra(MediaInfoExtra.CurTrack,0)
            val totTrack = intent.getIntExtra(MediaInfoExtra.TotTrack,0)
            val curFolder = intent.getIntExtra(MediaInfoExtra.CurFolder,0)
            val totFolder = intent.getIntExtra(MediaInfoExtra.TotFolder,0)
            val curTime = intent.getIntExtra(MediaInfoExtra.CurTime,0)
            val totTime = intent.getIntExtra(MediaInfoExtra.TotTime,0)
            val loopMode = intent.getIntExtra(MediaInfoExtra.LoopMode,0)
            val repeatMode = intent.getIntExtra(MediaInfoExtra.RepeatMode,0)
            val playStatus = intent.getIntExtra(MediaInfoExtra.PlayStatus,0)

            AppLogUtil.d("ZXWMusicBroadcast",
                "TOTALMINUTES: $totTime " +
                        "- CURMINUTES: $curTime - " +
                        "- music_name: $title - " +
                        "- author_name: $artist - " +
                        //"- music_path: $music_path - " +
                        "- state: $playStatus - " +
                        "- album: $album - " +
                        ""

            )
            FlutterChannelManager.sendMusicInfo(
                totTime.toLong(), curTime.toLong(), title ?:"",
                artist ?:"", playStatus == 0, album ?:""
            );

        }
    }

}