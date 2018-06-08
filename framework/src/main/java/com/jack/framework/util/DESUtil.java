package com.jack.framework.util;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;


/**
 * -
 *
 * @Author: JACK-GU
 * @Date: 2018/5/17 15:06
 * @E-Mail: 528489389@qq.com
 */
public class DESUtil {
    private static String key = "EFeYa1QmKkw=";

    /**
     * 生成密钥
     *
     * @throws Exception
     */
    public static String initKey() throws Exception {
        //密钥生成器
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        //初始化密钥生成器
        keyGen.init(56);
        //生成密钥
        SecretKey secretKey = keyGen.generateKey();

        String encodedKey = Base64.encode(secretKey.getEncoded());
        return encodedKey;
    }

    /**
     * 加密
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static String encryptDES(String s) throws Exception {
        //获得密钥
        SecretKey secretKey = new SecretKeySpec(Base64.decode(key), "DES");
        //Cipher完成加密
        Cipher cipher = Cipher.getInstance("DES");
        //初始化cipher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //加密
        byte[] n = s.getBytes("utf-8");
        byte[] encrypt = cipher.doFinal(n);

        return Base64.encode(encrypt);
    }

    /**
     * 解密
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static String decryptDES(String s) throws Exception {
        //恢复密钥
        SecretKey secretKey = new SecretKeySpec(Base64.decode(key), "DES");
        //Cipher完成解密
        Cipher cipher = Cipher.getInstance("DES");
        //初始化cipher
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //解密
        byte[] plain = cipher.doFinal(new BASE64Decoder().decodeBuffer(s));
        return new String(plain, "utf-8");
    }
}
