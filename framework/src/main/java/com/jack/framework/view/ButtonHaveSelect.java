package com.jack.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 自己写的button,支持TextView和ButtonLayout的属性
 * 可以设置为水波纹
 * 不用写选择器，但是要设置背景色哦,不然选择器默认使用白色的加深色
 * <p>
 * <p>
 * 目前，圆形的水波纹，还有点小问题
 *
 * @Author: JACK-GU
 * @Date: 2018/1/17
 * @E-Mail: 528489389@qq.com
 */
public class ButtonHaveSelect extends LinearLayout {
    private TextView textView;
    private ButtonLayout buttonLayout;

    public void setEnableRipple(boolean enableRipple) {
        this.buttonLayout.setEnableRipple(enableRipple);
    }

    public void setRippleType(int rippleType) {
        this.buttonLayout.setRippleType(rippleType);
    }

    @Override
    public void focusableViewAvailable(View v) {
        super.focusableViewAvailable(v);
    }

    public ButtonHaveSelect(Context context) {
        super(context);
        init(context, null);
    }

    public ButtonHaveSelect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ButtonHaveSelect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        add(context, attrs);
        //本层的颜色设置为透明，不然没效果
        setBackgroundColor(Color.TRANSPARENT);
        //屏蔽本层的padding
        setPadding(0, 0, 0, 0);
    }

    /**
     * 添加view
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    private void add(Context context, AttributeSet attrs) {
        buttonLayout = new ButtonLayout(context, attrs);

        LayoutParams buttonLayoutLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        //屏蔽本层的margin
        buttonLayoutLayoutParams.setMargins(0, 0, 0, 0);

        buttonLayout.setLayoutParams(buttonLayoutLayoutParams);

        //本层的颜色设置为透明，不然没效果
        buttonLayout.setBackgroundColor(Color.TRANSPARENT);
        //屏蔽本层的padding
        buttonLayout.setPadding(0, 0, 0, 0);
        addView(buttonLayout);

        textView = new TextView(context, attrs);

        LayoutParams textViewLayoutLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        //屏蔽本层的margin
        textViewLayoutLayoutParams.setMargins(0, 0, 0, 0);
        textView.setLayoutParams(textViewLayoutLayoutParams);
        textView.setGravity(Gravity.CENTER);

        buttonLayout.addView(textView);

        setOnClickListener(null);
    }

    public TextView getTextView() {
        return textView;
    }

    /**
     * 通过代码设置padding请调用这个方法
     */
    public void setPaddingCode(@Px int left, @Px int top, @Px int right, @Px int bottom) {
        //设置text的
        textView.setPadding(left, top, right, bottom);
    }


    /**
     * 设置按钮文字
     *
     * @Author: JACK-GU
     * @Date: 2018/4/12 09:41
     * @E-Mail: 528489389@qq.com
     */
    public void setText(String args) {
        textView.setText(args);
    }


    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ButtonLayout getButtonLayout() {
        return buttonLayout;
    }

    public void setButtonLayout(ButtonLayout buttonLayout) {
        this.buttonLayout = buttonLayout;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        if (buttonLayout != null) {
            buttonLayout.setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            });
        }

    }
}
