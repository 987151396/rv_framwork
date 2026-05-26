package com.rv.framwork.fyt.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.rv.framwork.manager.FlutterChannelManager
import com.rv.framwork.utils.AppLogUtil

class FytMusicService : Service() {
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId)
        } else {
            val action = intent.action
            if (LAUNCHER_MUSIC == action) {
                val bundle = intent.extras
                TOTALMINUTES = bundle!!.getLong(PLAY_TOTALMINUTES)
                CURMINUTES = bundle.getLong(PLAY_CURMINUTES)
                music_name = bundle.getString(TITLE)
                author_name = bundle.getString(PLAY_ARTIST)
                music_path = bundle.getString(PLAY_PATH)
                state = bundle.getBoolean(PLAY_STATE)

                if (bundle.getString(PLAY_ALBUM) != null) {
                    album = bundle.getString(PLAY_ALBUM)
                } else {
                    album = ""
                }
                // if(bundle.getByteArray(PLAY_ALBUMPIC) != null){
                // Log.i("hy", "1bundle");
                //// album_cover = new
                // byte[bundle.getByteArray(PLAY_ALBUMPIC).length];
                // album_cover = bundle.getByteArray(PLAY_ALBUMPIC);
                //
                // }else{
                // album_cover = null;
                //
                AppLogUtil.d("FytMusicService",
                    "TOTALMINUTES: $TOTALMINUTES " +
                            "- CURMINUTES: $CURMINUTES - " +
                            "- music_name: $music_name - " +
                            "- author_name: $author_name - " +
                            "- music_path: $music_path - " +
                            "- state: $state - " +
                            "- album: $album - " +
                            ""

                )
                FlutterChannelManager.sendMusicInfo(
                    TOTALMINUTES,CURMINUTES,music_name?:"",
                    author_name?:"",state,album?:""
                );
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        var music_name: String? = ""
        var author_name: String? = ""
        var music_path: String? = ""
        var state: Boolean = false
        var album: String? = ""
        var album_cover: ByteArray? = null

        const val TITLE: String = "title"
        const val PLAY_ARTIST: String = "play_artist"
        const val PLAY_STATE: String = "play_state"
        const val PLAY_ALBUM: String = "play_album"
        const val REMOVE_MUSIC: String = "com.fyt.systemui.remove"
        const val MUSIC_PKG: String = "com.syu.music"
        const val PLAY_PATH: String = "play_path"

        // public static final String PLAY_ALBUMPIC = "play_alubm_pic";
        const val NEXTMUSIC: String = "com.syu.music.next"
        const val PREVMUSIC: String = "com.syu.music.prev"
        const val PLAYPAUSEMUSIC: String = "com.syu.music.playpause"
        const val MUSICSERVICE: String = "com.fyt.launcher.music"
        var TOTALMINUTES: Long = 0
        var CURMINUTES: Long = 0
        const val PLAY_CURMINUTES: String = "play_cur"
        const val PLAY_TOTALMINUTES: String = "play_total"
        private val LAUNCHER_MUSIC: String = "com.fyt.launcher.music"
    }
}
