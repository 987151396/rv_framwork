package com.rv.framework.broadcast

object BroadEventConst {
    //空调板数据串口广播
    const val MCU_MSG_CAN_ATADATA = "com.choiceway.eventcenter.EventUtils.onCmdMcuATAData";
    //接收 can 盒子发过来的数据：
    const val MCU_MSG_CAN_ALL_INFO = "com.choiceway.eventcenter.EventUtils.MCU_MSG_CAN_ALL_INFO" //协议盒串口广播
    const val CAR_AIR_DATA = "EventUtils.CAR_AIR_DATA" //byte[]数组
    //发送数据给 can 盒子
    const val ACTION_MCU_CMD_EVENT: String = "com.szchoiceway.eventcenter.EventUtils.ACTION_MCU_CMD_EVENT"
    const val MCU_CMD_DATA = "EventUtils.MCU_CMD_DATA" //byte[]数组
    //接收车机开始倒车广播：
    const val ACTION_MCU_MSG_BACKCAR_START_EVT: String = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BACKCAR_START"
    //接收车机结束倒车广播：
    const val ACTION_MCU_MSG_BACKCAR_END_EVT: String = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BACKCAR_END"
    //接收车机蓝牙连接和来去电状态
    const val ACTION_HBCP_EVT_HSHF_STATUS: String = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_STATUS"
    const val ACTION_HBCP_EVT_HSHF_GET_STATUS: String = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_GET_STATUS"
    const val INTENT_EXTRA_INT_KEYNAME = "com.szchoiceway.btsuite.DATA_INT"
    //接收车机蓝牙电话信息：
    const val HBCP_EVT_CONTACT_NUM: String = "com.szchoiceway.btsuite.HBCP_EVT_CONTACT_NUM"
    const val INTENT_EXTRA_STR_KEYNAME = "com.szchoiceway.btsuite.DATA_STR"
    //接收车机蓝牙音乐信息：
    const val HBCP_EVT_AV_STATUS = "com.szchoiceway.btsuite.HBCP_EVT_AV_STATUS"
    //发送方控和面板按键给车机：
    const val SEND_KEY_ACTION =  "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT"
    const val SEND_KEY_EXTRA =  "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA"
    //接收主机音量：
    const val MCU_MSG_MAIL_VOL: String = "com.choiceway.eventcenter.EventUtils.MCU_MSG_MAIL_VOL"
    const val MCU_MSG_MAIL_VOL_VAL = "com.choiceway.eventcenter.EventUtils.MCU_MSG_MAIL_VOL_VAL"
    const val MCU_MSG_SHOW_VOL_WND: String = "com.choiceway.eventcenter.EventUtils.MCU_MSG_SHOW_VOL_WND" //是否显示音量
    //发送的音量加减和静音按键广播：
    const val ZXW_SYS_KEY_EVT: String = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY"

    const val ZXW_ACTION_CUR_MIAN_VOL_EVT = "com.szchoiceway.eventcenter.ZXW_ACTION_CUR_MAIN_VOL_EVT_DATA"

    const val ZXW_VOL_EXTRA: String = "zxw_vol_extra" //extra
    //zxw_vol_extra 对应的参数 Volume value
    const val REQUEST_CUR_VOL: String = "RequestCurVol" //请求音量值
    const val ZXW_KEY_VOL_ADD: String = "KEY_VOL_ADD" //音量加按键
    const val ZXW_KEY_VOL_SUB: String = "KEY_VOL_SUB" //音量减按键
    const val ZXW_KEY_MUTE: String = "KEY_MUTE" //静音按键
    const val ZXW_VOLUME_VALUE = "zxw_volume_value";//发送音量值 int 型范围:0-40
    //点击状态栏上的音量图标发送的广播：
    const val VOLUME_SHOW = "android.intent.action.I_ACTION_CUSTOM_TYPE_CHANGED"
    const val VOLUME_SHOW_EXTRA = "CUSTOM_TYPE_CHANGED"
    //接收主机媒体信息：
    const val VALID_MODE_INFOR_CHANGE: String = "com.szchoiceway.eventcenter.EventUtils.VALID_MODE_INFOR_CHANGE"
    //接收主机源模式
    const val BROADCAST_VALID_MODE_EVT: String = "com.szchoiceway.eventcenter.EventUtils.BROADCAST_VALID_MODE_EVT"
    const val MODE = "MODE" //参数 int 型
    //接收主机收音机信息
    var ZXW_RADIO_INFO_EVT: String? = "com.choiceway.eventcenter.EventUtils.ZXW_RADIO_INFO_EVT"
    //屏幕旋转
    const val ACTION_LAUNCHER_KEY_CTRL = "com.szchoiceway.ACTION_LAUNCHER_KEY_CTRL"
    const val EXTRA_LAUNCHER_KEY_WORD = "LauncherKeyWord" //ScreenRotate
    //语音命令控制
    const val ACTION_VOICE_CTRL = "com.szchoiceway.ACTION_VOICE_CTRL"
    const val EXTRA_VOICE_KEY_WORD = "VoiceKeyWord"
    const val EXTRA_VOICE_PARAM = "VoiceParam"

    //升级开始和结束广播:
    const val ACTION_CAN_UPGRADE_DATA_CMD_EVENT = "com.szchoiceway.eventcenter.EventUtils.ACTION_CAN_UPGRADE_START_END_EVENT"
    const val CAN_START_END_DATA ="EventUtils.CAN_START_END_DATA" //int型 0结束升级，1进入升级
    //升级数据广播
    const val ACTION_CAN_UPGRADE_DATA_CMD_EVENT2 = "com.szchoiceway.eventcenter.EventUtils.ACTION_CAN_UPGRADE_DATA_CMD_EVENT"
    //屏保广播
    const val DREAM_ACTION: String = "com.szchoiceway.eventcenter.action.SCREEN_SAVER"
    const val DREAM_EXTRA_KEY: String = "screensaverEnable" // 是否显示屏保

    const val ACTION_SYS_PORTRAIT_LANDSCAPE_SCREEN_KEY: String =
        "com.szchoiceway.eventcenter.ACTION_SYS_PORTRAIT_LANDSCAPE_SCREEN_KEY" //设置系统横竖屏

    const val EXTRA_PORTRAIT_LANDSCAPE: String = "EXTRA_PORTRAIT_LANDSCAPE" //int型 0竖屏，1横屏

}
object BtPhoneState{
    const val HBCP_STATUS_HSHF_INITIALISING = 0x00
    const val HBCP_STATUS_HSHF_READY = 0x01
    const val HBCP_STATUS_HSHF_CONNECTING = 0x02
    const val HBCP_STATUS_HSHF_CONNECTED = 0x03
    const val HBCP_STATUS_HSHF_OUTGOING_CALL = 0x04
    const val HBCP_STATUS_HSHF_INCOMING_CALL = 0x05
    const val HBCP_STATUS_HSHF_ACTIVE_CALL = 0x06
}

object RadioInfoExtra{
    const val RadioBndNum = "RadioBndNum";//波段 参数 int 型
    const val RadioTuneNum = "RadioTuneNum";//当前电台号 参数 int 型
    const val RadioCurFreq = "RadioCurFreq";//当前电台频率 参数 int 型 高 8 位在前，低 8 位在后，FM 单位 0.01，AM 单位 1。
}

object MediaInfoExtra{
    const val Title = "Title" //参数 String 型
    const val Album = "Ablum" //参数 String 型
    const val Artist = "Artist" //参数 String 型
    const val CurTrack = "CurTrack" //参数 int 型
    const val TotTrack = "TotTrack" //参数 int 型
    const val CurFolder = "CurFolder" //参数 int 型
    const val TotFolder = "TotFolder" //参数 int 型
    const val CurTime = "CurTime" //参数 int 型
    const val TotTime = "TotTime" //参数 int 型
    const val LoopMode = "LoopMode" //参数 int
    const val RepeatMode = "RepeatMode" //参数 int 型
    const val PlayStatus = "PlayStatus" //参数 int 型

    fun getSecFromPosition(Time: Int): Int {
        var iSecond = 0
        if (Time / 60 / 60 / 1000 > 0) {
            val s = Time / 1000 % 60
            val m = Time / (1000 * 60) % 60
            val h = Time / (1000 * 60 * 60) % 24
        } else if (Time / 60 / 1000 > 0) {
            val s = Time / 1000 % 60
            val m = Time / (1000 * 60) % 60
        } else {
            val s = Time / 1000 % 60
            iSecond = s
        }
        return iSecond
    }

}