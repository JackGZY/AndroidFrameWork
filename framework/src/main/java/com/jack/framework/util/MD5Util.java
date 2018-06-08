package com.jack.framework.util;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 *
 * @Author: JACK-GU
 * @Date: 2018/4/10 16:40
 * @E-Mail: 528489389@qq.com
 */

public class MD5Util {
    /**
     * MD5加密
     *
     * @param string 需要加密的字符串
     * @return md5 加密后的字符串
     * @Author: JACK-GU
     * @Date: 2018/4/10 16:40
     * @E-Mail: 528489389@qq.com
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
