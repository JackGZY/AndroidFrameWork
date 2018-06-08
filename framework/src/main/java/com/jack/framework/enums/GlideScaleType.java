package com.jack.framework.enums;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * 加载图片的类型
 *
 * 如果我们没有给Glide手动添加centerCrop和fitCenter方法，Glide会根据传入的ImageView的scaleType来自己去执行centerCrop或者fitCenter方法
 */
public enum GlideScaleType {
    //即缩放图像让它填充到ImageView界限内并且裁剪额外的部分。ImageView会被完全填充，但图像可能不会完整显示。
    centerCrop,
    //即缩放图像让图像都测量出来等于或小于ImageView的边界范围。该图像将会完全显示，但可能不会填满整个ImageView。
    fitCenter
}
