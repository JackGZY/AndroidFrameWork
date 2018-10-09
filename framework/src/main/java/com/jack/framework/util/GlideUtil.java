package com.jack.framework.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jack.framework.R;
import com.jack.framework.enums.GlideType;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11 16:50
 * @E-Mail: 528489389@qq.com
 * <p>
 * 图片加载类
 */

public class GlideUtil {
    //配置加载的图片占位符
    private static int IMAGE_ERROR = R.mipmap.ic_error;
    private static int IMAGE_EMPTY = R.mipmap.ic_empty;
    private static int IMAGE_LOADING = R.mipmap.ic_loading;
    //圆形的时候
    private static int IMAGE_ERROR_CIRCLE = R.mipmap.ic_error;
    private static int IMAGE_EMPTY_CIRCLE = R.mipmap.ic_empty;
    private static int IMAGE_LOADING_CIRCLE = R.mipmap.ic_loading;
    //圆角的时候
    private static int IMAGE_ERROR_ROUND = R.mipmap.ic_error;
    private static int IMAGE_EMPTY_ROUND = R.mipmap.ic_empty;
    private static int IMAGE_LOADING_ROUND = R.mipmap.ic_loading;

    /**
     * @param obj       图片的地址，可以是int，可以是string，uri，file
     * @param imageView 需要加载的试图
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片,使用imageview的ScaleType进行正常加载
     */
    public static void load(Object obj, ImageView imageView) {
        load(obj, imageView, null, 0, null);
    }


    /**
     * @param obj       图片的地址，可以是int，可以是string，uri，file
     * @param imageView 需要加载的试图
     * @param glideType 加载的方式
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片,使用imageview的ScaleType进行正常加载
     */
    public static void load(Object obj, ImageView imageView, GlideType glideType) {
        load(obj, imageView, glideType, 0, null);
    }

    /**
     * @param obj       图片的地址，可以是int，可以是string，uri，file
     * @param imageView 需要加载的试图
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆形的图片,使用imageview的ScaleType进行
     */
    public static void loadCircle(Object obj, ImageView imageView) {
        load(obj, imageView, GlideType.CIRCLE, 0, null);
    }


    /**
     * @param obj       图片的地址，可以是int，可以是string，uri，file
     * @param imageView 需要加载的试图
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,默认5dp,使用设置的glideScaleType进行正常加载
     */
    public static void loadRound(Object obj, ImageView imageView) {
        load(obj, imageView, GlideType.ROUND, 5, null);
    }

    /**
     * @param obj         图片的地址，可以是int，可以是string，uri，file
     * @param imageView   需要加载的试图
     * @param roundRadius 加载圆角的时候的度数 dp
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,默认5dp,使用设置的glideScaleType进行正常加载
     */
    public static void loadRound(Object obj, ImageView imageView, int roundRadius) {
        load(obj, imageView, GlideType.ROUND, roundRadius, null);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param changeCallBack 这里面可以进行自定义的操作：可以修改加载动画,修改占位符，
     *                       修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片,使用imageview的ScaleType进行正常加载
     */
    public static void load(Object obj, ImageView imageView
            , ChangeCallBack changeCallBack) {
        load(obj, imageView, null, 0, changeCallBack);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideType      加载的方式
     * @param changeCallBack 这里面可以进行自定义的操作：可以修改加载动画,修改占位符，
     *                       修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片,使用imageview的ScaleType进行正常加载
     */
    public static void load(Object obj, ImageView imageView, GlideType glideType
            , ChangeCallBack changeCallBack) {
        load(obj, imageView, glideType, 0, changeCallBack);
    }

    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param changeCallBack 这里面可以进行自定义的操作：可以修改加载动画,修改占位符，
     *                       修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆形的图片,使用imageview的ScaleType进行
     */
    public static void loadCircle(Object obj, ImageView imageView
            , ChangeCallBack changeCallBack) {
        load(obj, imageView, GlideType.CIRCLE, 0, changeCallBack);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param changeCallBack 这里面可以进行自定义的操作：可以修改加载动画,修改占位符，
     *                       修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,默认5dp,使用设置的glideScaleType进行正常加载
     */
    public static void loadRound(Object obj, ImageView imageView
            , ChangeCallBack changeCallBack) {
        load(obj, imageView, GlideType.ROUND, 5, changeCallBack);
    }

    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param roundRadius    加载圆角的时候的度数 dp
     * @param changeCallBack 这里面可以进行自定义的操作：可以修改加载动画,修改占位符，
     *                       修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,默认5dp,使用设置的glideScaleType进行正常加载
     */
    public static void loadRound(Object obj, ImageView imageView, int roundRadius
            , ChangeCallBack changeCallBack) {
        load(obj, imageView, GlideType.ROUND, roundRadius, changeCallBack);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideType      加载图片的方法，正常加载或者圆形或者圆角
     * @param roundRadius    加载圆角的时候的度数 dp
     * @param changeCallBack 这里面可以进行自定义的操作：可以修改加载动画,修改占位符，
     *                       修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片
     */
    public static void load(Object obj, ImageView imageView, GlideType glideType, int roundRadius,
                            ChangeCallBack changeCallBack) {
        RequestBuilder requestBuilder = Glide.with(imageView).load(obj);

        RequestOptions requestOptions = new RequestOptions();
        //设置缓存
        requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);//只需要缓存原始图片
        if (glideType != null) {
            if (glideType == GlideType.centerCrop) {
                requestOptions.centerCrop();
            } else if (glideType == GlideType.centerInside) {
                requestOptions.centerInside();
            } else if (glideType == GlideType.fitCenter) {
                requestOptions.fitCenter();
            } else if (glideType == GlideType.CIRCLE) {
                requestOptions.circleCrop();
            } else if (glideType == GlideType.ROUND) {
                requestOptions.transform(new RoundedCornersTransformation(DensityUtil.dip2px
                        (roundRadius), 0));
            }
        }

        if (glideType == GlideType.CIRCLE) {
            requestOptions.placeholder(IMAGE_LOADING_CIRCLE);
            requestOptions.error(IMAGE_ERROR_CIRCLE);
            requestOptions.fallback(IMAGE_EMPTY_CIRCLE); //为空的时候
        } else if (glideType == GlideType.ROUND) {
            requestOptions.placeholder(IMAGE_LOADING_ROUND);
            requestOptions.error(IMAGE_ERROR_ROUND);
            requestOptions.fallback(IMAGE_EMPTY_ROUND); //为空的时候
        } else {
            requestOptions.placeholder(IMAGE_LOADING);
            requestOptions.error(IMAGE_ERROR);
            requestOptions.fallback(IMAGE_EMPTY); //为空的时候
        }

        if (changeCallBack != null) {
            changeCallBack.changeCallBack(requestOptions, requestBuilder);
        }

        requestBuilder.apply(requestOptions).into(imageView);
    }

    public interface ChangeCallBack {
        /**
         * @param requestBuilder 可以修改加载动画
         * @param requestOptions 修改占位符，修改变化这些，因为变化是不作用在占位符上的(主要是圆角的和原型的图片)
         * @Author: JACK-GU
         * @E-Mail: 528489389@qq.com
         */
        void changeCallBack(RequestOptions requestOptions, RequestBuilder requestBuilder);
    }
}
