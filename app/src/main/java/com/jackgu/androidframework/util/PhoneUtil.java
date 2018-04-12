package com.jackgu.androidframework.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.DisplayMetrics;


import com.jackgu.androidframework.MyApplication;

import java.util.Locale;

/**
 * 这里定义一些方法，获取一些手机系统信息
 *
 * @Author: JACK-GU
 * @Date: 2018/4/9 10:10
 * @E-Mail: 528489389@qq.com
 */

public class PhoneUtil {
    private static volatile int statusBarHeight = 0;
    //网络状态的关键码
    private static final int NETWORK_NO_CONNECTION = 0;
    private static final int NETWORK_WIFI = 1;
    private static final int NETWORK_MOBILE = 2;


    /**
     * 获取状态栏的高度
     *
     * @return statusBarHeight 状态栏高度
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:36
     * @E-Mail: 528489389@qq.com
     */
    public static int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            //反射性能低，所以，获取过就不要再获取了
            try {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                int height = Integer.parseInt(clazz.getField("status_bar_height")
                        .get(object).toString());
                statusBarHeight = ResourcesUtil.getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    /**
     * 获取屏幕的高度
     *
     * @return ScreenWidth 屏幕的高度
     * @Author: JACK-GU
     * @Date: 2018/4/10 15:57
     * @E-Mail: 528489389@qq.com
     */
    public static int getScreenHeight() {
        DisplayMetrics dm = ResourcesUtil.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * 获取屏幕的宽度
     *
     * @return ScreenWidth 屏幕的宽度
     * @Author: JACK-GU
     * @Date: 2018/4/10 15:57
     * @E-Mail: 528489389@qq.com
     */
    public static int getScreenWidth() {
        DisplayMetrics dm = ResourcesUtil.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取包名
     *
     * @return packageName 应用包名
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:37
     * @E-Mail: 528489389@qq.com
     */
    public static String getPackageName() {
        return MyApplication.getApplication().getPackageName();
    }

    /**
     * 获取版本名
     *
     * @return VersionName 应用的版本名
     * @Author: JACK-GU
     * @Date: 2018/4/10 15:50
     * @E-Mail: 528489389@qq.com
     */
    public static String getVersionName() {
        return getPackageInfo(MyApplication.getApplication()).versionName;
    }

    /**
     * 获取版本号
     *
     * @return VersionCode 应用的版本号
     * @Author: JACK-GU
     * @Date: 2018/4/10 15:50
     * @E-Mail: 528489389@qq.com
     */
    public static int getVersionCode() {
        return getPackageInfo(MyApplication.getApplication()).versionCode;
    }


    /**
     * 获取程序信息
     *
     * @return PackageInfo 包详细信息
     * @Author: JACK-GU
     * @Date: 2018/4/10 15:48
     * @E-Mail: 528489389@qq.com
     */
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

    /**
     * 获取当前手机的网络状态,三种情况：
     * <p>
     * 1. 网络不可用 NETWORK_NO_CONNECTION
     * </p>
     * <p>
     * 2. 网络可用，wifi NETWORK_WIFI
     * </p>
     * <p>
     * 3. 网络可用，手机流量 NETWORK_MOBILE
     * </p>
     * </p>
     *
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:08
     * @E-Mail: 528489389@qq.com
     */
    public static int getNetWorkInfo() {
        int code = NETWORK_NO_CONNECTION;
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication
                .getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            //说明是没网络的
            LoggerUtil.e("网络未连接");
            code = NETWORK_NO_CONNECTION;
        } else {
            if (!networkInfo.isAvailable()) {
                //不可用，返回未连接
                LoggerUtil.e("网络不可用");
                code = NETWORK_NO_CONNECTION;
            } else {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    code = NETWORK_WIFI;
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    code = NETWORK_MOBILE;
                }
            }

        }

        return code;
    }

    /**
     * 获取手机的mac地址
     *
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:25
     * @E-Mail: 528489389@qq.com
     */
    public static String getMacAddress() {
        String macAddress;
        WifiManager wifiManager =
                (WifiManager) MyApplication.getApplication().getApplicationContext()
                        .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        if (!wifiManager.isWifiEnabled()) {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        macAddress = info.getMacAddress();
        return macAddress;
    }

    /**
     * 获取当前手机系统语言
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:31
     * @E-Mail: 528489389@qq.com
     */

    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:31
     * @E-Mail: 528489389@qq.com
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:31
     * @E-Mail: 528489389@qq.com
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:31
     * @E-Mail: 528489389@qq.com
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
}
