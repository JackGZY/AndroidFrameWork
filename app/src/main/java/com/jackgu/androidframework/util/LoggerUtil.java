package com.jackgu.androidframework.util;


import com.jackgu.androidframework.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 日志类
 *
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 */
public class LoggerUtil {

    public static void i(String msg) {
        if (BuildConfig.DEBUG)
            Logger.i(msg);
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG)
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG)
            Logger.e(msg);
    }

    public static void json(String jsonStr) {
        if (BuildConfig.DEBUG)
            Logger.json(jsonStr);
    }

}
