package com.rv.framework.cmd

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Process
import androidx.core.content.ContextCompat
import com.rv.framework.broadcast.BroadEventConst.ACTION_CAN_UPGRADE_DATA_CMD_EVENT
import com.rv.framework.broadcast.BroadEventConst.ACTION_CAN_UPGRADE_DATA_CMD_EVENT2
import com.rv.framework.broadcast.BroadEventConst.ACTION_HBCP_EVT_HSHF_GET_STATUS
import com.rv.framework.broadcast.BroadEventConst.ACTION_HBCP_EVT_HSHF_STATUS
import com.rv.framework.broadcast.BroadEventConst.ACTION_LAUNCHER_KEY_CTRL
import com.rv.framework.broadcast.BroadEventConst.ACTION_MCU_CMD_EVENT
import com.rv.framework.broadcast.BroadEventConst.ACTION_MCU_MSG_BACKCAR_END_EVT
import com.rv.framework.broadcast.BroadEventConst.ACTION_MCU_MSG_BACKCAR_START_EVT
import com.rv.framework.broadcast.BroadEventConst.ACTION_VOICE_CTRL
import com.rv.framework.broadcast.BroadEventConst.BROADCAST_VALID_MODE_EVT
import com.rv.framework.broadcast.BroadEventConst.CAN_START_END_DATA
import com.rv.framework.broadcast.BroadEventConst.DREAM_ACTION
import com.rv.framework.broadcast.BroadEventConst.HBCP_EVT_AV_STATUS
import com.rv.framework.broadcast.BroadEventConst.HBCP_EVT_CONTACT_NUM
import com.rv.framework.broadcast.BroadEventConst.MCU_CMD_DATA
import com.rv.framework.broadcast.BroadEventConst.MCU_MSG_CAN_ALL_INFO
import com.rv.framework.broadcast.BroadEventConst.MCU_MSG_CAN_ATADATA
import com.rv.framework.broadcast.BroadEventConst.MCU_MSG_MAIL_VOL
import com.rv.framework.broadcast.BroadEventConst.SEND_KEY_ACTION
import com.rv.framework.broadcast.BroadEventConst.SEND_KEY_EXTRA
import com.rv.framework.broadcast.BroadEventConst.VALID_MODE_INFOR_CHANGE
import com.rv.framework.broadcast.BroadEventConst.VOLUME_SHOW
import com.rv.framework.broadcast.BroadEventConst.ZXW_RADIO_INFO_EVT
import com.rv.framework.broadcast.BroadEventConst.ZXW_SYS_KEY_EVT
import com.rv.framework.broadcast.ZxwMcuBroadcast
import com.rv.framework.cmd.base.AndroidDevice
import com.rv.framework.flowbus.event.StartBleServerEvent
import com.rv.framework.flowbus.postEvent
import com.rv.framework.utils.AppLogUtil
import com.rv.framework.utils.StringUtils
import com.rv.framework.utils.Utils

class ZxwSerialCommand : SerialCommand() {
    private val bd : ZxwMcuBroadcast by lazy {
        ZxwMcuBroadcast().apply {
            onDataReceivedCallBack={
                it?.let {
                    AppLogUtil.e("收到数据 ：" + StringUtils.bytes2HexString(it,it.size) + " --- " + isCanBusUpdate)
                    if(isCanBusUpdate.value == false) {
                        dataHandler?.onDataReceived(portStr, it, it.size)
                    }else{
                        //把数据直接给总线盒升级页面
                        //commonEventVM.canBusUpdateEvent.postValue(it)
                        dataHandler?.onCanBusUpdateDataReceived(portStr, it, it.size)
                    }
                }
            }
        }
    }
    private val intentFilter : IntentFilter by lazy {
        IntentFilter().apply {
            addAction(MCU_MSG_CAN_ALL_INFO)
            addAction(MCU_MSG_CAN_ATADATA)
            addAction(ACTION_MCU_CMD_EVENT)
            addAction(ACTION_MCU_MSG_BACKCAR_START_EVT)
            addAction(ACTION_MCU_MSG_BACKCAR_END_EVT)
            addAction(BROADCAST_VALID_MODE_EVT)
            addAction(VALID_MODE_INFOR_CHANGE)
            addAction(MCU_MSG_MAIL_VOL)
            addAction(ZXW_RADIO_INFO_EVT)
            addAction(HBCP_EVT_AV_STATUS)
            addAction(HBCP_EVT_CONTACT_NUM)
            addAction(ACTION_HBCP_EVT_HSHF_STATUS)
            addAction(ACTION_HBCP_EVT_HSHF_GET_STATUS)
            addAction(ACTION_LAUNCHER_KEY_CTRL)
            addAction(ACTION_VOICE_CTRL)
            addAction(DREAM_ACTION)

            addAction(ZXW_SYS_KEY_EVT)
            addAction(VOLUME_SHOW)
        }
    }
    private val broadcastIntent = Intent(ACTION_MCU_CMD_EVENT)
    private val canUpgradeBroad = Intent(ACTION_CAN_UPGRADE_DATA_CMD_EVENT)
    private val canUpgradeBroad2 = Intent(ACTION_CAN_UPGRADE_DATA_CMD_EVENT2)
    override fun sendCom2Port(data: ByteArray) {
        sendCom2Port(data,data.size)
    }

    override fun sendCom2Port(data: ByteArray, size: Int) {
        /*byteArrayOf(0x0d,0x08).plus(data).apply {
            val broadcastIntent = Intent(ACTION_MCU_CMD_EVENT)
            broadcastIntent.putExtra(MCU_CMD_DATA, this)
            Utils.getApp().sendBroadcastAsUser(broadcastIntent, Process.myUserHandle())
            AppLogUtil.e("ZxwSerialCommand: ${StringUtils.bytes2HexString(this,this.size)}")
        }*/
        byteArrayOf(0x30).plus(data).apply {
            val bd = if(isCanBusUpdate.value == true){
                canUpgradeBroad2
            }else{
                broadcastIntent
            }
            bd.putExtra(MCU_CMD_DATA, this)
            Utils.getApp().sendBroadcastAsUser(bd, Process.myUserHandle())
            //AppLogUtil.e("ZxwSerialCommand: ${StringUtils.bytes2HexString(this,this.size)}")
        }
    }
    override fun isCanBusUpdate(values : Boolean){
        super.isCanBusUpdate(values)
        val value = if(values){
            1
        }else{
            0
        }
        AppLogUtil.e("isCanBusUpdate:$value")
        canUpgradeBroad.putExtra(CAN_START_END_DATA, value)
        Utils.getApp().sendBroadcastAsUser(canUpgradeBroad, Process.myUserHandle())
    }

    override fun musicPrevious() {
        sendSquareCtrl(KeyCodeConst.MCU_KEY_PREV,1)
        //sendSquareCtrl(KeyCodeConst.MCU_KEY_PREV,0)
    }

    override fun musicNext() {
        sendSquareCtrl(KeyCodeConst.MCU_KEY_NEXT,1)
        //sendSquareCtrl(KeyCodeConst.MCU_KEY_NEXT,0)
    }

    override fun musicPlayPause() {
        sendSquareCtrl(KeyCodeConst.MCU_KEY_PLAYPAUSE,1)
        //sendSquareCtrl(KeyCodeConst.MCU_KEY_PLAYPAUSE,0)
    }

    override fun getAndroidDeviceType(): AndroidDevice {
        return AndroidDevice.AndroidZXW
    }

    @SuppressLint("WrongConstant")
    override fun start() : Int{
        ContextCompat.registerReceiver(
            Utils.getApp(),
            bd,
            intentFilter,
            ContextCompat.RECEIVER_EXPORTED
        )
        getSystemVolume(Utils.getApp()).apply {
            AppLogUtil.e("getSystemVolume: ${this.first} - ${this.second}")
        }
        postEvent(StartBleServerEvent(), 5000)
        return 0
    }



    private fun getSystemVolume(context: Context, streamType: Int = AudioManager.STREAM_MUSIC): Pair<Int, Int> {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val currentVolume = audioManager.getStreamVolume(streamType)
        val maxVolume = audioManager.getStreamMaxVolume(streamType)
        return Pair(currentVolume, maxVolume)
    }

    override fun exit() {
        Utils.getApp().unregisterReceiver(bd)
    }
    override fun sendSquareCtrl(keyValue: Int, action: Int) {
        AppLogUtil.e("发送按键值- keyValue：$keyValue - action: $action")
        Intent(SEND_KEY_ACTION).apply {
            putExtra(SEND_KEY_EXTRA, keyValue)
            Utils.getApp().sendBroadcast(this)
        }
    }
    override fun getSwcBtKeys(): Array<IntArray> {
        return arrayOf()
    }
}