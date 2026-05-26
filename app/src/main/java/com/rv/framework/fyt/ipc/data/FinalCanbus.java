/**
 * 版权：深圳深青联科技有限公司
 * 设计:	 柯华栋
 * 代码：深青联研发部/Android组
 * 日期：2015年1月1日
 */

package com.rv.framework.fyt.ipc.data;

public class FinalCanbus {
	// 注意所有CMD_CODE都在同一个区域内，不要越界
	public static final int C_MISC_BEGIN					= 1000;
	public static final int C_CANBUS_ID						= C_MISC_BEGIN+0;	// (car_id<<16|canbus_id)
	public static final int C_DRIVER_ON_RIGHT				= C_MISC_BEGIN+1;	// 司机在右边
	// 显示空调弹窗(主要用于屏幕按钮) PARAM new int[]{0:临时显示,n秒后自动消失} 
	public static final int C_SHOW_AIR_WINDOW				= C_MISC_BEGIN+2;
	// PARAM new int[]{0}
	public static final int C_CHANGE_PANORAMA				= C_MISC_BEGIN+3;	// 切换全景摄像头
	// PARAM new int[]{0:临时显示,n秒后自动消失}
	public static final int C_SHOW_DOOR_WINDOW				= C_MISC_BEGIN+4;	// 显示车门弹窗
	public static final int C_CAMERA_MODE					= C_MISC_BEGIN+5;	// 摄像头模式
	//= 00：广角 	= 01：标准	= 02：俯角 	= 03： 全广角
//	public static final int C_SET_PARKTRACK					= C_MISC_BEGIN+6;	// 摄像头模
//	public static final int C_SET_REMINDER					= C_MISC_BEGIN+7;	//泊车轨迹模式
	
//-------------------------------------------------------------------------------------------------
	// 注意所有GET_CODE都在同一个区域内，不要越界
	public static final int G_MISC_BEGIN					= 1000;
	// PARAM new int[]{index}
	public static final int G_MCU_CANBUS_SUPPORT			= G_MISC_BEGIN+0;	// 获取MCU支持的CANBUS
	
//-------------------------------------------------------------------------------------------------
	// 注意所有UPDATE_CODE都在同一个区域内，不要越界
	public static final int U_MISC_BEGIN					= 1000;
	public static final int U_CANBUS_ID						= U_MISC_BEGIN+0;
	// 空调窗口使能 (有些人不想被动弹出空调窗口,此处为一选项)
	public static final int U_AIR_WINDOW_ENABLE				= U_MISC_BEGIN+1;	// AIR弹窗使能
	// 车门窗口使能(有些人不想被动探出车门窗口,此处为一选项)
	public static final int U_DOOR_WINDOW_ENABLE			= U_MISC_BEGIN+2;	// DOOR弹窗使能
	public static final int U_DRIVER_ON_RIGHT				= U_MISC_BEGIN+3;	// 司机是否在右边
	public static final int U_EXIST_DOOR					= U_MISC_BEGIN+4;	// 是否有车门
	// CANBUS版本号 PARAM:new String[]{VER}
	public static final int U_CANBUS_VER					= U_MISC_BEGIN+5;	// CANBUS版本信息
	public static final int U_TIP_ID						= U_MISC_BEGIN+6;	// 提示(主要是提示当前协议不支持等信息)
	public static final int U_EXIST_AIR						= U_MISC_BEGIN+7;	// 是否有空调
	public static final int U_SHOW_AIR_WINDOW				= U_MISC_BEGIN+8;	// 显示空调(来自屏幕按钮的调用)
	public static final int U_EXIST_PANORAMA				= U_MISC_BEGIN+9;	// 是否有全景摄像头
	public static final int U_MCU_CANBUS_SUPPORT_CNT		= U_MISC_BEGIN+10;	// MCU支持的CANBUS总数
	public static final int U_SHOW_DOOR_WINDOW				= U_MISC_BEGIN+11;	// 显示车门(来自屏幕按钮的调用)
	public static final int U_EXIST_TEMP_OUT				= U_MISC_BEGIN+12;	// 是否存在外温
	public static final int U_CAMERA_MODE					= U_MISC_BEGIN+13;	// 摄像头模式
	public static final int U_AIR_CONTROL_PAGE				= U_MISC_BEGIN+14;	// 空调控制界面
	public static final int U_CAR_BT_ON						= U_MISC_BEGIN+15;	// 原车蓝牙状态，MCU返回的
	public static final int U_EXIST_CAR_RADIO				= U_MISC_BEGIN+16;	// 是否存在原车收音机
	public static final int U_RIGHT_CAMERA_ON_OFF			= U_MISC_BEGIN+17;	// 原车右视频打开
	public static final int U_EXIST_AIR_CONTROL				= U_MISC_BEGIN+18;	// 是否有空调控制，浮动按钮、主界面根据这个显示小风扇
	
	//= 00： 广角 
	//= 01： 标准
	//= 02： 俯角
	//= 03： 全广角
	public static final int U_CNT_MAX						= 1200;
	
//-------------------------------------------------------------------------------------------------
	public static final int TIP_CANBUS_UNSUPPORT			= 0;
	
	public static final int MCU_CANBUS_SUPPORT_CNT_MAX		= 1000;
	
//-------------------------------------------------------------------------------------------------
	// 无
	public static final int CAR_NULL_Null					= 0;
	// 威驰 大众 (欧系 德国)
	public static final int CAR_WC1_DaZhong 				= 1; 
	// 欣朴 大众 (欧系 德国)
	public static final int CAR_XP1_DaZhong 				= 2; 
	// 威驰 标志408 (欧系 法国)
	public static final int CAR_WC2_Psa408 					= 3; 
	// 方易通 东南V5 (国产)
	public static final int CAR_FYT1_DongNanV5 				= 4; 
	public static final int CAR_FYT1_DongNanV5_H 			= 4; // 方易通 东南V5 (国产)
	// 方易通 福美来 (国产)
	public static final int CAR_FYT2_FuMeiLai 				= 5; 
	// 比亚迪F3 马3 马5... (比亚迪 国产)
	public static final int CAR_RZC1_BydF3 					= 6; 
	// 比亚迪F6 (比亚迪 国产)
	public static final int CAR_RZC2_BydF6 					= 7; 
	// 威驰 一汽奔腾X80 (国产)
	public static final int CAR_WC1_BenTengX80 				= 8; 
	// 威驰:奔腾B50/B70 /GA3 (国产)
	public static final int CAR_WC1_BenTengB50 				= 9; 
	// 威驰 菲亚特-菲翔 (欧系 意大利)
	public static final int CAR_WC1_FeiyateFeixiang 		= 10; 
	// 威驰 标志 301 (欧系 法国)
	public static final int CAR_WC2_Biaozhi301 				= 11; 
	// 威驰 雪铁龙C4L (欧系 法国)
	public static final int CAR_WC2_XueTieLongC4L 			= 12; 
	// 威驰 2013新君威 (美系 美国)
	public static final int CAR_WC2_XinJunWei2013 			= 13; 
	//威驰 2013新君威 (美系 美国)高配
	public static final int CAR_WC2_AngkeWei				= (2<<16)|13;
	// 威驰 福克斯 (美系 美国)
	public static final int CAR_WC2_Focus 					= 14; 
	// 欣朴 现代IX35 (日韩系 韩国)
	public static final int CAR_XP2_XiandaiIX35 			= 15; 
	// 欣朴 马自达 (日产)
	public static final int CAR_XP2_Mazida 					= 16; 
	// 威驰 高尔夫7 (欧系 德国)
	public static final int CAR_WC2_GaoErFu7 				= 17; 
	// 睿智诚 13款奔腾B50 (国产)
	public static final int CAR_RZC1_BenTengB50_13 			= 18; 
	// 欣朴 凌派 杰德 (日韩系 日本)
	public static final int CAR_XP1_JieDe 					= 19; 
	// 欣朴 2013款凯美瑞 RAV4 (日韩系 日本)
	public static final int CAR_XP1_Camry2013 				= 20; 
	public static final int CAR_XP1_Camry2013_H 			= (2<<16)|20;
	// 威驰 蒙迪欧致胜2013 (美系 美国)
	public static final int CAR_WC2_MengDiOuZS2013 			= 21; 
	public static final int CAR_WC2_MengDiOuZS2013_H 	= (2<<16)|21; // 威驰 蒙迪欧致胜2013 (美系 美国)
	// 欣朴 福克斯2012 (美系 美国)
	public static final int CAR_XP1_Focus2012 				= 22; 
	// 威驰 标志3008 (欧系 法国)
	public static final int CAR_WC2_BiaoZhiPSA3008 			= 23; 
	// 欣朴 思域 (日韩系 日本)
	public static final int CAR_XP1_SIYU2012 				= 24; 
	// 欣朴 通用 高配 (美系 美国)
	public static final int CAR_XP1_GM_H 					= 25; 
	// 欣朴 通用 低配 (美系 美国)
	public static final int CAR_XP1_GM_L 					= 26; 
	// 欣朴 天籁2013 (日韩系 日本)
	public static final int CAR_XP1_TIANLAI2013 			= 27; 
	// 方易通 吉利全球鹰 ( 国产)
	public static final int CAR_FYT1_JILIGC7 				= 28; 
	// 威驰 标志308 (欧系 法国)
	public static final int CAR_WC1_BiaoZhi308 				= 29; 
	// 威驰 标志508 (欧系 法国)
	public static final int CAR_WC2_Biaozhi508 				= 30; 
	public static final int CAR_WC2_Biaozhi508_11_H 	= (1<<16)|30; // 威驰 标志508高配 (欧系 法国)	11款高配
	public static final int CAR_WC2_Biaozhi508_15 		= (2<<16)|30; // 威驰 标志508高配 (欧系 法国)	15款低配
	public static final int CAR_WC2_Biaozhi508_15_H 	= (3<<16)|30; // 威驰 标志508高配 (欧系 法国)	15款高配
	// 欣朴 现代IX45/IX35 (日韩系 韩国)
	public static final int CAR_XP1_XiandaiIX45_L 			= 31; 
	public static final int CAR_XP1_XiandaiIX45_M 			= (1<<16)|31; 
	public static final int CAR_XP1_XiandaiIX45_H 			= (2<<16)|31;
	// 威驰 B90 (国产)
	public static final int CAR_WC1_BenTengB90 				= 32; 
	// 威驰 新锐志2013 (日韩系 日本)
	public static final int CAR_WC2_XinRuiZhi2013 			= 33; 
	// 威驰 力帆720 ( 国产)
	public static final int CAR_WC1_Lifan720 				= 34; 
	public static final int CAR_WC1_Lifan720_M 			= (1<<16)|34; // 威驰 力帆720 ( 国产)
	public static final int CAR_WC1_Lifan720_H 			= (2<<16)|34; // 威驰 力帆720 ( 国产)
	// 睿智诚 力帆720 ( 国产)
	public static final int CAR_RZCexc_Lifan720 			= 35; 
	// 威驰 通用系列 (美系 美国)
	public static final int CAR_WC2_GM 						= 36; 
	// 威驰 雅阁9 (日韩系 日本)
	public static final int CAR_WC2_Accord9 				= 37; 
	public static final int CAR_WC2_Accord9_H				= (2<<16)|37;
	// 威驰 天籁 (日韩系 日本)
	public static final int CAR_WC1_TianLai2013 			= 38; 
	// 睿智诚 菲翔 (欧系 意大利)
	public static final int CAR_RZCexc_FeiyateFeixiang 		= 39; 
	// 欣朴 高尔夫7 (欧系 德国)
	public static final int CAR_XP1_GaoErFu7 				= 40; 
	// 欣朴 雅阁9（低配） (日韩系 日本)
	public static final int CAR_XP1_ACCORD9_Lo 				= 41; 
	// 威驰 雅阁9（低配） (日韩系 日本)
	public static final int CAR_WC2_ACCORD9_Lo 				= 42; 
	// 方易通 索航菲翔 (欧系 意大利)
	public static final int CAR_WC1_SOHONG_FEIXIANG 		= 43; 
	// 欣朴 高尔夫低配 (欧系 德国)
	public static final int CAR_XP1_GaoErFu7_Lo 			= 44; 
	// 威驰 高尔夫低配 (欧系 德国)
	public static final int CAR_WC2_GaoErFu7_Lo 			= 45; 
	// 威驰 RAV4 (日韩系 日本)
	public static final int CAR_WC2_RAV4_2013 				= 46; 
	// 欣朴 CRV 2012 (日韩系 日本)
	public static final int CAR_XP1_CRV2012 				= 47; 
	// 欣朴 翼虎2012 (美系 美国)
	public static final int CAR_XP1_Yihu2012 				= 48; 
	// 威驰 翼虎 (美系 美国)
	public static final int CAR_WC2_YiHu 					= 49; 
	// 方易通-爱影 凯迪拉克（美系 美国）
	public static final int CAR_AY1_KaiDiLaKe 				= 50; 
	// 睿智诚（仿欣朴） 大众（欧系 德国）
	public static final int CAR_RZC_DaZhong 				= 51; 
	public static final int CAR_RZC_DaZhong_H 			= (2<<16)|51; // 睿智诚（仿欣朴） 大众（欧系 德国）
	// 睿智诚 自己的大众协议 （欧系 德国）
	public static final int CAR_RZC4_DaZhong 				= 52;
	// 方易通 风神A60
	public static final int CAR_FYT1_FengShengA60 			= 53;
	// 方易通 别克凯越
	public static final int CAR_FYT1_BieKeKaiYue 			= 54;
	// 威驰1 现代IX45
	public static final int CAR_WC1_XianDaiIX45 			= 55; 
	public static final int CAR_WC1_XianDaiIX45_H 		= (2<<16)|55; // 威驰1 现代IX45高配
	// 威驰2 雪铁龙2008 //2014.4.23 TLM
	public static final int CAR_WC2_XueTieLong2008 			= 56;
	// 索航 找来的思博 雪佛兰创库
	public static final int CAR_SH_XFLChuangKu 				= 57;
	// 鑫巴斯(仿欣朴)海马8 // 20140504 // Add by YangChao
	public static final int CAR_XBS_XP1_HaiMaM8 			= 58; 
	// 威驰雅阁低配
	public static final int CAR_WC2_ACCORD9_Lo_ZYC785 		= 59; 
	// 鑫巴斯（仿欣朴1）蒙迪欧
	public static final int CAR_XBS_XP1_MengDiOu 			= 60; 
	// 睿智诚（仿欣朴1）通用系列
	public static final int CAR_RZC_XP1_GM_H 				= 61; 
	public static final int CAR_RZC_XP1_GM_1 				= 1<<16|61; 
	public static final int CAR_RZC_XP1_GM_2 				= 2<<16|61; 
	public static final int CAR_RZC_XP1_GM_3 				= 3<<16|61;
	// 睿智诚 c4l
	public static final int CAR_Index_RZC4_C4L 				= 62; 
	// 欣朴14款霸道
	public static final int CAR_XP1_14Prado_L 				= (0<<16)|63; 
	public static final int CAR_XP1_14Prado_M 				= (1<<16)|63;
	public static final int CAR_XP1_14Prado_H 				= (2<<16)|63;
	// 日系威驰凌派
	public static final int CAR_WC2_LingPai 				= 64; 
	// 日系威驰杰德
	public static final int CAR_WC2_JieDe 					= 65; 
	// 日系威驰名图
	public static final int CAR_WC1_MingTu_L 				= 66; 
	public static final int CAR_WC1_MingTu_M 				= (1<<16)|66; 
	public static final int CAR_WC1_MingTu_H 				= (2<<16)|66;
	// 日系威驰思域
	public static final int CAR_WC3_SiYu 					= 67; 
	// 欣朴1通迅格式的睿智诚荣威550
	public static final int CAR_RZC_XP1_ROEWE550 			= 68; 
	// 欣朴1通迅格式的睿智诚长安奔奔MINI
	public static final int CAR_RZC_XP1_CHANGANBENBEN_MINI	= 69;
	// 欣朴1通迅格式的睿智诚长安CS75
	public static final int CAR_RZC_XP1_CHANGANCS75 		= 70; 
	// 欣朴1通迅格式的睿智诚海马M8
	public static final int CAR_RZC_XP1_HAIMAM8 			= 71; 
	// 老款荣威550，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_RongWei550 			= 72; 
	// 预留:公司自调的君越2014
	public static final int CAR_FYT0_JunYue2014 			= 73; 
	// 老款奔腾B50，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_BenTengB50 			= 74; 
	// 韩系威驰索纳塔IX35
	public static final int CAR_WC2_SoNaTa 					= 75; 
	// 日系威驰CRV
	public static final int CAR_WC3_CRV 					= 76; 
	// 日系雅阁9 高配
	public static final int CAR_XP1_ACCORD9_H 				= 77; 
	// 洞见日系艾力绅
	public static final int CAR_DJ_ELYSION 					= 78; 
	// 睿智成马自达3/5
	public static final int CAR_RZC1_MZD3_5 				= 79; 
	// 睿智成日产奇骏低配
	public static final int CAR_RZC_XP1_QiJun_L 			= 80; 
	public static final int CAR_RZC_XP1_QiJun_H 			= (2 << 16)|80;
	// 欣朴1通信格式的方易通海马S7协议
	public static final int CAR_FYT_XP1_HaiMaS7 			= 81; 
	// 海马M3协议，兼容最新款的2014款，新旧款协议格式不一样，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_HAIMAM3 				= 82; 
	// 奇瑞A3协议，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_QIRUIA3 				= 83; 
	// 14款B50 ，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_BenTeng14B50 			= 84; 
	// 欣朴1通迅格式的睿智诚威旺M20协议
	public static final int CAR_RZC_XP1_WEIWANGM20 			= 85; 
	// 威驰2通迅格式的奔腾B70 全拆方案
	public static final int CAR_WC2_BENGTENGB70_QUANCHAI	= 86; 
	// 威驰1通迅格的长丰猎豹协议
	public static final int CAR_WC1_CHANGFENG_LIEBAO 		= 87; 
	// 欣朴1通迅格式的睿智诚14款B70
	public static final int CAR_RZC_XP1_BenTeng14B70 		= 88; 
	// 睿智诚2通迅格式的睿智态X80协议
	public static final int CAR_RZC2_BenTengX80 			= 89; 
	// 威驰 DS5
	public static final int CAR_WC2_DS5 					= 90; 
	// 爱影 奥迪Q3		2014.05.07
	public static final int CAR_AY1_AUDIQ3 					= 91; 
	// 雪铁龙 DS5LS 2014.6.7
	public static final int CAR_WC2_DS5LS 					= 92; 
	// 欣朴1通信格式的和驰大切诺基协议2014.06.09
	public static final int CAR_HC_XP1_GrdCherokee 			= 93; 
	// 威驰3通迅格式的威驰荣威550协议，13 款和14款兼容，方控按键不同款式不同，工厂设置可选
	public static final int CAR_WC1_ROEWE550 				= 94; 
	public static final int CAR_WC1_ROEWE550_2014			= (1 << 16) | 94;
	// 睿智诚1通迅格式的睿智诚奇瑞A3协议
	public static final int CAR_RZC1_QiRuiA3 				= 95; 
	// 睿智诚1通迅格式的睿智诚福美来协议
	public static final int CAR_RZC1_FML 					= 96; 
	// 睿智诚1通迅格式的睿智诚海马M3协议
	public static final int CAR_RZC1_HaiMaM3 				= 97; 
	// 睿智诚1通迅格式的睿智诚海马S7协议
	public static final int CAR_RZC1_HaiMaS7 				= 98; 
	// 睿智诚1通迅格式的睿智诚全球鹰GC7协议
	public static final int CAR_RZC1_GC7 					= 99; 
	// 睿智诚1通迅格式的睿智诚传琪GA3协议
	public static final int CAR_RZC1_GA3 					= 100;
	// 威驰日产奇骏
	public static final int CAR_WC1_QiJun 					= 101;
	public static final int CAR_WC1_QiJun_H 			= (2 << 16) | 101;// 威驰日产奇骏
	// 睿智成韩系现代IX45
	public static final int CAR_RZC3_XiandaiIX45 			= 102;
	// 睿智成日系艾力绅
	public static final int CAR_RZC_XP1_ELYSION 			= 103;
	// 威驰奔腾B70，14款之前款式，14款车不一样，单独出来做了
	public static final int CAR_WC1_BenTengB70_Old 			= 104;
	// 东南V6，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_DongNanV6 				= 105;
	// 睿智诚4通迅格式的比亚迪S6协议
	public static final int CAR_RZC4_BYDS6 					= 106;
	// 洞见协议奇瑞艾瑞泽7协议
	public static final int CAR_DJ_QiRuiAiRuiZe7 			= 107;
	// 威驰本田哥士图
	public static final int CAR_WC2_GeShiTu 				= 108;
	// 威驰丰田卡罗拉
	public static final int CAR_WC2_Corolla 				= 109;
	// 威驰丰田凯美瑞
	public static final int CAR_WC2_Camry 					= 110;
	// 威旺M20 ，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_WeiWangM20				= 111;
	// 睿智成丰田凯美瑞
	public static final int CAR_RZC_XP1_Carmy				= 112;
	// 和驰本田CRV
	public static final int CAR_HECHI_XP1_CRV		    	= 113;
	// 迈宏 欣朴 宝马3系车
	public static final int CAR_MAIHONG_XP1_BMW3 			= 114;
	// 与威驰 福克斯共用
	public static final int CAR_WC2_YIBO 					= 115;
	// 与欣朴福克斯 翼虎共用
	public static final int CAR_XP1_YIBO 					= 116;
	// 欣朴凌派
	public static final int CAR_XP1_LINGPAI					= 117;
	// 欣朴标志2008
	public static final int CAR_XP1_BiaoZhi2008				= 118;
	// 众易畅IX35
	public static final int CAR_RZC3_ZYC_IX35 				= 119;
	public static final int CAR_RZC3_ZYC_IX35_H 		= (2 << 16) | 119;// 众易畅IX35高配带功放
	// 方易通霸道低配
	public static final int CAR_FYT_XP1_14Prado_L 			= 120;
	// 威驰14霸道低配
	public static final int CAR_WC2_14Prado_L 				= 121;
	public static final int CAR_WC2_14Prado_M				= (1 << 16) | 121;
	public static final int CAR_WC2_14Prado_H				= (2 << 16) | 121;
	// 威驰14款408
	public static final int CAR_WC2_408_14_L 				= 122;
	public static final int CAR_WC2_408_14_M 				= (1 << 16) |122;
	public static final int CAR_WC2_408_14_H 				= (2 << 16) | 122;
	// 跟C4L一样
	public static final int CAR_RZC4_PSA301					= 123;
	// 欣朴14款思域/CRV
	public static final int CAR_XP1_2014SIYU_CRV 			= 124;
	// 威驰2通迅格式的奔腾B70保留主机方案
	public static final int CAR_WC2_BENGTENGB70_KeepTheHost = 125;
	// 传琪GA3
	public static final int CAR_WC1_GA3						= 126;
	// 方易通中华H530，方易通自解的协议，格式不统一
	public static final int CAR_FYT_ZhongHuaH530 			= 127;
	// 威驰2通迅格式的睿智诚长安CS75
	public static final int CAR_WC2_CHANGANCS75 			= 128;
	// 威驰雪铁龙C5
	public static final int CAR_WC2_XueTieLongC5 			= 129;
	// 风驰奇瑞瑞虎5协议（威驰1通迅格式）
	public static final int CAR_FC_WC1_QiRuIRuihu5			= 130;
	// 欣朴通迅格式的鑫巴斯哈弗H6
	public static final int CAR_XBS_XP1_HavalH6 			= 131;
	// 威驰2通信格式的哈弗H2
	public static final int CAR_WC2_HavalH2					= 132;
	public static final int CAR_WC2_HavalH2_H				= (1 << 16)|132;
	// 科源 马自达3
	public static final int CAR_KeYuan_FYTA_MZD3 			= 133;
	// 睿智成仿欣朴杰德 .2014.08.07改为杰德专用，凌派另外的ID 号
	public static final int CAR_RZC_XP1_JieDe 				= 134;
	// 欣朴三菱劲畅
	public static final int CAR_XP1_SLJinChang 				= 135;
	// 威驰马自达CX7
	public static final int CAR_WC1_MZD_CX7					= 136;
	public static final int CAR_WC1_MZD_CX7_H			= (1<<16)|136;// 威驰马自达CX7高配带攻放
	// 睿智成韩系现代IX35
	public static final int CAR_RZC3_XiandaiIX35_L 			= (0<<16)|137;
	public static final int CAR_RZC3_XiandaiIX35_H 			= (2<<16)|137;
	// 洞见GA3
	public static final int CAR_DJ_GA3						= 138;
	// 欣朴1通迅格式的睿智诚吉利EC7
	public static final int CAR_RZC_XP1_JiLiEC7		  		= 139;
	// 长安逸动(东琛自己解的协议格式不固定用方易通通迅模式)
	public static final int CAR_DC_FYTA_ChangAnYiDong		= 140;
	// 睿智成仿欣朴凌派
	public static final int CAR_RZC_XP1_LingPai				= 141;
	// 欣朴1通迅格式的睿智诚 宝骏730，协议写的是14款
	public static final int CAR_RZC_XP1_BaoJun730 			= 142;
	// 欣朴奇骏
	public static final int CAR_XP1_QiJun 				= 143;// 欣朴奇骏
	public static final int CAR_XP1_QiJun_M 			= (1<<16)|143;// 欣朴奇骏
	public static final int CAR_XP1_QiJun_H 			= (2<<16)|143;// 欣朴奇骏143;
	// 威驰 指南者
	public static final int CAR_WC1_ZhiNanZhe 				= 144;
	// 威驰 牧马人 高配，注意高配是1，不是2，高配带原车功放
	public static final int CAR_WC1_MuMaRen					= 145;
	public static final int CAR_WC1_MuMaRen_H				= (1<<16)|145;
	// 睿智成仿欣朴大众和马自达兼容但是音量这款不一样所以分开(众易畅在用)
	public static final int CAR_RZC_DaZhong_ZYCMZD 			= 146;
	// 方易通自解的众泰T200协议格式不固定用方易通通迅模式
	public static final int CAR_FYTA_ZhongTaiT200 			= 147;
	// 方易通自解的奔腾X80协议格式不固定用方易通通迅模式
	public static final int CAR_FYTA_BenTengX80				= 148;
	// 方易通仿欣朴增加一个时间处理
	public static final int CAR_FYT_XP2_Mazida 				= 149;
	// 威驰马自达6
	public static final int CAR_WC1_MZD6 					= 150;
	public static final int CAR_WC1_MZD3 					= (1 << 16)|150;
	// 欣朴1通迅格式的道俊BYDS6
	public static final int CAR_DAOJUN_XP1_BYDS6 			= 151;
	// 睿智成仿欣朴传琪GS5
	public static final int CAR_RZC_XP1_ChuanQiGS5			= 152;
	// 睿智成名图和IX45共用一个文件
	public static final int CAR_RZC3_MingTu					= 153;
	public static final int CAR_RZC3_MingTu_H			= (2<<16)|153;//睿智成名图和IX45共用一个文件
	// 中码易通蒙迪欧
	public static final int CAR_ZMYT_XP1_Mendeo				= 154;
	// 洞见的宝骏730
	public static final int CAR_DJ_WC1_BaoJun730 			= 155;
	// 方易通自己解的长安逸动协议格式不固定用方易通通迅模式
	public static final int CAR_FYTA_ChangAnYiDong			= 156;
	// 欣朴通迅格式的道俊海马S7和海马M8共用协议
	public static final int CAR_DAOJUN_XP1_HaiMaM8_HaiMaS7	= 157;
	// 睿智诚仿欣朴格式 奥迪Q3  2014.8.25 tlm
	public static final int CAR_RZC_XP1_AoDi_Q3				= 158;
	// 鑫巴斯 欣朴格式 大众  2014.8.25 tlm
	public static final int CAR_XinBaSi_XP1_DaZhong			= 159;
	// 睿智诚高尔夫7 兼容新明锐
	public static final int CAR_RZC_XP1_DaZhong_GaoErFu7	= 160; 
	public static final int CAR2_RZC_XP1_DaZhong_GaoErFu7_H= (2<<16)|160;//睿智诚高尔夫7 兼容新明锐高配
	// 欧华 大众 朗逸
	public static final int CAR_OuHua_XP1_DaZhong_LangYi 	= 161;
	// 威驰IX35
	public static final int CAR_WC2_XiandaIx35_L			= 162;
	public static final int CAR_WC2_XiandaIx35_M			= (1<<16)|162;
	public static final int CAR_WC2_XiandaIx35_H			= (2<<16)|162;
	// 睿智诚新款标志408
	public static final int CAR_RZC4_PSA14_408		    	= 163;
	// 威驰老款嘉年华09
	public static final int CAR_WC1_FIESTA09		    	= 164;
	// 鑫巴斯传琪GS5 支持2014款传琪GS5两驱周年增值版
	public static final int CAR_XBS_XP1_ChuanQiGS5			= 165;
	// 威驰15款本田奥德赛
	public static final int CAR_WC2_15_AoDeSai		    	= 166;
	// 鑫巴斯老马自达6
	public static final int CAR_XBS_XP1_OLD_MZD6			= 167;
	// 欣朴索纳塔低配
	public static final int CAR_XP1_SoNaTa_L				= (0 << 16)|168;
	public static final int CAR_XP1_SoNaTa_M				= (1 << 16)|168;
	public static final int CAR_XP1_SoNaTa_H				= (2 << 16)|168;
	// 道俊的众泰5008协议
	public static final int CAR_DAOJUN_XP1_ZhongTai5008		= 169;
	// 欣朴不拆原车CD 马自达昂科塞拉
	public static final int CAR_XP1_AnKeSela				= 170;
	// 芯智码仿欣朴三菱劲畅
	public static final int CAR_XZM_XP1_SanLinJinChang		= 171;
	// 欣朴通迅格式的鑫巴斯长城风骏6
	public static final int CAR_XBS_XP1_ChangChengFengJun6 	= 172;
	// 威驰14款思域
	public static final int CAR_WC2_14_SiYu					= 173;
	// 欣朴蒙迪欧
	public static final int CAR_XP1_MengDiOu		    	= 174;
	// 欣朴马自达CX5
	public static final int CAR_XP1_MZD_CX5			    	= 175;
	// 睿智诚仿欣朴的东南V5协议
	public static final int CAR_RZC_XP1_DongNanV5			= 176;
	// 欣朴通迅格式的睿智诚长城风骏6(14款)
	public static final int CAR_RZC_XP1_ChangChengFengJun6	= 177;
	// 比亚迪F3速锐 ，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_BYDF3SuRui		    	= 178;
	// 威驰宝马MINI
	public static final int CAR_WC1_BMW_MINI		    	= 179;
	// 长城风俊6，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_ChangChengFengJun6		= 180;
	// 洞见的长安CS75
	public static final int CAR_DJ_ChangAnCS75		    	= 181;
	// 新马六无协议（无协议内容，但有高低配的区别）
	public static final int CAR_FYT_NewMazda6		    	= 182;
	// 洞见奇骏/天籁低配
	public static final int CAR_DJ_WC1_QiJun_Tianlai_L		= 183;
	public static final int CAR_DJ_WC1_QiJun_Tianlai_M		= (1 << 16)|183;
	public static final int CAR_DJ_WC1_QiJun_Tianlai_H		= (2 << 16)|183;
	// 长远通 C4L
	public static final int CAR_CYT_XP1_C4L		 	    	= 184;
	// 欣朴标志408
	public static final int CAR_XP1_BiaoZhi408		    	= 185;
	// 睿智诚 标志508
	public static final int CAR_RZC4_PSA508			    	= 186;
	// 睿智诚 标志308
	public static final int CAR_RZC4_PSA308			    	= 187;
	// 鑫巴斯12款CRV
	public static final int CAR_XBS_XP1_CRV2012		    	= 188;
	// 鑫巴斯马自达
	public static final int CAR_XBS_MZD			    		= 189;
	// 睿智成天籁
	public static final int CAR_RZC_XP1_TianLai		    	= 190;
	// 11款新巴斯蒙迪欧致胜 ,格式不固定用方易通FYTA格式来处理
	public static final int CAR_XBS_FYTA_MengDiOuZhiSheng	= 191;
	// 威驰 宾智
	public static final int CAR_WC2_15_BinZhi		    	= 192;
	// 洞见的传奇GS5速博
	public static final int CAR_DJ_ChuanQiGs5SuBo			= 193;
	// 鑫巴斯奔腾X80
	public static final int CAR_XBS_XP1_BenTengX80			= 194;
	// 鑫巴斯吉利GC7
	public static final int CAR_XBS_XP1_JiLiGc7		    	= 195;
	// 鑫巴斯长城C30
	public static final int CAR_XBS_XP1_ChangChengC30		= 196;
	// 睿智成仿欣朴传琪GS5速博
	public static final int CAR_RZC_XP1_ChuanQiGS5SuBo		= 197;
	// 长远通比亚迪S6协议
	public static final int CAR_CYT_FYTA_BYDS6		    	= 198;
	// 方易通自解的北汽绅宝D60低配
	public static final int CAR_FYTA_BeiQiShenBaoD60_L		= (0<<16)|199;
	public static final int CAR_FYTA_BeiQiShenBaoD60_M		= (1<<16)|199;
	public static final int CAR_FYTA_BeiQiShenBaoD60_H		= (2<<16)|199;
	// 洞见长城C30（15款）
	public static final int CAR_DJ1_ChangChengC30			= 200;
	// 方易通力帆630
	public static final int CAR_FYTA_LIFAN630		    	= 201;
	// 长远通蒙迪欧
	public static final int CAR_CYT_XP1_MengDiOu			= 202;
	// 睿智成奥德赛/宾智
	public static final int CAR_RZC_XP1_AoDeSai				= 203;
	// 迈斯诺仿睿智成标志301(拆原车CD)
	public static final int CAR_MSN_RZC_PSA301		    	= 204;
	// 方易通格式的广汽吉奥
	public static final int CAR_FYTA_JIAO			    	= 205;
	// 威驰奔驰C180/C260/GLK300/B200/E300
	public static final int CAR_WC1_Benz			  		= 206;
	// 思佰德自己找人解的长城C30
	public static final int CAR_SBD_WC1_ChangChengC30   	= 207;
	// 洞见吉利GX9，又名吉利豪情
	public static final int CAR_DJ1_JiLiGX9      			= 208;
	// 雅阁8代无协议
	public static final int CAR_FYT_ACCORD8      			= 209;
	// 迈斯洛新君越
	public static final int CAR_MSL_XP1_JUNYUE 		    	= 210;
	public static final int CAR_MSL_XP1_JUNYUE_M 		= (1<<16)|210;//迈斯洛新君越  中配	原车倒车
	public static final int CAR_MSL_XP1_JUNYUE_H 		= (2<<16)|210;//迈斯洛新君越  高配 原车倒车，支持触摸
	// 睿智成长城C30
	public static final int CAR_RZC_XP1_ChangChengC30   	= 211;
	// 欣朴奔驰B200
	public static final int CAR_XP1_BenChiB200      		= 212;
	// 方易通自解的协议，格式不统一,长城C30
	public static final int CAR_FYTA_ChangChengC30			= 213;
	// 睿智成仿欣朴霸道(仿的不全)
	public static final int CAR_RZC_XP1_14Prado_L 			= 214;
	public static final int CAR_RZC_XP1_14Prado_M 		= (1<<16)|214;//睿智成仿欣朴霸道中配
	public static final int CAR_RZC_XP1_14Prado_H 		= (2<<16)|214;//睿智成仿欣朴霸道高配
	// 睿智诚 标致 3008
	public static final int CAR_RZC4_PSA3008		    	= 215;
	// 睿智成 仿欣朴 北汽 绅宝D60
	public static final int CAR_RZC_XP1_BeiQiShenBaoD60 	= 216;
	// 睿智诚 标志 13款408
	public static final int CAR_RZC4_PSA13_408		    	= 217;
	// 欣朴通迅格式的鑫巴斯 13款蒙迪欧
	public static final int CAR_XBS_XP1_13MengDiOu			= 218;
	// 洞见 长安 悦翔V7
	public static final int CAR_DJ_ChangAnYueXiangV7		= 219;
	// 洞见 东风 风神AX7
	public static final int CAR_DJ_DongFengFengShenAX7		= 220;
	// 睿智诚 标致 2008
	public static final int CAR_RZC4_PSA2008		    	= 221;
	// 威驰 欧版福克斯
	public static final int CAR_WC2_EU_Focus		    	= 222;
	// 威驰 老款蒙迪欧
	public static final int CAR_WC1_MENGDIOU2011			= 223;
	// 东琛（威驰） 15款长安逸动
	public static final int CAR_DC_WC1_15YIDONG		    	= 224;
	// 欣朴 三菱(帕杰罗.欧蓝德.劲炫.蓝瑟)
	public static final int CAR_XP1_SanLinPajero			= 225;
	public static final int CAR_XP1_SanLinPajero_H		= (2<<16)|225;//高配带功放
	// 长安 悦翔V7，方易通自解的协议，格式不统一
	public static final int CAR_FYTA_ChangAnYueXiangV7		= 226;
	// 睿智成 长安 悦翔V7
	public static final int CAR_RZC_XP1_ChangAnYueXiangV7	= 227;
	// 思佰德自己找人解的 长安 悦翔V7
	public static final int CAR_SBD_WC1_ChangAnYueXiangV7	= 228;
	// 长远通 东南 V5 协议
	public static final int CAR_CYT_FYTA_DongNanV5      	= 229;
	// 威驰 宝马 X1
	public static final int CAR_WC1_BMWX1			   	 	= 230;
	// 道峻 2013款蒙迪欧
	public static final int CAR_DJ_MENGDIOU2013		    	= 231;
	// 欣朴 欧宝
	public static final int CAR_XP2_OPEL			    	= 232;
	// 鑫巴斯仿欣朴 吉普
	public static final int CAR_XBS_XP1_Jeep		    	= 233;
	// 睿智诚 雪铁龙 DS5
	public static final int CAR_RZC4_DS5			    	= 234;
	// 睿智诚 雪铁龙 DS5LS
	public static final int CAR_RZC4_DS5LS			    	= 235;
	// 威驰 雪佛兰 15款赛欧3
	public static final int CAR_WC2_SAIL3			    	= 236;
	// 睿智诚（仿欣朴）海马M5//欣朴1通迅格式的//睿智诚海马M5（馨飞扬的海马M5和睿智诚的一样）
	public static final int CAR_RZC_XP1_HAIMAM5		    	= 237;
	// 睿智诚 雪铁龙 C5
	public static final int CAR_RZC4_C5			    		= 238;
	// 爱影 爱影自解 保时捷
	public static final int CAR_AY_FYTA_BaoShiJie			= 239;
	// 睿智诚仿欣朴雪佛兰15款赛欧3	  2014.12.27
	public static final int CAR_RZC_XP1_SAIL3				= 240;
	// 威驰锐界
	public static final int CAR_WC1_RUIJIE					= 241;
	// 长远通标志408
	public static final int CAR_CYT_XP1_BiaoZhi408			= 242;
	// 方易通自解的海马M5
	public static final int CAR_FYT_WC1_HAIMAM5				= 243;
	// 睿智诚的欣朴1通信格式的哈弗H2
	public static final int CAR_RZC_XP1_HavalH2				= 244;	
	// 洞见的比亚迪L3
	public static final int CAR_DJ_BydL3					= 245;	
	// 威驰 宝骏730
	public static final int CAR_WC2_BaoJun730				= 246;	
	// 雪铁龙 C3XR
	public static final int CAR_WC2_XueTieLongC3XR			= 247;
	// 睿智诚2通迅格式的睿智诚中华V5/H530 /*下面是睿智诚给其它方案公司的CAN_SCI_GeneralV 0.08.pdf文当，我司专用CAN_SCI_V08.pdf，兼容车型：奇瑞A3，福美来，海马M3，海马S7，全球鹰GC7，传琪GA3，H530/V5等等,具体车型可能要分出多个ID，但共用一个文件!!!!
	public static final int CAR_RZC2_ZhongHuaV5				= 248;
	// 睿智诚索纳塔9
	public static final int CAR_RZC3_SoNaTa9				= 249;
	// 方易通东风风神AX7
	public static final int CAR_FYT_XP1_FengShenAX7			= 250;
	// 欣朴起亚索兰托
	public static final int CAR_XP_QIYA_SoLanTuo			= 251;
	//欣朴 起亚 索兰托
	public static final int CAR_XP_QIYA_SoLanTuo_M		= (1<<16)|251;
	//欣朴 起亚 索兰托
	public static final int CAR_XP_QIYA_SoLanTuo_H		= (2<<16)|251;
	// 威驰标志系列全兼容
	public static final int CAR_WC2_PSAALL_0			= (0<<16)|252;//威驰 标志 系列全兼容 DS5高配
	public static final int CAR_WC2_PSAALL_1			= (1<<16)|252;//威驰 标志 系列全兼容 DS5低配
	public static final int CAR_WC2_PSAALL_2			= (2<<16)|252;//威驰 标志 系列全兼容 DS5LS高配
	public static final int CAR_WC2_PSAALL_3			= (3<<16)|252;//威驰 标志 系列全兼容 DS5LS低配
	public static final int CAR_WC2_PSAALL_4			= (4<<16)|252;//威驰 标志 系列全兼容 DS4LS高配
	public static final int CAR_WC2_PSAALL_5			= (5<<16)|252;//威驰 标志 系列全兼容 DS4LS低配
	public static final int CAR_WC2_PSAALL_6			= (6<<16)|252;//威驰 标志 系列全兼容 408高配（2014）
	public static final int CAR_WC2_PSAALL_7			= (7<<16)|252;//威驰 标志 系列全兼容 508高配
	public static final int CAR_WC2_PSAALL_8			= (8<<16)|252;//威驰 标志 系列全兼容 508低配
	public static final int CAR_WC2_PSAALL_9			= (9<<16)|252;//威驰 标志 系列全兼容 C4L
	public static final int CAR_WC2_PSAALL_10			= (10<<16)|252;//威驰 标志 系列全兼容 其他
	// 欣朴15款英朗
	public static final int CAR_XP1_15YingLang				= 253;	
	// 威驰15款英朗
	public static final int CAR_WC2_15YingLang				= 254;
	// 睿智诚东风风神AX7
	public static final int CAR_RZC_XP1_FengShenAX7			= 255;
	// 思佰德自己找人解的传琪GA6
	public static final int CAR_SBD_WC1_ChuanQiGA6			= 256;
	// 洞见传琪GA6
	public static final int CAR_DJ_ChuanQiGA6				= 257;
	public static final int CAR_DJ_ChuanQiGA6_M		    = (1<<16)|257;//洞见 传琪 GA6
	public static final int CAR_DJ_ChuanQiGA6_H		    = (2<<16)|257;//洞见 传琪 GA6
	// 科源传琪GA6
	public static final int CAR_KeYuan_FYTA_ChuanQiGA6		= 258;
	// 睿智成仿欣朴传琪GA6-GA3-GA3S视界  （智诚说后面的GA3也按GS5/GA6/GS5速博的协议盒子出，而不用以前那种很乱的GA3协议出货）
	public static final int CAR_RZC_XP1_ChuanQiGA6			= 259; 
	// 威驰传琪GA6
	public static final int CAR_WC2_ChuanQiGA6		    = 260;//威驰 传琪 GA6
	public static final int CAR_WC2_ChuanQiGA6_M		= (1<<16)|260;//威驰 传琪 GA6中配
	public static final int CAR_WC2_ChuanQiGA6_H		= (2<<16)|260;//威驰 传琪 GA6高配
	public static final int CAR_KANGSHENG_XP1_FuRuiSi		= 261;
	// 鑫巴斯雅阁9低配
	public static final int CAR_XBS_XP1_ACCORD9_Lo			= 262;
	// 睿智成仿欣朴15款英朗
	public static final int CAR_RZC_XP1_15YingLang			= 263;
	// 洞见奇瑞瑞虎5协议
	public static final int CAR_DJ_QiRuIRuihu5				= 264;   
	// 欣朴1通迅格式的睿智诚众泰T600 （MCU兼容到吉利EC7协议文件中）
	public static final int CAR_RZC_XP1_ZhongTaiT600		= 265;
	// 欣朴索纳塔9
	public static final int CAR_XP1_SoNaTa9					= 266;
	// 欣朴奥迪A3
	public static final int CAR_XP_FYTA_AudiA3				= 267;
	// 方易通奇瑞瑞虎5协议
	public static final int CAR_FYT_WC1_QiRuIRuihu5			= 268;
	// 威驰 奔驰B200 协议内容跟大众一样的
	public static final int CAR_WC1_BenzB200				= 269;
	// 威驰 大众 凌渡
	public static final int CAR_WC2_DaZhong_LAMANDO			= 270;
	// 洞见新款B70
	public static final int CAR_DJ_BENGTENG14B70			= 271;
	// 欣朴1通迅格式的睿智诚江滩瑞风S3
	public static final int CAR_RZC_XP1_JiangHuaiRuiFengS3	= 272;	
	// 睿智诚的 名爵-锐腾
	public static final int CAR_RZC_XP1_MingJueRuiTeng		= 273;
	// 道俊雅阁7
	public static final int CAR_DJ_XP1_ACCORD7				= 274;	
	// 鑫巴斯老款保时捷卡宴
	public static final int CAR_XBS_XP1_BaoShiJie			= 275;
	// 道骏比亚迪F6
	public static final int CAR_DJ_XP1_BIYADI_F6			= 276;
	// 迈宏宝马X1
	public static final int CAR_MH_XP1_BmwX1				= 277;
	// 欣朴标志301和---14款408/2008共用文件
	public static final int CAR_XP1_BiaoZhi301				= 278;
	// 欣朴-大众凌渡
	public static final int CAR_XP1_DaZhong_LAMANDO			= 279;
	// 睿智诚----传琪GS5/GA5/GA6/gs4
	public static final int CAR_RZC_XP1_ChuanQiGS4			= 280;
	// 欣朴标志全兼容---和301/14款408/2008共用文件
	public static final int CAR_XP1_PsaAll					= 281;
	// 威驰---名爵锐腾
	public static final int CAR_WC2_MingJueRuiTeng			= 282;
	// 爱影宝马3 系专用
	public static final int CAR_AY1_BMW3S					= 283;
	// 爱影凯迪拉克拆空调板
	public static final int CAR_AY1_ATS_NOAIRKEYBOARD		= 284;
	// 爱影----宝马MINI
	public static final int CAR_AY1_BMW_MINI				= 285;
	// 巴谷----奥迪系列
	public static final int CAR_BG_XP1_Audi					= 286;
	// 欣朴吉普
	public static final int CAR_XP1_Jeep					= 287;
	public static final int CAR_XP_JEEP_H				= (1<<16)|287;//欣朴指南者 高配带攻防
	// 科源-名爵锐腾(共用传琪GA6文件)
	public static final int CAR_KeYuan_FYTA_MingJueRuiTeng	= 288;
	// 威驰-传琪GS4(兼容于GA6)
	public static final int CAR_WC2_ChuanQiGS4				= 289;
//	public static final int CAR_WC2_ChuanQiGS4_M		= (1<<16)|289;//威驰格式传琪 GS4(兼容于GA6)中配
//	public static final int CAR_WC2_ChuanQiGS4_H		= (2<<16)|289;//威驰格式传琪 GS4(兼容于GA6)高配
	// 睿智成-启辰T70
	public static final int CAR_RZC_XP1_QiChenT70			= 290;
	// 睿智诚--b50(38400)和中华V5共用文件
	public static final int CAR_RZC2_BenTengB50				= 291;
	// 睿智诚--东南V6（兼容于东南V5)
	public static final int CAR_RZC_XP1_DongNanV6			= 292;
	// 威驰--15款锐界
	public static final int CAR_WC2_RuiJieNew				= 293;
	// 威驰丰田全兼容
	public static final int CAR_WC2_TOYOTA_ALL				= 294;
	public static final int CAR_WC2_TOYOTA_REIZ			= (1<<16)|294;//威驰丰田全兼容-锐志
	public static final int CAR_WC2_TOYOTA_RAV4			= (2<<16)|294;//威驰丰田全兼容-RAV4
	public static final int CAR_WC2_TOYOTA_CAMRY		= (3<<16)|294;//威驰丰田全兼容-凯美瑞
	public static final int CAR_WC2_TOYOTA_COROLLA		= (4<<16)|294;//威驰丰田全兼容-卡罗拉
	public static final int CAR_WC2_TOYOTA_HIGHLANDER	= (5<<16)|294;//威驰丰田全兼容-汉兰达
	// 思佰德--东风风光360
	public static final int CAR_SBD_FYTA_DongFengFengGuang360		= 295;
	// 索航--速锐
	public static final int CAR_SH_FYTA_BydSuRui			= 296;
	// 威驰--15款CRV
	public static final int CAR_WC2_15_CRV_SiYu				= 297;
	// 欣朴--15款CRV
	public static final int CAR_XP1_2015SiYu_CRV			= 298;
	public static final int CAR_XP1_2015SIYU_CRV_M		= (1<<16)|298;//欣朴15款CRV 锋范（协议选择的时候写锋范）
	// 爱影 15款英朗
	public static final int CAR_AY1_15YingLang		    	= 299;
	// 欣朴 15款锐界
	public static final int CAR_XP1_RUIJIE2015				= 300;
	// 和驰仿欣朴 标致206
	public static final int CAR_HECHI_XP1_PSA206			= 301;
	// 洞见仿威驰 雪铁龙 DS5LS
	public static final int CAR_DJ_WC2_DS5LS				= 302;
	// 洞见仿威驰 雪铁龙 DS5
	public static final int CAR_DJ_WC2_DS5					= 303;
	// 威驰 传琪GS5系列(选低配GS5 高配GS5速博 枚举中有说明)
	public static final int CAR_WC2_ChuanQiGS5AndSuBo		= 304;
	public static final int CAR_WC2_ChuanQiGS5AndSuBo_H	= (2<<16)|304;//威驰传琪GS5速博
	// 道骏 蒙迪欧(保留原车空调面板与欣朴福克斯一样)
	public static final int CAR_DJ_MENGDIOU2013_BAOLIUAirPanel = 305;
	// 睿智诚仿欣朴 力帆 820
	public static final int CAR_RZCexc_Lifan820				= 306;
	// 鑫巴斯 中华V3(2015)
	public static final int CAR_XBS_XP1_15ZhongHuaV3		= 307;
	// 威驰 15款威朗
	public static final int CAR_WC2_15WeiLang				= 308;
	// 睿智成仿欣朴 15款 威朗
	public static final int CAR_RZC_XP1_15WeiLang			= 309;
	// 睿智诚仿欣朴 宝骏 560
	public static final int CAR_RZC_XP1_BaoJun560			= 310;
	// 欣朴 福特 F150
	public static final int CAR_XP1_FordF150				= 311;
	// 鑫巴斯仿欣朴 马自达3/马自达6睿翼
	public static final int CAR_XBS_XP1_MZD3_MZD6RuiYi		= 312;
	// 《思佰德--自已找人解的电动协议》(责任人:yc)	 2015.07.13
	public static final int CAR_SBD_WC1_ElectricVehicle		= 313;
	// 《睿智诚--东风风光360》(责任人:yc)	 2015.07.14
	public static final int CAR_RZC_XP1_FengGuang360		= 314;
	// 《鑫巴斯--鑫巴斯斯巴鲁森林人》(责任人:XG)	 2015.07.14
	public static final int CAR_XBS_XP1_Subaru_SenLinren	= 315;
	// 《爱影奔驰C200》(责任人:xjh)
	public static final int CAR_AiYing1_AUDIQ3				= 316;
	// 《威驰凯迪拉克》(责任人:xjh)
	public static final int CAR_WeiChi2_ATS					= 317;
	// 《欣朴15款威朗 》(责任人:XJH)	 2015.7.30
//	public static final int CAR_XinPu1_TongYongGM_H			= 318;
	public static final int CAR_XP1_15WeiLang		    	= (4<<16)|318;//欣朴 威朗 2015款（手动空调）
	public static final int CAR_XP1_15WeiLang_H		    	= (5<<16)|318;//欣朴 威朗 2015款 高配（自动空调）
	// 《威驰--15款福克斯》(责任人:cjx) 
	public static final int CAR_WeiChi2_RuiJieNew			= 319;
	// 【威驰----哈弗h6(兼容于H2)】(责任人:YC)
	public static final int CAR_WeiChi2_ChangChengH2		= 320;
	// 【威驰----威驰本田全兼容】(责任人:XG)
	public static final int CAR_WC2_Honda_AllCom			= 321;
	// 【威驰-日产全兼容(楼兰)】(责任人:xg)
	public static final int CAR_WC1_NISSAN_LouLan			= 322;
	public static final int CAR_WC1_NISSAN_LouLan_M		= (1<<16)|322;//威驰 日产 全兼容(楼兰)
	public static final int CAR_WC1_NISSAN_LouLan_H		= (2<<16)|322;//威驰 日产 全兼容(楼兰)
	// 【和驰---保时捷卡宴】(责任人:YC)
	public static final int CAR_HC_XP1_PorscheCayenne		= 323;
	// 《比纳瑞---日产楼兰 》(责任人: xg)
	public static final int CAR_BNR_XP1_NISSAN_LouLan		= 324;//欣朴 日产 全兼容(楼兰)
	public static final int CAR_BNR_XP1_NISSAN_LouLan_H		= (2<<16)|324;//欣朴 日产 全兼容(楼兰)
	// 《洞见---马自达CX5 》(责任人: xg)
	public static final int CAR_DJ_WC1_MZD_CX5				= 325;
	// 【鑫飞扬东南DX7】(责任人:YC)
	public static final int CAR_XFY_XP1_DongNanDX7			= 326;
	//和驰老天籁
	public static final int CAR_HC_OLD_TianLai				= 327;
	//睿智成马自达CX7
	public static final int CAR_RZC2_MZD_CX7				= 328;
	//鼓润的东南DX7博朗
	public static final int CAR_GR_XP1_DongNanDX7			= 329;
	//睿智诚老款嘉年华(12之前款式)
	public static final int CAR_RZC_XP1_OldFiestar			= 330;
	//睿智诚楼兰
	public static final int CAR_RZC_XP1_LuoLan				= 331;
	//睿智诚楼兰 全景
	public static final int CAR_RZC_XP1_LuoLan_H			=  (2<<16)|331;
	 //思博赛欧3 别克通用系列车型
	public static final int CAR_SB_XP1_SaiOu3				= 332;
	//欣朴 日产楼兰
	public static final int CAR_XP1_NISSAN_LouLan			= 333;
	//欣朴 日产楼兰
	public static final int CAR_XP1_NISSAN_LouLan_H			=  (2<<16)|333;
	//睿智诚2015款福克斯
	public static final int CAR_RZC_XP1_Focus2015			= 334;
	//科源宝骏560(兼容宝骏730)
	public static final int CAR_KeYuan_FYTA_BaoJun560		= 335;
	//威驰福睿斯
	public static final int CAR_WC2_Escort					= 336;
	//威驰荣威360
	public static final int CAR_WC2_RongWei360				= 337;
	// 惠州金鹰科技 荣威 360(15款)
	public static final int CAR_JYKJ_XP1_RongWei360			= 338;
	// 睿智诚 标志 全兼容
	public static final int CAR_RZC4_PSA_ALL				= 339;
	// 鑫巴斯仿欣朴 本田 15款CRV
	public static final int CAR_XBS_XP1_2015CRV				= 340;
	// 威驰 15款宝骏560
	public static final int CAR_WC2_BaoJun560				= 341;
	// 睿智诚仿欣朴 荣威 360
	public static final int CAR_RZC_XP1_ROEWE360			= 342;
	// 爱影 雷克萨斯 rx270
	public static final int CAR_AY1_LEXUS_RX270				= 343;
	// 欣朴 奔驰 威霆
	public static final int CAR_XP1_BenChiVITO				= 344;
	// 威驰 别克 君威(竖屏)
	public static final int CAR_WC2_ShuPingJunWei			= 345;
	// 方易通 长安逸动.2015 外挂置盒子
	public static final int CAR_FYT_WC1_15YiDong			= 346;
	// 莹隆 福特 锐界(原车屏升级)
	public static final int CAR_YingLong_Ruijie				= 347;
	// 道俊 凯迪拉克 ATS(竖屏)
	public static final int CAR_DAOJUN_XP1_ATS				= 348;
	//路正仿道俊XTS的14款君越
	public static final int CAR_DAOJUN_14Junwei_Luzheng		= (10 << 16)|348;
	// 巴谷仿欣朴 保时捷 卡宴
	public static final int CAR_BAGOO_XP1_PorscheCayenne	= 349;
	// 豪正仿欣朴 宝马 系列
	public static final int CAR_HAOZHENG_XP1_BmwSeries		= 350;
	// 威驰 起亚 K5（16款）
	public static final int CAR_WC2_16_QiYaK5				= 351;
	// 威驰 雷诺 卡宾
	public static final int CAR_WC2_RENAULT_KaBin			= 352;
	public static final int CAR_0353						= 353;
	public static final int CAR_0354						= 354;
	public static final int CAR_0355						= 355;
	public static final int CAR_0356						= 356;
	public static final int CAR_0357						= 357;
	public static final int CAR_0358						= 358;
	public static final int CAR_0359						= 359;
	public static final int CAR_0360						= 360;
	public static final int CAR_0361						= 361;
	public static final int CAR_0362						= 362;
	public static final int CAR_0363						= 363;
	public static final int CAR_0364						= 364;
	public static final int CAR_0365						= 365;
	public static final int CAR_0366						= 366;
	public static final int CAR_0367						= 367;
	public static final int CAR_0368						= 368;
	public static final int CAR_0369						= 369;
	public static final int CAR_0370						= 370;
	public static final int CAR_0371						= 371;
	public static final int CAR_0372						= 372;
	public static final int CAR_0373						= 373;
	public static final int CAR_0374						= 374;
	public static final int CAR_0375						= 375;
	public static final int CAR_0376						= 376;
	public static final int CAR_0377						= 377;
	public static final int CAR_0378						= 378;
	public static final int CAR_0379						= 379;
	public static final int CAR_0380						= 380;
	public static final int CAR_0381						= 381;
	public static final int CAR_0382						= 382;
	public static final int CAR_0383						= 383;
	public static final int CAR_0384						= 384;
	public static final int CAR_0385						= 385;
	public static final int CAR_0386						= 386;
	public static final int CAR_0387						= 387;
	public static final int CAR_0388						= 388;
	public static final int CAR_0389						= 389;
	public static final int CAR_0390						= 390;
	public static final int CAR_0391						= 391;
	public static final int CAR_0392						= 392;
	public static final int CAR_0393						= 393;
	public static final int CAR_0394						= 394;
	public static final int CAR_0395						= 395;
	public static final int CAR_0396						= 396;
	public static final int CAR_0397						= 397;
	public static final int CAR_0398						= 398;
	public static final int CAR_0399						= 399;
	public static final int CAR_0400						= 400;
}
