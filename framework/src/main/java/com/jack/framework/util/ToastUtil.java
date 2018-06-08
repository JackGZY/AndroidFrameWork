package com.jack.framework.util;

import android.widget.Toast;

import com.jack.framework.FrameWorkApplication;


/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * Toast显示
 */
public class ToastUtil {
    /**
     * @Author: JACK-GU
     * @Date: 2018/1/11
     * @E-Mail: 528489389@qq.com
     * 显示短消息
     */
    public static void showShortMessage(String message) {
        Toast.makeText(FrameWorkApplication.getApplication(),message,Toast.LENGTH_SHORT).show();
    }

    /**
     * @Author: JACK-GU
     * @Date: 2018/1/11
     * @E-Mail: 528489389@qq.com
     * 显示长消息
     */
    public static void showLongMessage(String message) {
        Toast.makeText(FrameWorkApplication.getApplication(),message,Toast.LENGTH_LONG).show();
    }
}
