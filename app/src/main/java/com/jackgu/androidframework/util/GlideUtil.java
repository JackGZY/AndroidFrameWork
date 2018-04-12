package com.jackgu.androidframework.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.enums.GlideScaleType;
import com.jackgu.androidframework.enums.GlideType;

import java.io.File;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11 16:50
 * @E-Mail: 528489389@qq.com
 * <p>
 * 图片加载类
 */

public class GlideUtil {
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
        load(obj, imageView, null, null, 0);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideScaleType 加载的方式
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片,使用设置的glideScaleType进行正常加载
     */
    public static void load(Object obj, ImageView imageView, GlideScaleType glideScaleType) {
        load(obj, imageView, glideScaleType, null, 0);
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
        load(obj, imageView, null, GlideType.CIRCLE, 0);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideScaleType 加载的方式
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,默认5dp,使用设置的glideScaleType进行正常加载
     */
    public static void loadRound(Object obj, ImageView imageView, GlideScaleType glideScaleType) {
        load(obj, imageView, glideScaleType, GlideType.ROUND, 5);
    }


    /**
     * @param obj       图片的地址，可以是int，可以是string，uri，file
     * @param imageView 需要加载的试图
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,默认5dp,使用imageview的ScaleType进行
     */
    public static void loadRound(Object obj, ImageView imageView) {
        load(obj, imageView, null, GlideType.ROUND, 5);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideScaleType 加载的方式
     * @param roundRadius    度数 dp
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,使用设置的glideScaleType进行正常加载
     */
    public static void loadRound(Object obj, ImageView imageView, GlideScaleType glideScaleType,
                                 int roundRadius) {
        load(obj, imageView, glideScaleType, GlideType.ROUND, roundRadius);
    }


    /**
     * @param obj         图片的地址，可以是int，可以是string，uri，file
     * @param imageView   需要加载的试图
     * @param roundRadius 度数 dp
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆角的图片,使用imageview的ScaleType进行
     */
    public static void loadRound(Object obj, ImageView imageView, int roundRadius) {
        load(obj, imageView, null, GlideType.ROUND, roundRadius);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideScaleType 加载的方式
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载圆形的图片,使用设置的glideScaleType进行正常加载
     */
    public static void loadCircle(Object obj, ImageView imageView, GlideScaleType glideScaleType) {
        load(obj, imageView, glideScaleType, GlideType.CIRCLE, 0);
    }


    /**
     * @param obj            图片的地址，可以是int，可以是string，uri，file
     * @param imageView      需要加载的试图
     * @param glideScaleType glide的加载图片模式，传入NULL就使用Image的ScaleType
     * @param glideType      加载图片的方法，正常加载或者圆形或者圆角
     * @param roundRadius    加载圆角的时候的度数 dp
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 加载图片
     */
    public static void load(Object obj, ImageView imageView, GlideScaleType glideScaleType,
                            GlideType glideType, int roundRadius) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, imageView);
        if (drawableTypeRequest == null) {
            //如果返回来的是空的，我们就用空的加载
            drawableTypeRequest = getDrawableTypeRequest(AppConfig.IMAGE_EMPTY, imageView);
        }

        if (drawableTypeRequest != null) {
            setType(drawableTypeRequest, glideScaleType);

            //判断如果传入的glideType是空的，就正常加载
            if (glideType == null || glideType == GlideType.NORMAL) {
                //什么都不做
            } else {
                if (glideType == GlideType.CIRCLE) {
                    drawableTypeRequest.transform(new BitmapTransformation[]{new
                            GlideCircleTransform(imageView.getContext())});
                } else if (glideType == GlideType.ROUND) {
                    drawableTypeRequest.transform(new BitmapTransformation[]{new
                            GlideRoundTransform(imageView.getContext(), roundRadius)});
                }
            }


            drawableTypeRequest
                    .dontAnimate()
                    .placeholder(AppConfig.IMAGE_LOADING)
                    .error(AppConfig.IMAGE_ERROR)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //所有尺寸都缓存到磁盘
                    .into(imageView);
        }
    }


    /**
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 设置加载的模式，如果传入的type是null，就使用ImageView的
     */
    private static void setType(DrawableTypeRequest drawableTypeRequest,
                                GlideScaleType type) {
        if (drawableTypeRequest != null && type != null) {
            if (type == GlideScaleType.centerCrop) {
                drawableTypeRequest.centerCrop();
            } else if (type == GlideScaleType.fitCenter) {
                drawableTypeRequest.fitCenter();
            }

        }
    }

    /**
     * 获得DrawableTypeRequest，通过传入的obj自动判断
     */
    @Nullable
    private static DrawableTypeRequest getDrawableTypeRequest(Object obj, View view) {
        if (view == null || obj == null) return null;
        Context context = view.getContext();
        RequestManager manager = Glide.with(context);
        DrawableTypeRequest drawableTypeRequest = null;
        if (obj instanceof String) {
            drawableTypeRequest = manager.load((String) obj);
        } else if (obj instanceof Integer) {
            drawableTypeRequest = manager.load((Integer) obj);
        } else if (obj instanceof Uri) {
            drawableTypeRequest = manager.load((Uri) obj);
        } else if (obj instanceof File) {
            drawableTypeRequest = manager.load((File) obj);
        }
        return drawableTypeRequest;
    }

    /**
     * 转换为圆的
     */
    private static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int
                outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            } else {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;
                Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
                Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
                if (result == null) {
                    result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                }

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode
                        .CLAMP));
                paint.setAntiAlias(true);
                float r = (float) size / 2.0F;
                canvas.drawCircle(r, r, r, paint);
                return result;
            }
        }

        public String getId() {
            return this.getClass().getName();
        }
    }

    /**
     * 转成圆角
     */
    private static class GlideRoundTransform extends BitmapTransformation {
        float radius = 0f;

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int
                outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap toTransform) {
            if (toTransform == null) return null;

            Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap
                    .Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(),
                        Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP,
                    BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, toTransform.getWidth(), toTransform.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return this.getClass().getName();
        }
    }

}
