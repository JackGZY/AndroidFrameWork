package com.jackgu.androidframework;

import android.app.Activity;
import android.os.Bundle;

import com.jack.framework.FrameWorkApplication;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 应用程序
 */

public class MyApplication extends FrameWorkApplication {
    //这个可以管理每个activity的生命在这里
    private ActivityLifecycleCallbacks activityLifecycleCallbacks
            = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override
    public String getMainModuleID() {
        return BuildConfig.APPLICATION_ID;
    }

}
