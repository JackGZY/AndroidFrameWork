package com.jack.framework;

import android.app.Application;

import com.jack.framework.config.AppConfig;
import com.jack.framework.util.SharedPreferencesUtil;
import com.jack.framework.view.ClassicsFooter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 应用程序
 */

public abstract class FrameWorkApplication extends Application {
    private static FrameWorkApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化
        SharedPreferencesUtil.init(application, AppConfig.SHAREDPREFERNCES_NAME);

        initSmartRefreshLayout();
    }

    /**
     * 获取app这个Module的application id
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public abstract String getMainModuleID();

    public static FrameWorkApplication getApplication() {
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
