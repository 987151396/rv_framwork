package com.rv.framework.cmd

import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import com.rv.framework.cmd.base.AndroidDevice
import com.rv.framework.em.EqFieldMode
import com.rv.framework.fyt.car.FytCarStates
import com.rv.framework.fyt.ipc.FinalMainServer
import com.rv.framework.fyt.ipc.data.FinalSound
import com.rv.framework.manager.EqualizerManager
import com.rv.framework.utils.AppLogUtil
import com.rv.framework.utils.Utils

class FytSerialCommand : SerialCommand(), EqualizerCtrlInterface{
    var workThread: HandlerThread = HandlerThread("remote connection")
    var mHandler: Handler? = null

    init {
        FytCarStates.getCar(Utils.getApp())
        EqualizerManager.init(this);
        workThread.start()
        mHandler = Handler(workThread.looper)
    }

    override fun getAndroidDeviceType(): AndroidDevice {
        return AndroidDevice.AndroidFYT
    }

    override fun musicPrevious() {
        val intent = Intent()
        intent.setAction(PREVMUSIC)
        intent.setPackage(MUSIC_PKG)
        Utils.getApp().startService(intent)
    }

    override fun musicNext() {
        val intent = Intent()
        intent.setAction(NEXTMUSIC)
        intent.setPackage(MUSIC_PKG)
        Utils.getApp().startService(intent)
    }

    override fun musicPlayPause() {
        val intent = Intent()
        intent.setAction(PLAYPAUSEMUSIC)
        intent.setPackage(MUSIC_PKG)
        Utils.getApp().startService(intent)
    }

    override fun handleVolumeRequest(volume: Double) {
        mHandler?.postDelayed({
            FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                FinalMainServer.MODULE_CODE_SOUND,
                FinalSound.C_VOL, volume.toInt()
            );
        },20)
    }

    override fun handleEqParametersRequest(
        treble: Double?,
        mid: Double?,
        bass: Double?
    ) {

        treble?.let {
            mHandler?.post {
                FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                    FinalMainServer.MODULE_CODE_SOUND,
                    FinalSound.C_EQ_GAIN,1, it.toInt());
            }
        }
        bass?.let {
            mHandler?.post {
                FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                    FinalMainServer.MODULE_CODE_SOUND,
                    FinalSound.C_EQ_GAIN,0, it.toInt());
            }
        }
    }

    override fun handleModeRequest(mode: Int) {
        val mode = EqFieldMode.fromCode(mode)
        AppLogUtil.i("handleModeRequest - mode: $mode")
        when(mode){
            EqFieldMode.OtherMode -> {
                mHandler?.post {
                    FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                        FinalMainServer.MODULE_CODE_SOUND,
                        FinalSound.C_BAL_FADE,0, 16);
                }
            }
            EqFieldMode.FrontMode -> {
                mHandler?.post {
                    FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                        FinalMainServer.MODULE_CODE_SOUND,
                        FinalSound.C_BAL_FADE,8, 0);
                }
            }
            EqFieldMode.CenterMode -> {
                mHandler?.post {
                    FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                        FinalMainServer.MODULE_CODE_SOUND,
                        FinalSound.C_BAL_FADE,8, 8);
                }
            }
            EqFieldMode.RearMode -> {
                mHandler?.post {
                    FytCarStates.getCar(Utils.getApp()).mTools.sendInt(
                        FinalMainServer.MODULE_CODE_SOUND,
                        FinalSound.C_BAL_FADE,8, 16);
                }
            }
        }

    }

    override fun handleEqualizerInfo() {
        val state =  FytCarStates.getCar(Utils.getApp())
        state.sendModeUpdate()
        state.sendEqUpdate()
        state.sendVolumeUpdate()
    }

    override fun exit() {
        super.exit()
        workThread.quit()
    }

    companion object{
        const val NEXTMUSIC: String = "com.syu.music.next"
        const val PREVMUSIC: String = "com.syu.music.prev"
        const val PLAYPAUSEMUSIC: String = "com.syu.music.playpause"
        const val MUSIC_PKG: String = "com.syu.music"
    }
}