package com.jackgu.androidframework.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 获得Android的版本号和版本名
 * 
 * @author GZY
 * @version 1.0
 * @data 2015年9月23日 下午4:40:54
 */
public class VersionUtil {
	public static Context mContext;

	public static void init(Context context) {
		mContext = context;
	}

	/**
	 * 获得版本名称
	 */
	public static String getVersionName() {
		return getPackageInfo(mContext).versionName;
	}

	/**
	 * 获得版本号
	 */
	public static int getVersionCode() {
		return getPackageInfo(mContext).versionCode;
	}

	private static PackageInfo getPackageInfo(Context context) {
		PackageInfo pi = null;

		try {
			PackageManager pm = context.getPackageManager();
			pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);

			return pi;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pi;
	}
}
