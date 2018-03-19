/*
 * Copyright (C) 2014 Balys Valentukevicius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jackgu.androidframework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.jackgu.androidframework.R;

import java.lang.reflect.Field;

/**
 * 要加在按钮外面，就可以不写选择器了，但是子view一定要有背景色,不然默认白色
 * 默认圆角0dp，如果设置了圆角，优先，如果设置了四个角的，四个角的值优先，如果都没设置，会自动读取子控件的圆角
 */
public class ButtonLayout extends FrameLayout {
    private View childView;
    private int DEFAULT_RADIUS = 0;
    private int DEFAULT_RADIUS_LEFT_TOP = -1;
    private int DEFAULT_RADIUS_LEFT_BOTTOM = -1;
    private int DEFAULT_RADIUS_RIGHT_BOTTOM = -1;
    private int DEFAULT_RADIUS_RIGHT_TOP = -1;
    private float REDUCE = 1.3f;

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
    }


    @Override
    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("MaterialRippleLayout can host only one child");
        }

        childView = child;
        try {
            setSelector();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        super.addView(child, index, params);
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
        //如果默认的有，那就需要设置为默认的
        if (DEFAULT_RADIUS >= 0) {
            //说明xml中设置了的，所以要为四个角判断一波
            setFourCorners(DEFAULT_RADIUS);
        }


        StateListDrawable sd = new StateListDrawable();

        int color = getContext().getResources().getColor(R.color.white);
        //获取背景色
        Drawable drawable = childView.getBackground();

        if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            Class gradientDrawableClass = (Class) gradientDrawable.getClass();
            Field field = gradientDrawableClass.getDeclaredField("mFillPaint");
            field.setAccessible(true);
            Paint paint = (Paint) field.get(gradientDrawable);
            color = paint.getColor();

            //这个说明可能有度数哦，我们拿到四个角的度数，然后设置，当然优先级别没有在xml中高
            Field mGradientStateField = gradientDrawableClass.getDeclaredField("mGradientState");
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

        } else if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            color = colorDrawable.getColor();
        } else if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
        }

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
        drawablePressed.setCornerRadii(new float[]{
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

        childView.setFocusableInTouchMode(false);
        childView.setBackground(sd);
    }
}

