package com.jack.framework.util;

import android.view.View;

/**
 * -
 *
 * @Author: JACK-GU
 * @Date: 2018-07-17 14:41
 * @E-Mail: 528489389@qq.com
 */
public class ViewUtil {
    /**
     * 获取view的宽度
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static int getViewWidth(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredWidth(); // 获取宽度
    }


    /**
     * 获取view的宽度
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static int getViewHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight(); // 获取宽度
    }
}
