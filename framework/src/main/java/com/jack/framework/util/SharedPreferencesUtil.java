package com.jack.framework.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences处理工具类
 *
 * @author GZY
 */
public class SharedPreferencesUtil {
    public static SharedPreferences preferences = null;

    /**
     * 在调用SharedPreferencesUtil里的方法之前必须进行初始化
     *
     * @param context
     * @param name    SharedPreferences文件名
     */
    public static void init(Context context, String name) {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 默认值false
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static void putInt(String key, int value) {
        preferences.edit().putInt(key, value).commit();
    }

    /**
     * 默认值0
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public static void putFloat(String key, Float value) {
        preferences.edit().putFloat(key, value).commit();
    }

    /**
     * 默认值0f
     *
     * @param key
     * @return
     */
    public static Float getFloat(String key) {
        return preferences.getFloat(key, 0f);
    }

    public static void putLong(String key, Long value) {
        preferences.edit().putLong(key, value).commit();
    }

    /**
     * 默认值0L
     *
     * @param key
     * @return
     */
    public static Long getLong(String key) {
        return preferences.getLong(key, 0L);
    }

    public static void putString(String key, String value) {
        preferences.edit().putString(key, value).commit();
    }

    /**
     * 默认值""
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String value) {
        return preferences.getString(key, value);
    }

    /**
     * 把第一个字符大写
     */
    private static String getFirstUpString(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static String getName(String className, String fieldName) {
        return className + "." + fieldName;
    }
}
