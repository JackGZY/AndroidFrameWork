package com.jack.framework.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jack.framework.R;
import com.jack.framework.util.ViewUtil;

import java.lang.reflect.Field;

/**
 * 要加在按钮外面，就可以不写选择器了，但是子view一定要有背景色,不然默认白色
 * 默认圆角0dp，如果设置了圆角，优先，如果设置了四个角的，四个角的值优先，如果都没设置，会自动读取子控件的圆角
 * <p>
 * 5.0默认开启水波纹，使用圆形水波纹的时候，ripple的作用范围和子控件相同，而不是ButtonLayout。
 * 需要确定一点就是颜色需要和父控件一直才行
 * ,RADIUS对其无效果,会自动适应的
 * <p>
 * 如果是圆形的水波纹的时候，建议子控件写死，不然那会出问题
 *
 * @see #setCircle(boolean)
 */
public class ButtonLayout extends FrameLayout {
    public static final int RIPPLE_TYPE_CIRCLE = 0; //圆形
    public static final int RIPPLE_TYPE_RECTANGLE = 1; //矩形
    private View childView;
    private int DEFAULT_RADIUS = 0;
    private int DEFAULT_RADIUS_LEFT_TOP = -1;
    private int DEFAULT_RADIUS_LEFT_BOTTOM = -1;
    private int DEFAULT_RADIUS_RIGHT_BOTTOM = -1;
    private int DEFAULT_RADIUS_RIGHT_TOP = -1;
    private static final float REDUCE = 1.2f;
    //是否开启水波纹效果
    private boolean enableRipple = false;
    private int rippleType = RIPPLE_TYPE_RECTANGLE;
    private int childColor = 0;
    private int defaultColor = 0; //默认的合成颜色，优先级小于获取到的子控件的背景色
    private int radius = 0;

    public ButtonLayout(Context context, int rippleType) {
        this(context, null, 0);
        this.rippleType = rippleType;
        init(context, null);
    }

    public ButtonLayout(Context context, int rippleType, boolean enableRipple) {
        this(context, null, 0);
        this.rippleType = rippleType;
        this.enableRipple = enableRipple;
        init(context, null);
    }

    public void setEnableRipple(boolean enableRipple) {
        this.enableRipple = enableRipple;
    }

    public void setRippleType(int rippleType) {
        this.rippleType = rippleType;
    }

    public ButtonLayout(Context context) {
        this(context, null, 0);
        init(context, null);
    }

    public ButtonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public ButtonLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        enableRipple = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

        if (attrs == null) {
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonLayout);
        DEFAULT_RADIUS = a.getDimensionPixelSize(R.styleable
                .ButtonLayout_buttonLayoutRippleRoundedCorners, -1);

        DEFAULT_RADIUS_LEFT_TOP = a.getDimensionPixelSize(R.styleable
                .ButtonLayout_buttonLayoutRippleRoundedCornersLeftTop, -1);
        DEFAULT_RADIUS_LEFT_BOTTOM = a.getDimensionPixelSize(R.styleable
                .ButtonLayout_buttonLayoutRippleRoundedCornersLeftBottom, -1);
        DEFAULT_RADIUS_RIGHT_TOP = a.getDimensionPixelSize(R.styleable
                .ButtonLayout_buttonLayoutRippleRoundedCornersRightTop, -1);
        DEFAULT_RADIUS_RIGHT_BOTTOM = a.getDimensionPixelSize(R.styleable
                .ButtonLayout_buttonLayoutRippleRoundedCornersRightBottom, -1);
        enableRipple = a.getBoolean(R.styleable.ButtonLayout_buttonLayoutEnableRipple,
                enableRipple);
        defaultColor = a.getColor(R.styleable.ButtonLayout_buttonLayoutDefaultColor,
                defaultColor);
        rippleType = a.getInt(R.styleable.ButtonLayout_buttonLayoutRippleType,
                rippleType);
        a.recycle();
    }


    @Override
    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ButtonLayout can host only one child");
        }
        super.addView(child, index, params);

        childView = child;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /**************************************************************************************
         *  页面绘制完成后，如果是圆形的水波纹，需要始终drawable的幅度达到效果 by JACK-GU 2018-07-18 10:55
         **************************************************************************************/
        Looper.myQueue().addIdleHandler(() -> {
            if (enableRipple && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                    && rippleType == RIPPLE_TYPE_CIRCLE
                    && childView != null) {
                //有的时候没有自定大小，我们就需要，在测算一下，可能以前的没有宽度和高度，判断一下
                if (radius <= 0)
                    setCircle(true);
            }
            return false;
        });
    }

    private void setFourCorners(float r) {
        if (DEFAULT_RADIUS_LEFT_TOP < 0) {
            DEFAULT_RADIUS_LEFT_TOP = (int) r;
        }
        if (DEFAULT_RADIUS_RIGHT_TOP < 0) {
            DEFAULT_RADIUS_RIGHT_TOP = (int) r;
        }
        if (DEFAULT_RADIUS_RIGHT_BOTTOM < 0) {
            DEFAULT_RADIUS_RIGHT_BOTTOM = (int) r;
        }
        if (DEFAULT_RADIUS_LEFT_BOTTOM < 0) {
            DEFAULT_RADIUS_LEFT_BOTTOM = (int) r;
        }
    }

    public void setRadius(int radius) {
        DEFAULT_RADIUS = radius;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setLeftTopRadius(int radius) {
        DEFAULT_RADIUS_LEFT_TOP = radius;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setLeftBottomRadius(int radius) {
        DEFAULT_RADIUS_LEFT_BOTTOM = radius;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setRightTopRadius(int radius) {
        DEFAULT_RADIUS_RIGHT_TOP = radius;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setRightBottomRadius(int radius) {
        DEFAULT_RADIUS_RIGHT_BOTTOM = radius;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setSelector() throws NoSuchFieldException, IllegalAccessException {
        if (childView == null) {
            return;
        }

        setDefault();
    }

    private void setRipple() throws NoSuchFieldException, IllegalAccessException {
    }

    private void setRadiusFromXml() {
        //如果默认的有，那就需要设置为默认的
        if (DEFAULT_RADIUS >= 0) {
            //说明xml中设置了的，所以要为四个角判断一波
            setFourCorners(DEFAULT_RADIUS);
        }
    }


    private void setDefault() throws NoSuchFieldException, IllegalAccessException {
        setRadiusFromXml();
        StateListDrawable sd = new StateListDrawable();

        //获取背景色
        Drawable drawable = childView.getBackground();
        int color = 0;
        if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            Class gradientDrawableClass = (Class) gradientDrawable.getClass();

            //这个说明可能有度数哦，我们拿到四个角的度数，然后设置，当然优先级别没有在xml中高
            Field mGradientStateField = gradientDrawableClass.getDeclaredField
                    ("mGradientState");
            mGradientStateField.setAccessible(true);
            Object object = mGradientStateField.get(gradientDrawable);//这个是内部的类，所以没法，先用这个存着

            //在反射拿到里面的数组
            Field mRadiusArrayField = object.getClass().getDeclaredField("mRadiusArray");
            mRadiusArrayField.setAccessible(true);
            float[] mRadiusArray = (float[]) mRadiusArrayField.get(object);
            if (mRadiusArray != null) {
                //这里我们拿到的是一个8个长度的数组，分别是左上，右上，右下，左下，两个一起的，还是try，免得溢出了数组
                try {
                    if (DEFAULT_RADIUS_LEFT_TOP < 0) {
                        DEFAULT_RADIUS_LEFT_TOP = (int) mRadiusArray[0];
                    }
                    if (DEFAULT_RADIUS_RIGHT_TOP < 0) {
                        DEFAULT_RADIUS_RIGHT_TOP = (int) mRadiusArray[2];
                    }
                    if (DEFAULT_RADIUS_RIGHT_BOTTOM < 0) {
                        DEFAULT_RADIUS_RIGHT_BOTTOM = (int) mRadiusArray[4];
                    }
                    if (DEFAULT_RADIUS_LEFT_BOTTOM < 0) {
                        DEFAULT_RADIUS_LEFT_BOTTOM = (int) mRadiusArray[6];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //看看圆角的弧度
                Field mRadiusField = object.getClass().getDeclaredField("mRadius");
                mRadiusField.setAccessible(true);
                float mRadius = (float) mRadiusField.get(object);
                //如果是0就不管了
                if (mRadius > 0) {
                    setFourCorners(mRadius);
                }
            }
            try {
                //拿到颜色数组
                Field mSolidColors = object.getClass()
                        .getDeclaredField("mSolidColors");
                ColorStateList colorStateList = (ColorStateList) mSolidColors.get(object);
                if (colorStateList == null) {
                    color = Color.parseColor("#FFFFFF");
                } else {
                    color = getColor(colorStateList);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                //说明是5.0的，拿颜色换个方法
                Field field = gradientDrawableClass.getDeclaredField("mFillPaint");
                field.setAccessible(true);
                Paint paint = (Paint) field.get(gradientDrawable);
                color = paint.getColor();
            }
        } else if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            color = colorDrawable.getColor();
        } else if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
        }


        //如果没有color，看看默认的
        if (color == 0 && defaultColor != 0) {
            color = defaultColor;
        }

        if (color == 0) {
            color = Color.parseColor("#33000000");
        } else {
            int red1 = Color.red(color);
            int green1 = Color.green(color);
            int blue1 = Color.blue(color);

            int newColor = Color.parseColor("#000000");
            int red2 = Color.red(newColor);
            int green2 = Color.green(newColor);
            int blue2 = Color.blue(newColor);

            //颜色合成
            color = Color.rgb((int) ((red1 + red2) / REDUCE), (int) ((green1 + green2) / REDUCE),
                    (int) ((blue1 + blue2) / REDUCE));
        }
        childColor = color;


        //防止没有，设置为了-1.设置为默认的
        if (DEFAULT_RADIUS < 0) {
            DEFAULT_RADIUS = 0;
        }

        if (DEFAULT_RADIUS_LEFT_TOP < 0) {
            DEFAULT_RADIUS_LEFT_TOP = DEFAULT_RADIUS;
        }
        if (DEFAULT_RADIUS_RIGHT_TOP < 0) {
            DEFAULT_RADIUS_RIGHT_TOP = DEFAULT_RADIUS;
        }
        if (DEFAULT_RADIUS_RIGHT_BOTTOM < 0) {
            DEFAULT_RADIUS_RIGHT_BOTTOM = DEFAULT_RADIUS;
        }
        if (DEFAULT_RADIUS_LEFT_BOTTOM < 0) {
            DEFAULT_RADIUS_LEFT_BOTTOM = DEFAULT_RADIUS;
        }

        GradientDrawable drawablePressed = new GradientDrawable();
        drawablePressed.setCornerRadius(DEFAULT_RADIUS);
        //左上右上右下左下,两个一起
        drawablePressed.setCornerRadii(new float[]{
                DEFAULT_RADIUS_LEFT_TOP,
                DEFAULT_RADIUS_LEFT_TOP,
                DEFAULT_RADIUS_RIGHT_TOP,
                DEFAULT_RADIUS_RIGHT_TOP,
                DEFAULT_RADIUS_RIGHT_BOTTOM,
                DEFAULT_RADIUS_RIGHT_BOTTOM,
                DEFAULT_RADIUS_LEFT_BOTTOM,
                DEFAULT_RADIUS_LEFT_BOTTOM});
        drawablePressed.setColor(color);

        GradientDrawable drawableFocus = new GradientDrawable();
        drawableFocus.setCornerRadius(DEFAULT_RADIUS);
        //左上右上右下左下,两个一起
        drawableFocus.setCornerRadii(new float[]{
                DEFAULT_RADIUS_LEFT_TOP,
                DEFAULT_RADIUS_LEFT_TOP,
                DEFAULT_RADIUS_RIGHT_TOP,
                DEFAULT_RADIUS_RIGHT_TOP,
                DEFAULT_RADIUS_RIGHT_BOTTOM,
                DEFAULT_RADIUS_RIGHT_BOTTOM,
                DEFAULT_RADIUS_LEFT_BOTTOM,
                DEFAULT_RADIUS_LEFT_BOTTOM});
        drawableFocus.setColor(color);

        sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused},
                drawableFocus);
        sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled},
                drawablePressed);
        sd.addState(new int[]{android.R.attr.state_focused}, drawableFocus);
        sd.addState(new int[]{android.R.attr.state_pressed}, drawablePressed);
        sd.addState(new int[]{android.R.attr.state_enabled}, drawable);
        sd.addState(new int[]{}, drawable);

        if (enableRipple && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (rippleType == RIPPLE_TYPE_CIRCLE) {
                setCircle(false);
            } else {
                RippleDrawable rippleDrawable = new
                        RippleDrawable(ColorStateList.valueOf(Color
                        .parseColor("#55000000"))
                        , sd, drawablePressed);
                childView.setFocusableInTouchMode(false);
                childView.setBackground(rippleDrawable);
            }
        } else {
            childView.setFocusableInTouchMode(false);
            childView.setBackground(sd);
        }

    }

    /**
     * 圆形ripple
     *
     * @param isNeedMeasureSpec 是否使用测算高度，在拿不到高度的时候
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private void setCircle(boolean isNeedMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //获取view的宽度高度
            int w = childView.getWidth();
            int h = childView.getHeight();


            /***************************************************************
             *  这里测算，如果是0的话,从layoutParams拿是为了适配xml，
             *  建议将子控件的高度和宽度写死，或者本控件写死 by JACK-GU 2018-07-19 10:24
             ****************************************************************/
            if (w == 0) {
                ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
                if (layoutParams != null)
                    w = layoutParams.width;
                if (w <= 0) {
                    //测算父类的
                    ViewGroup.LayoutParams layoutParamsParent = getLayoutParams();
                    if (layoutParamsParent != null)
                        w = layoutParamsParent.width;
                }
                if (w <= 0 && isNeedMeasureSpec)
                    w = ViewUtil.getViewWidth(childView);
            }

            if (h == 0) {
                ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
                if (layoutParams != null)
                    h = layoutParams.height;
                if (h <= 0) {
                    //测算父类的
                    ViewGroup.LayoutParams layoutParamsParent = getLayoutParams();
                    if (layoutParamsParent != null)
                        h = layoutParamsParent.height;
                }
                if (h <= 0 && isNeedMeasureSpec)
                    h = ViewUtil.getViewHeight(childView);
            }

            int r = w > h ? h : w;
            r = r / 2;
            radius = r;

            Drawable drawable = childView.getBackground();
            StateListDrawable sd = new StateListDrawable();

            GradientDrawable drawablePressed = new GradientDrawable();
            drawablePressed.setCornerRadius(r);
            drawablePressed.setColor(childColor);

            GradientDrawable drawableFocus = new GradientDrawable();
            drawableFocus.setCornerRadius(r);
            drawableFocus.setColor(childColor);

            sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused},
                    drawableFocus);
            sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled},
                    drawablePressed);
            sd.addState(new int[]{android.R.attr.state_focused}, drawableFocus);
            sd.addState(new int[]{android.R.attr.state_pressed}, drawablePressed);
            sd.addState(new int[]{android.R.attr.state_enabled}, drawable);
            sd.addState(new int[]{}, drawable);

            //使用drawable，在跳转activity的时候没效果，但是圆形的只适合顶部，我们这里就不管了，预留着,
            //要想有效果，使用sd,drawablePressed
            RippleDrawable rippleDrawable = new
                    RippleDrawable(ColorStateList.valueOf(Color.parseColor("#55000000"))
                    , null, null);

            try {
                setRadius(rippleDrawable, r);
                childView.setBackground(rippleDrawable);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void setRadius(RippleDrawable rippleDrawable, int r)
            throws NoSuchFieldException, IllegalAccessException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rippleDrawable.setRadius(r);
        } else {
            //反射
            Class aClass = rippleDrawable.getClass();
            Field field = aClass.getDeclaredField
                    ("mState");
            field.setAccessible(true);
            Object object = field.get(rippleDrawable);

            Field mMaxRadius = object.getClass().getDeclaredField("mMaxRadius");
            mMaxRadius.setAccessible(true);
            mMaxRadius.set(object, r);
        }
    }

    private int getColor(ColorStateList colorStateList) {
        int color = Color.parseColor("#FFFFFF");
        Field field = null;
        try {
            field = colorStateList.getClass().getDeclaredField("mColors");
            field.setAccessible(true);
            int[] colors = (int[]) field.get(colorStateList);
            if (colors != null && colors.length > 0) {
                color = colors[0];
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return color;
    }
}

