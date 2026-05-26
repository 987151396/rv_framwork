/**
 * ��Ȩ�������������Ƽ����޹�˾
 * ���:	 �»���
 * ���룺�������з���/Android��
 * ���ڣ�2015��1��1��
 */

package com.rv.framework.fyt.ipc.data;

public class FinalSound {
    public static final int MODULE_2313 = 8;
    // cmd(C_VOL, VOL_SHOW_UI)
    public static final int C_VOL						= 0;	// 声音控制
    public static final int C_EQ_GAIN					= 1;
    public static final int C_EQ_MODE					= 2;
    public static final int C_BAL_FADE					= 3;
    public static final int C_FIELD_MODE				= 4;
    public static final int C_LOUD						= 5;
    public static final int C_SB						= 6;
    // PARAM new int[]{0/1/2}
    public static final int C_AMP						= 7;
    // PARAM new int[]{0/1/2}
    public static final int C_BEEP						= 8;
    public static final int C_NAVI_MIX					= 9;
    public static final int C_NON_NAVI_MIX_PERCENT		= 10;	// 非导航应用混音比例 [0~20]
    public static final int C_NAVI_MIX_PERCENT			= 11;
    public static final int C_DEF_VOL_ON_BOOT			= 12;	// 开机默认音量开关
    public static final int C_BACKCAR_MUTE				= 13;	// 倒车静音
    public static final int C_DEF_VOL					= 14;	// 默认音量值
    // 数字功放下有用 PARAM new int[]{index, cf} cf为频率 20~20000
    public static final int C_EQ_CF						= 15;
    public static final int C_EQ_Q						= 16;
    public static final int C_SRS_CF					= 17;
    // PARAM new int[]{index, value}
    public static final int C_SRS_GAIN					= 18;
    // PARAM new int[]{1:播放器静音 1~2秒恢复 0:100ms恢复静音}
    public static final int C_STREAM_PLAYER_LOSE		= 19;
    // 倒车降音比例//(0~20)
    public static final int C_BACKCAR_PERCENT			= 20;
    public static final int C_SPECTRUM_ENABLE			= 21;
    // TTS播报状态 1：开始播报。1s发一次  0：结束播报，
    public static final int C_TIPS_TTS_AUDIO			= 22;
    // subwoof控制(0~10)
    public static final int C_SUBWOOF_GAIN				= 23;

    public static final int OUTPUT_VALUME               = 53;

    public static final int OUTPUT_BTN_VALUME               = 77;
    public static final int OUTPUT_FRCY_CHANNEL               = 75;
    public static final int OUTPUT_FRCY_STYLE               = 76;
    public static final int OUTPUT_FAN_BTN               = 78;

    public static final int OUTPUT_FRCY_STYLE_7738A13 = 83;
    public static final int OUTPUT_FRCY_VALUE_7738A13 = 84;



    /**
     *  以下为扩展参数，根据不同音频IC功能而已选择使用，不同芯片并且参数有可能不一致，
     *  功能相似尽量概念复用，减少常量定义
     */
    private static final int C_EXPEND = 24;
    public static final int C_SETUP = C_EXPEND;
    public static final int C_SUBWOOF_FREQ				= C_EXPEND + 1;

    public static final int C_P2BASS_ON				= C_EXPEND + 2;
    public static final int C_P2BASS_GAIN				= C_EXPEND + 3;
    public static final int C_P2BASS_FREQ				= C_EXPEND + 4;
    //
    public static final int C_HPF_FREQ				= C_EXPEND + 5;
    public static final int C_SURROUND_ON				= C_EXPEND + 6;
    public static final int C_SURROUND_MODE				= C_EXPEND + 7;
    public static final int C_SURROUND_LEVEL				= C_EXPEND + 8;
    public static final int C_SURROUND_GAIN				= C_EXPEND + 9;
    public static final int C_P2BASS			= C_EXPEND + 10;
    public static final int C_EQ_TYPE			= C_EXPEND + 11;
    public static final int C_SUBWOOF_ON			= C_EXPEND + 12;

    public static final int C_RANG			= C_EXPEND + 13; // 音域控制
    public static final int C_RANG_LEVEL			= C_EXPEND + 14; // 音域控制
    public static final int C_DYNAMIC			= C_EXPEND + 15; //动感音效
    public static final int C_HIGH_PITCH	= C_EXPEND + 16; //高音
    public static final int C_HPF_GAIN				= C_EXPEND + 17;
    public static final int FILTER_METHOD			=  56;
    public static final int STEREO_OPTION				= 63;//立体音控制操作， int[]{index, value}
    public static final int STEREO_SWITCH			=  64;//立体音控制开关 int[]{on/off}
    public static final int SOUND_3D				=  65;//3d音效
    public static final int VOCALS					=  66;//人声效果设置 int[]{mode, value}  mode: (DU561  0 - 人声消除，1 - 干声，2 - 湿声, 3 - 截止频率 300 - 10000Hz)
    public static final int CS_AUTO					=  68;//DTS
    public static final int DLY_MIXER					=  69;//扩展
    public static final int DLY_FIELD					=  70;//
    public static final int DLY_FIELD_MODE					=  71;//
    public static final int EQ_CUSTOMIZED			=  72;// EQ数据被用户调整,可以保存自定义数据   new int[]{ 0 / 1}
    public static final int SAVE_EQ_CUSTOMIZED		=  73;// 指定自定义模式保存EQ数据(只能保存自定义模式)   new int[]{ custome_mode }


    public static final int STREAM_MASTER				= 0;
    public static final int STREAM_PLAYER				= 1;
    public static final int U_SPECTRUM					= 0;	// 频谱数据
    public static final int U_MODULE_ID					= 1;	// 模块ID
    // PARAM new int[]{value / value, op}
    public static final int U_VOL						= 2;	// 音量
    public static final int U_MUTE						= 3;	// 静音开关
    public static final int U_BEEP						= 4;	// 按键音开关
    public static final int U_DEF_VOL_ON_BOOT			= 5;	// 开机默认音量
    public static final int U_DEF_VOL					= 6;	// 默认音量
    public static final int U_BACKCAR_MUTE				= 7;	// 倒车静音
    public static final int U_BAL_FADE					= 8;	// 声场
    public static final int U_EQ_GAIN					= 9;	// EQ频点 index, value
    public static final int U_EQ_MODE					= 10;	// EQ模式

    public static final int U_LOUD						= 11;	// 等响度
    public static final int U_SB						= 12;	// 音量平衡
    public static final int U_AMP						= 13;	// 功放开关
    public static final int U_NAVI_MIX					= 14;	//
    public static final int U_NON_NAVI_MIX_PERCENT		= 15;
    public static final int U_NAVI_MIX_PERCENT			= 16;
    // 当使用了原车的EQ, EQ界面可能需要发生变化  (之所以是USE_CAR_EQ,因为CANBUS可能有CAR_EQ,但是客户不需要)
    public static final int U_USE_CAR_EQ				= 17;
    public static final int U_EQ_CF						= 18;
    // 同U_USE_CAR_EQ,但是需要注意优先级 USE_CAR_EQ>U_USE_AMP_EQ>3702/8288等EQ
    public static final int U_USE_AMP_EQ				= 19;
    public static final int U_EQ_Q						= 20;
    public static final int U_SRS_CF					= 21;
    public static final int U_SRS_GAIN					= 22;
    // 倒车降音比例
    public static final int U_BACKCAR_PERCENT			= 23;	//(0~10)
    public static final int U_SPECTRUM_ENABLE			= 24;
    public static final int U_IS_VOLUI_SHOW				= 25;

    /**
     *  以下为扩展参数，根据不同音频IC功能而已选择使用，不同芯片并且参数有可能不一致，
     *  功能相似尽量概念复用，减少常量定义
     */
    private static final int U_EXPEND = 26;
    public static final int U_SUBWOOF_FREQ				= U_EXPEND + 1;

    public static final int U_P2BASS_ON				= U_EXPEND + 2;
    public static final int U_P2BASS_GAIN				= U_EXPEND + 3;
    public static final int U_P2BASS_FREQ				= U_EXPEND + 4;
    //
    public static final int U_HPF_FREQ				= U_EXPEND + 5;
    public static final int U_SURROUND_ON				= U_EXPEND + 6;
    public static final int U_SURROUND_MODE				= U_EXPEND + 7;
    public static final int U_SURROUND_LEVEL				= U_EXPEND + 8;
    public static final int U_SURROUND_GAIN			= U_EXPEND + 9;
    public static final int U_P2BASS			= U_EXPEND + 10;
    public static final int U_EQ_TYPE			= U_EXPEND + 11;
    public static final int U_SUBWOOF_ON			= U_EXPEND + 12;

    public static final int U_RANG			= U_EXPEND + 13; // 音域控制
    public static final int U_RANG_LEVEL			= U_EXPEND + 14; // 音域控制
    public static final int U_DYNAMIC			= U_EXPEND + 15; //动感音效
    public static final int U_HIGH_PITCH	= U_EXPEND + 16; //高音
    public static final int U_HPF_GAIN				= U_EXPEND + 17;


    // subwoof控制(0~10)
    public static final int U_SUBWOOF_GAIN				= 26;
    public static final int U_CNT_MAX					= 50;

    public static final int FIELD_BAL					= 0;
    public static final int FIELD_FADE					= 1;


    public static final int EQ_BAND_BASS				= 0;//低音
    public static final int EQ_BAND_SENOR				= 1;//中音
    public static final int EQ_BAND_TREBLE				= 2;//高音
    public static final int EQ_BAND_CNT_MAX				= 36;

    public static final int EQ_MODE_CUSTOM				= 0;	// 用户
    public static final int EQ_MODE_STANDARD			= 1;	// 标准
    public static final int EQ_MODE_ROCK				= 2;	// 摇滚
    public static final int EQ_MODE_SOFT				= 3;	// 柔和
    public static final int EQ_MODE_CLASSIC				= 4;	// 古典
    public static final int EQ_MODE_POP					= 5;	// 流行
    public static final int EQ_MODE_HALL				= 6;	// 大厅
    public static final int EQ_MODE_JAZZ				= 7;	// 爵士
    public static final int EQ_MODE_CINEMA				= 8;	// 剧院
    public static final int SRS_BAND_CNT_MAX			= 36;

    public static final int SRS_MODE_CUSTOM				= 0;	// 用户

    // FIELD_MODE只发命令,不更新,UI的按钮不做高亮(15/01/23阿东)
    public static final int FIELD_MODE_DRIVER			= 0;
    public static final int FIELD_MODE_FRONT			= 1;
    public static final int FIELD_MODE_ALL				= 2;
    public static final int FIELD_MODE_REAR				= 3;

    // 音量控制
    public static final int VOL_UP						= -1;
    public static final int VOL_DOWN					= -2;
    public static final int VOL_MUTE					= -3;
    public static final int VOL_UNMUTE					= -4;	// 非静音
    public static final int VOL_MUTE_SWITCH				= -5;
    public static final int VOL_SHOW_UI					= -6;
    public static final int VOL_HIDE_UI					= -7;

    public static final int SB_RADIO					= 0;
    public static final int SB_DVD						= 1;
    public static final int SB_BTPHONE					= 2;
    public static final int SB_BTAV						= 3;
    public static final int SB_IPOD						= 4;
    public static final int SB_AUX						= 5;
    public static final int SB_PLAYER					= 6;
    public static final int SB_NAVI						= 7;
    public static final int SB_TV						= 8;
    public static final int SB_CAR_BTPHONE				= 9;
    public static final int SB_CAR_USB					= 10;
    public static final int SB_CAR_RADIO				= 11;
    public static final int SB_CNT_MAX					= 48;




    public static final int DTS_CENTER_REAR					= 0;
    public static final int DTS_FRONT_REAR						= DTS_CENTER_REAR+1;

    public static final int DTS_FOCUS_CENTER					= DTS_FRONT_REAR+1;
    public static final int DTS_FOCUS_FRONT						= DTS_FOCUS_CENTER+1;
    public static final int DTS_FOCUS_REAR						= DTS_FOCUS_FRONT+1;
    public static final int DTS_FOCUS_CENTERSW					= DTS_FOCUS_REAR+1;
    public static final int DTS_FOCUS_FRONTSW					= DTS_FOCUS_CENTERSW+1;
    public static final int DTS_FOCUS_REARSW						= DTS_FOCUS_FRONTSW+1;

    public static final int DTS_TRUBASS_REAR						= DTS_FOCUS_REARSW+1;
    public static final int DTS_TRUBASS_FRONT					= DTS_TRUBASS_REAR+1;
    public static final int DTS_TRUBASS_CENTER						= DTS_TRUBASS_FRONT+1;
    public static final int DTS_TRUBASS_CENTERSW						= DTS_TRUBASS_CENTER+1;
    public static final int DTS_TRUBASS_FRONTSW					= DTS_TRUBASS_CENTERSW+1;
    public static final int DTS_TRUBASS_REARSW						= DTS_TRUBASS_FRONTSW+1;

    public static final int DTS_AUTO_PASS						= DTS_TRUBASS_REARSW+1;
    public static final int DTS_MODE_SELECT						= DTS_AUTO_PASS+1;
    public static final int DTS_FULL_BAND				= DTS_MODE_SELECT+1;
    public static final int DTS_PHANTOM					= DTS_FULL_BAND+1;


    public static final int DTS_TRUBASS_CENTERSIZE						= DTS_PHANTOM+1;
    public static final int DTS_TRUBASS_FRONTSIZE				= DTS_TRUBASS_CENTERSIZE+1;
    public static final int DTS_TRUBASS_REARSIZE						= DTS_TRUBASS_FRONTSIZE+1;

    public class Func {
        public static final int ALL = 0;
        public static final int EQ = 1;
        public static final int FIELD = 2;
        public static final int SURROUND = 3;
        public static final int BASS = 4;
        public static final int HIGHPITCH = 5;
        public static final int STEREO = 6;
        public static final int VOCALS = 7;
        public static final int CSAUTO = 8;
        public static final int DLYMIX = 9;
    }

    public class Face {
        public static final int ALL = 0x01 << 30;
        public static final int FRONT = 0x01 << 16;
        public static final int REAR = 0x02 << 16;
        public static final int LEFT = 0x01 << 12;
        public static final int RIGHT = 0x02 << 12;
    }

    public class Stereo {
        public static final int TG = 0;
        public static final int LG = 1;
        public static final int HG = 2;
        public static final int MG = 3;
        public static final int FC = 4;
    }
    public final class FilterMethod {
        public static final int BUTTERWORTH 			= 0; //巴特沃斯
        public static final int CHEBYSHEVS 				= 1; //切比雪夫
        public static final int BEZIER 					= 2; //贝塞尔
        public static final int NINGKE 					= 3; //宁克
    }
    public class Index {
        public static final int L = 0 << 8;
        public static final int H = 1 << 8;

        public static final int A = 1 << 4;
        public static final int B = 2 << 4;
        public static final int C = 3 << 4;
        public static final int D = 4 << 4;
        public static final int E = 5 << 4;
        public static final int F = 6 << 4;

        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FOUR = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
    }
}
