package com.jackgu.androidframework.util.network;

/**
 * 自定义网络请求异常，是针对code的
 *
 * @Author: JACK-GU
 * @Date: 2018/4/3 16:47
 * @E-Mail: 528489389@qq.com
 */
public class CodeException extends Exception {
    private int code;

    public int getCode() {
        return code;
    }

    public CodeException(String msg, int code) {
        super(msg);
        this.code = code;
    }
}
