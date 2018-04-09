package com.jackgu.androidframework;

import android.app.Application;

import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.util.SharedPreferencesUtil;
import com.jackgu.androidframework.view.ClassicsFooter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

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

        initSmartRefreshLayout();
    }

    public static Application getApplication() {
        return application;
    }

    /***
     * 初始化 SmartRefreshLayout
     *
     * */
    public void initSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter
                (context));
    }
}
