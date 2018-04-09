package com.jackgu.androidframework.util;

import android.content.res.Resources;

import com.linc.linc.MyApplication;

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
            resources = MyApplication.getApplication().getResources();
        }
    }

    /**
     * 通过id获得string
     *
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
     * @return color 返回颜色，可以直接设置
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:17
     * @E-Mail: 528489389@qq.com
     */
    public static int getColor(int color) {
        return resources.getColor(color);
    }

}
