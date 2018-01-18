package com.jackgu.androidframework.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.jackgu.androidframework.MyApplication;

public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     */
    public static int getHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getHeight();
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 获取屏幕宽度 px
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public static int getDeviceWidth() {
        WindowManager wm = (WindowManager) MyApplication.getApplication().getSystemService
                (Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }


    /**
     * 获取屏幕宽度 px
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public static int getDeviceHeight() {
        WindowManager wm = (WindowManager) MyApplication.getApplication().getSystemService
                (Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }
}
