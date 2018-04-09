package com.jackgu.androidframework.util;


import com.jackgu.androidframework.MyApplication;

/**
 * 这里定义一些方法，获取一些系统信息
 *
 * @Author: JACK-GU
 * @Date: 2018/4/9 10:10
 * @E-Mail: 528489389@qq.com
 */

public class PhoneUtil {
    /**
     * 获取状态栏的高度
     *
     * @return statusBarHeight 状态栏高度
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:36
     * @E-Mail: 528489389@qq.com
     */
    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = ResourcesUtil.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusBarHeight;
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


}
