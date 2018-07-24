package com.jack.framework.util;

import android.content.res.Resources;
import android.util.TypedValue;

import com.jack.framework.FrameWorkApplication;


/**
 * 获取资源
 *
 * @Author: JACK-GU
 * @Date: 2018/4/4 15:57
 * @E-Mail: 528489389@qq.com
 */
public class ResourcesUtil {
    private static Resources resources;

    /**
     * 初始化，建议在Application
     *
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:14
     * @E-Mail: 528489389@qq.com
     */
    public static void init() {
        if (resources == null) {
            resources = FrameWorkApplication.getApplication().getResources();
        }
    }

    /**
     * 通过id获得string
     *
     * @param id 资源ID
     * @return string id对应的string
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:15
     * @E-Mail: 528489389@qq.com
     */
    public static String getString(int id) {
        return resources.getString(id);
    }

    /**
     * 获得Resources对象
     *
     * @return resources resources对象
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:16
     * @E-Mail: 528489389@qq.com
     */
    public static Resources getResources() {
        return resources;
    }

    /**
     * 获得颜色
     *
     * @param id 资源ID
     * @return color 返回颜色，可以直接设置
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:17
     * @E-Mail: 528489389@qq.com
     */
    public static int getColor(int id) {
        return resources.getColor(id);
    }

    /**
     * 获得像素，如果是sp不建议这个获取有问题，sp使用#getDimensionValue(int)
     *
     * @param id 资源ID
     * @return px 返回像素,如果xml中单位是px，返回是px，如果是dp或者sp都会转成px
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:17
     * @E-Mail: 528489389@qq.com
     * @see #getDimensionValue(int)
     */
    public static float getPx(int id) {
        return resources.getDimension(id);
    }

    /**
     * 获取Dimension的值，单位不变，值不变，自己转换
     *
     * @return
     * @Author: JACK-GU
     * @Date: 2018-07-19 09:11
     * @E-Mail: 528489389@qq.com
     */
    public static float getDimensionValue(int id) {
        TypedValue mTmpValue = new TypedValue();
        resources.getValue(id, mTmpValue, true);
        return TypedValue.complexToFloat(mTmpValue.data);
    }
}
