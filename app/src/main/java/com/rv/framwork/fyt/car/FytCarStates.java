package com.rv.framwork.fyt.car;

import android.content.Context;


import com.rv.framwork.fyt.ipc.FinalBt;
import com.rv.framwork.fyt.ipc.FinalRadio;
import com.rv.framwork.fyt.ipc.FinalMainServer;
import com.rv.framwork.fyt.ipc.data.FinalCanbus;
import com.rv.framwork.fyt.ipc.data.FinalMain;
import com.rv.framwork.fyt.ipc.data.FinalSound;
import com.rv.framwork.manager.EqualizerManager;
import com.rv.framwork.utils.AppLogUtil;
import com.rv.framwork.em.EqFieldMode;
import com.syu.remote.Callback.OnRefreshLisenter;
import com.syu.remote.RemoteTools;
import com.rv.framwork.fyt.ipc.JTools;

import java.util.ArrayList;
import java.util.List;

public class FytCarStates {

	final int [] MAIN_LOOK_CODE = {
    		FinalMain.U_APP_ID, FinalMain.U_NAVI_PACKAGE, FinalMain.U_APP_VISIBILITY, FinalMain.U_ACC_ON,
    		FinalMain.U_MCU_DIEECTION_KEY, FinalMain.U_BRIGHT_LEVEL, FinalMain.U_GPS_SPEED,
    		FinalMain.U_LAMPLET
    };

	final int [] TV_LOOK_CODE = {

    };

	final int [] CANBUS_LOOK_CODE = {
    		FinalCanbus.U_CANBUS_ID, FinalCanbus.U_EXIST_CAR_RADIO
    };

	final int [] RADIO_LOOK_CODE = {
    		FinalRadio.U_BAND, FinalRadio.U_FREQ,FinalRadio.U_AREA,
    		FinalRadio.U_SCAN, FinalRadio.U_LOC,FinalRadio.U_STEREO
    };

	final int [] BT_LOOK_CODE = {
			FinalBt.U_PHONE_NAME, FinalBt.U_PHONE_STATE
	};


	final int [] SOUND_LOOK_CODE = {
			FinalSound.U_VOL,
			FinalSound.U_MUTE
	};

	public static int mAppID = 0;
	public static int mAccState = 0;

	public static int mExistCarRadio = 0;

	static FytCarStates mCar;

	public RemoteTools mTools;
	Context mContext;
	//默认导航包名
	String mNaviPkg = "";
	//电视制式
	int mTvStyle = 0;

	public static int mCanbusType;

	//需要隐藏的应用
	List<String> mHideApps = new ArrayList<String>();
	List<String> mShowApps = new ArrayList<String>();
	private FytCarBean fytCarBean = new FytCarBean();
    public int[] eq_gain = new int[36];//EQ增益  分16和32组
    public int[] eq_q = new int[36];//EQ：Q值  16组
    public int[] eq_fc = new int[16];//EQ：FC值  16组
    private int fieldsX = 0;
    private int fieldsY = 0;
    private int volume = 0;
	public static FytCarStates getCar(Context context) {
		if(mCar == null)
			mCar = new FytCarStates(context);
		return mCar;
	}

	private FytCarStates(Context context) {
		mContext = context;
        initCarBeanData();
        AppLogUtil.e(" setupEqModule - CarStates");
		mTools = new RemoteTools(mContext);
		setupMainModule(mTools);
		//setupTvModule(mTools);
		//setupcanbusModule(mTools);
		//setupRadioModule(mTools);
		//setupBtModule(mTools);
        setupEqModule(mTools);
		mTools.bind();
	}

    public void initCarBeanData() {
        if (fytCarBean.getSoundEffect() == null) {
            fytCarBean.setSoundEffect(new FytCarBean.SoundEffect());
        }

        if (fytCarBean.getSoundField() == null) {
            fytCarBean.setSoundField(new FytCarBean.SoundField());
        }

        if (fytCarBean.getBassFilter() == null) {
            fytCarBean.setBassFilter(new FytCarBean.BassFilter());
        }

        if (fytCarBean.getBassIncrease() == null) {
            fytCarBean.setBassIncrease(new FytCarBean.BassIncrease());
        }

        if (fytCarBean.getSurroundSound() == null) {
            fytCarBean.setSurroundSound(new FytCarBean.SurroundSound());
        }
    }
    void setupEqModule(RemoteTools tools){
        AppLogUtil.e(" setupEqModule - MODULE_CODE_SOUND");
        if(tools == null) return;
        //auto.observe(FinalMainServer.MODULE_CODE_SOUND, FinalSound.U_EQ_TYPE, FinalSound.U_LOUD, FinalSound.EQ_CUSTOMIZED);
//        auto.observe(FinalMainServer.MODULE_CODE_SOUND, FinalSound.U_BAL_FADE);
//        auto.observe(FinalMainServer.MODULE_CODE_CANBUS, FinalCanbus.U_DRIVER_ON_RIGHT);
        tools.enableModule(
                FinalMainServer.MODULE_CODE_SOUND,
                FinalSound.U_MUTE,
                FinalSound.U_VOL,
                FinalSound.U_EQ_GAIN,
                FinalSound.U_EQ_Q,
                FinalSound.U_EQ_MODE,
                FinalSound.U_EQ_CF,
                FinalSound.U_EQ_TYPE,
                FinalSound.EQ_CUSTOMIZED,
                FinalSound.U_LOUD,
                FinalSound.U_SUBWOOF_GAIN,
                FinalSound.U_BAL_FADE
        );

        tools.addRefreshLisenter(FinalMainServer.MODULE_CODE_SOUND,
                new OnRefreshLisenter() {
                    @Override
                    public void onRefresh(int code, int[] ints, float[] floats, String[] strings) {
                        //                        if (TheApp.getInstance().mCustomerId != FinalCustomer.CUSTOMER_ID_HBS)
                        //                            return;
                        int tag = -1;
                        int value = -1;
                        try{
                            if(ints.length > 0){
                                tag = ints[0];
                            }
                            if(ints.length > 1){
                                value = ints[1];
                            }
                            switch (code) {
                                case FinalSound.U_EQ_TYPE://类型
                                    fytCarBean.getSoundEffect().setEq_type(ints[0]);
                                    break;
                                case FinalSound.U_EQ_GAIN:
                                    eq_gain[ints[0]] = ints[1];
                                    fytCarBean.getSoundEffect().setEq_gain(eq_gain);
                                    sendEqUpdate();
                                    break;
                                case FinalSound.U_EQ_Q:
                                    eq_q[ints[0]] = ints[1];
                                    fytCarBean.getSoundEffect().setEq_q(eq_q);
                                    break;
                                case FinalSound.U_EQ_MODE:
                                    fytCarBean.getSoundEffect().setEq_mode(ints[0]);
                                    break;
                                case FinalSound.U_LOUD:
                                    fytCarBean.getSoundEffect().setLoud(ints[0]);
                                    break;
                                case FinalSound.U_EQ_CF:
                                    eq_fc[ints[0]] = ints[1];
                                    fytCarBean.getSoundEffect().setEq_q(eq_fc);
                                    break;
                                case FinalSound.U_BAL_FADE:
                                    fieldsY = ints[1];
                                    fieldsX = ints[0];
                                    AppLogUtil.e("fieldsX:"+fieldsX+"msg.ints[0]"+ints[0]+"fieldsY:"+fieldsY+"msg.ints[1]"+ints[1]);
                                    sendModeUpdate();
                                    break;
                                case FinalSound.U_VOL:
                                    volume = tag;
                                    sendVolumeUpdate();
                                    break;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        AppLogUtil.e("setupEqModule - MODULE_CODE_SOUND- tag:" + tag + "value: " + value + " code : " + code);
                    }
                },
                FinalSound.U_VOL,
                FinalSound.U_MUTE,
                FinalSound.U_EQ_TYPE,
                FinalSound.U_LOUD,
                FinalSound.U_EQ_GAIN,
                FinalSound.U_EQ_Q,
                FinalSound.U_EQ_MODE,
                FinalSound.U_EQ_CF,
                FinalSound.U_SUBWOOF_GAIN,
                FinalSound.EQ_CUSTOMIZED,
                FinalSound.U_BAL_FADE
        );
    }
    public void sendEqUpdate(){
        //L825
        /*if(tag == 0){
            //低
        }else{
            //高
        }*/
        EqualizerManager.INSTANCE.sendEqUpdate(
                eq_gain[1],
                0,
                eq_gain[0]
        );
    }
    public void sendVolumeUpdate(){
        EqualizerManager.INSTANCE.sendVolumeUpdate(volume);
    }

    public void sendModeUpdate(){
        EqFieldMode mode = EqFieldMode.OtherMode;
        if(fieldsX == 8 && fieldsY == 8){
            mode = EqFieldMode.CenterMode;
        }else if(fieldsX == 8 && fieldsY == 16){
            mode = EqFieldMode.RearMode;
        }else if(fieldsX == 8 && fieldsY == 0){
            mode = EqFieldMode.FrontMode;
        }
        EqualizerManager.INSTANCE.sendModeUpdate(mode);
    }
	void setupMainModule(RemoteTools tools){
		if(tools == null) return;
		tools.enableModule(FinalMainServer.MODULE_CODE_MAIN, MAIN_LOOK_CODE);
		tools.enableModule(FinalMainServer.MODULE_CODE_SOUND, SOUND_LOOK_CODE);
		//debug
		tools.addRefreshLisenter(FinalMainServer.MODULE_CODE_MAIN, new OnRefreshLisenter() {

			@Override
			public void onRefresh(int updateCode, int[] ints, float[] flts,
					String[] strs) {

                int tag = -1;
                int value = -1;
                try{
                    tag = ints[0];
                    value = ints[1];
                }catch (Exception e){
                    e.printStackTrace();
                }
                AppLogUtil.e("setupMainModule - MODULE_CODE_MAIN- tag:" + tag + "value: " + value);

				if(updateCode == FinalMain.U_APP_ID && JTools.check(ints, 1)){
					if (ints != null && ints.length > 0) {
						mAppID = ints[0];
					}

				}else if(updateCode == FinalMain.U_ACC_ON){
					if(ints != null && ints.length > 0){
						mAccState = ints[0];
					}
				}
			}
		}, FinalMain.U_APP_ID,FinalMain.U_NAVI_PACKAGE,FinalMain.U_APP_VISIBILITY,FinalMain.U_ACC_ON);

	}

	void setupcanbusModule(RemoteTools tools){
		if(tools == null) return;


		tools.enableModule(FinalMainServer.MODULE_CODE_CANBUS, CANBUS_LOOK_CODE);
		//debug
		tools.addRefreshLisenter(FinalMainServer.MODULE_CODE_CANBUS, new OnRefreshLisenter() {

			@Override
			public void onRefresh(int updateCode, int[] ints, float[] flts,
					String[] strs) {
				if(updateCode == FinalCanbus.U_CANBUS_ID && JTools.check(ints, 1)){
					mCanbusType = ints[0];
				}else if(updateCode == FinalCanbus.U_EXIST_CAR_RADIO && JTools.check(ints, 1)){
					mExistCarRadio = ints[0];
				}
			}
		}, FinalCanbus.U_CANBUS_ID, FinalCanbus.U_EXIST_CAR_RADIO);
	}

	void setupTvModule(RemoteTools tools){
		if(tools == null) return;
		tools.enableModule(FinalMainServer.MODULE_CODE_TV, TV_LOOK_CODE);
	}

	void setupRadioModule(RemoteTools tools){
		if(tools == null) return;
		tools.enableModule(FinalMainServer.MODULE_CODE_RADIO, RADIO_LOOK_CODE);
	}
	void setupBtModule(RemoteTools tools){
		if(tools == null) return;
		tools.enableModule(FinalMainServer.MODULE_CODE_BT, BT_LOOK_CODE);
	}
	public void setTvStyle(int style) {
		this.mTvStyle = style;
	}

	public int getmTvStyle() {
		return mTvStyle;
	}

	public void setmNaviPkg(String mNaviPkg) {
		this.mNaviPkg = mNaviPkg;
	}

	public String getmNaviPkg() {
		return mNaviPkg;
	}

	public List<String> getHideApps() {
		return mHideApps;
	}

	public List<String> getShowApps() {
		return mShowApps;
	}

	public RemoteTools getTools() {
		return mTools;
	}

	public  void addHideApp(String app) {
		if(!mHideApps.contains(app))
			mHideApps.add(app);
	}

	public  void removeHideApp(String app) {
		if(mHideApps.contains(app))
			mHideApps.remove(app);
	}

	public  void addShowApp(String app) {
		if(!mShowApps.contains(app))
			mShowApps.add(app);
	}

	public  void removeShowApp(String app) {
		if(mShowApps.contains(app))
			mShowApps.remove(app);
	}

    public FytCarBean getFytCarBean() {
        return fytCarBean;
    }

    public void setFytCarBean(FytCarBean fytCarBean) {
        this.fytCarBean = fytCarBean;
    }

    public int getFieldsX() {
        return fieldsX;
    }

    public int getFieldsY() {
        return fieldsY;
    }

    public int getVolume() {
        return volume;
    }
}
