package com.jack.framework.enums;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * <p>
 * 设置加载图片的方式，不作处理，圆形的或者是圆角,这个是只作用于加载的资源，不作用于imageView，也就是说占位符是不影响的
 */

public enum GlideType {
    NORMAL,
    CIRCLE,
    ROUND,
    centerCrop,
    fitCenter,
    centerInside,
}
