package com.jackgu.androidframework;

import android.app.Application;

import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.util.SharedPreferencesUtil;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 应用程序
 */

public class MyApplication extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //初始化
        SharedPreferencesUtil.init(application, AppConfig.SHAREDPREFERNCES_NAME);
    }

    public static Application getApplication() {
        return application;
    }
}
