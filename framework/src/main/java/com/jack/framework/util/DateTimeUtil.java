package com.jack.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: JACK-GU
 * @Date: 2017-07-14
 * @E-Mail: 528489389@qq.com
 */
public class DateTimeUtil {
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy年MM月dd日
     */
    public static String getYMDDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(long times) {
        Date currentTime = new Date(times);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort(long times) {
        Date currentTime = new Date(times);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间
     *
     * @return 返回短时间字符串格式yyyy年MM月dd日
     */
    public static String getYMDDateShort(long times) {
        Date currentTime = new Date(times);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间
     * yyyy-MM-dd HH:mm:ss
     *
     * @return返回时间戳
     */
    public static long getLongFromStringDate(String times) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long mTime = 0;
        try {
            mTime = formatter.parse(times).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mTime;
    }

    /**
     * 获取时间yyyy-MM-dd
     *
     * @return 返回时间戳
     */
    public static long getLongFromStringDateShort(String times) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long mTime = 0;
        try {
            mTime = formatter.parse(times).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mTime;
    }

    /**
     * 获取时间
     * yyyy年MM月dd日
     *
     * @return 时间戳
     */
    public static long getLongFromYMDDateShort(String times) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        long mTime = 0;
        try {
            mTime = formatter.parse(times).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mTime;
    }
}
