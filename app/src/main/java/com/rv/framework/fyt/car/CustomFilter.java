package com.rv.framework.fyt.car; /**
 * 工程名:syu_sp_Launcher3
 * 文件名:CustomFilter.java
 * 包   名:com.syu.car
 * 日   期:2016年8月22日上午10:37:51
 * 作   者:Administrator 
 * Copyright (c) 2016, kexuan52@yeah.net All Rights Reserved.
 *
 *//*

package com.rv.framework.fyt.car;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;


import com.rv.framework.fyt.ipc.data.FinalMain;
import com.syu.remote.Callback.OnRefreshLisenter;


import java.util.HashMap;

*/
/**
 *类   名:CustomFilter
 *功   能:TODO
 *
 *日  期:2016年8月22日 上午10:37:51
 * @author Administrator
 *
 *//*

public class CustomFilter extends AppFilter implements OnRefreshLisenter, Runnable{
	public static HashMap<String, Boolean> mHideApps = null;
	public static final HashMap<ComponentName, Integer> mIcons = new HashMap<ComponentName, Integer>();
	
	static {
		mHideApps = new HashMap<String, Boolean>();
		mHideApps.put("com.android.calendar", true);
		mHideApps.put("com.android.inputmethod.latin", false);
		mHideApps.put(FytPackage.OUTER_DVR, false);
		mHideApps.put(FytPackage.INNER_DVR, false);
		mHideApps.put(FytPackage.ipodAction, true);
		mHideApps.put(FytPackage.dvdAction, false);
		mHideApps.put(FytPackage.cdcAction, true);
		mHideApps.put(FytPackage.carBus, false);
		mHideApps.put(FytPackage.calibrationAction, true);
		mHideApps.put(FytPackage.tvAction, false);
		mHideApps.put(FytPackage.sysSetAction,true );
		mHideApps.put(FytPackage.radioAction,true );
		mHideApps.put(FytPackage.btAction,true );
		mHideApps.put(FytPackage.allappAction,false );
		
		
	}
	
	Looper workLooper = null;
	Handler mHandler = null;
	RefreshCallback mCallback;
	
	public interface RefreshCallback {
		void onRefresh();
	}
	
	@Override
	public boolean shouldShowApp(ComponentName app) {
		if(app == null) return false; 
		boolean result = true;
		final String pkg = app.getPackageName();
		if(pkg != null && mHideApps.containsKey(pkg)) {
			result = mHideApps.get(pkg);
		}
		return result;
	}

	@Override
	public void onRefresh(int updateCode, int[] ints, float[] flts, String[] strs) {

		switch (updateCode) {

		case FinalMain.U_APP_VISIBILITY:
			if (JTools.check(ints, 1) && JTools.check(strs, 1)) {
//				LogPreview.show("U_APP_VISIBILITY   strs[0]=" + strs[0] + "  ints[0]=" + ints[0]);
				final String pkg = strs[0];
				boolean changed = false;
				final boolean has = mHideApps.containsKey(pkg);
				if (ints[0] == 1 && has) {
					mHideApps.remove(pkg);
					changed = true;
				} else if(ints[0] != 1 && !has){
					mHideApps.put(pkg, false);
					changed = true;
				}
				
				if(changed && mCallback != null && mHandler != null) {
					mHandler.removeCallbacks(this);
					mHandler.postDelayed(this, 150);
				}
				
				if (changed && LauncherApplication.sApp.getResources().getBoolean(R.bool.apps_all_disable)) {
					LauncherApplication.appEnable(pkg, ints[0]);
//					LogPreview.show("appEnable --- " + pkg + " : " + ints[0]);
				}
			}
			break;
		}
	}
	
	@Override
	public void run() {
		if(mCallback != null) mCallback.onRefresh();
	}

	public void register() {
		if(workLooper == null) {
			HandlerThread work = new HandlerThread("app_visibility_handler");
			work.start();
			workLooper = work.getLooper();
		}
		mHandler = new Handler(workLooper);

//		mHandler.postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				final CarStates mStates = CarStates
//						.getCar(LauncherApplication.sApp);
//				mStates.getTools().addRefreshLisenter(
//						FinalMainServer.MODULE_CODE_MAIN, CustomFilter.this,
//						FinalMain.U_APP_VISIBILITY);
//				mStates.getTools().notify(FinalMainServer.MODULE_CODE_MAIN,
//						FinalMain.U_APP_VISIBILITY);
//			}
//		}, 3000);
		final CarStates mStates = CarStates
				.getCar(LauncherApplication.sApp);
		mStates.getTools().addRefreshLisenter(
				FinalMainServer.MODULE_CODE_MAIN, this,
				FinalMain.U_APP_VISIBILITY);
	}
	
	public void unregister() {
		if(workLooper != null) {
			workLooper.quit();
			mHandler = null;
		}
		final CarStates mStates = CarStates.getCar(LauncherApplication.sApp);
		mStates.getTools().removeRefreshLisenter(FinalMainServer.MODULE_CODE_MAIN, this);
	}
	
	*/
/**
	 * @param mCallback the mCallback to set
	 *//*

	public void setCallback(RefreshCallback mCallback) {
		this.mCallback = mCallback;
	}
	
	public static boolean loadCustomIcons(Context context, int res) {
		return false;
	}

	@Override
	public String getAppTitle(ComponentName app) {
		// TODO Auto-generated method stub
		return null;
	}
}
*/
