package com.jackgu.androidframework.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity栈
 *
 * @Author: JACK-GU
 * @Date: 2018/3/29 14:42
 * @E-Mail: 528489389@qq.com
 */
public class ActivityStackUtil {
    private List<Activity> activities;
    private static volatile ActivityStackUtil activityStackUtil;

    private ActivityStackUtil() {
        activities = new ArrayList<>();
    }


    /**
     * 获取实例，单列类
     *
     * @Author: JACK-GU
     * @Date: 2018/3/29 14:48
     * @E-Mail: 528489389@qq.com
     */
    public static ActivityStackUtil getInstance() {
        if (activityStackUtil == null) {
            synchronized (ActivityStackUtil.class) {
                if (activityStackUtil == null) {
                    activityStackUtil = new ActivityStackUtil();
                }
            }
        }

        return activityStackUtil;
    }

    /**
     * 加入新的activity
     *
     * @param activity 加入的activity
     * @Author: JACK-GU
     * @Date: 2018/3/29 14:48
     * @E-Mail: 528489389@qq.com
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity 移除activity
     * @Author: JACK-GU
     * @Date: 2018/3/29 14:48
     * @E-Mail: 528489389@qq.com
     */
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 移除activity
     *
     * @param index 移除activity的小标
     * @Author: JACK-GU
     * @Date: 2018/3/29 14:48
     * @E-Mail: 528489389@qq.com
     */
    public void removeActivity(int index) {
        if (index < activities.size())
            activities.remove(index);
    }


    /**
     * 获得最后一个，栈顶的activity
     *
     * @return activity 如果没有，是null
     * @Author: JACK-GU
     * @Date: 2018/3/29 14:51
     * @E-Mail: 528489389@qq.com
     */
    public Activity getLastActivity() {
        if (activities.size() <= 0) {
            return null;
        }
        return activities.get(activities.size() - 1);
    }


    /**
     * 获得activity
     *
     * @param index 获得activity的小标
     * @return activity 如果没有，是null
     * @Author: JACK-GU
     * @Date: 2018/3/29 14:48
     * @E-Mail: 528489389@qq.com
     */
    public Activity getIndexActivity(int index) {
        try {
            return activities.get(index);
        } catch (Exception e) {
            return null;
        }
    }

}
