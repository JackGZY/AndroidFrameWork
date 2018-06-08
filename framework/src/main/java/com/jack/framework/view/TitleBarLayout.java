package com.jack.framework.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.framework.R;


/**
 * 标题栏
 *
 * @Author: JACK-GU
 * @Date: 2018/3/13 09:48
 * @E-Mail: 528489389@qq.com
 */

public class TitleBarLayout extends LinearLayout {
    private Context context;
    protected ButtonLayout buttonLayout_back;
    protected ButtonLayout buttonLayout_more;
    protected TextView moreText;
    protected TextView leftText;
    protected TextView title;


    public TitleBarLayout(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.layout_title, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                .WRAP_CONTENT);
        addView(view, layoutParams);

        buttonLayout_back = view.findViewById(R.id.buttonLayout_back);
        buttonLayout_more = view.findViewById(R.id.buttonLayout_more);
        moreText = view.findViewById(R.id.moreText);
        leftText = view.findViewById(R.id.leftText);
        title = view.findViewById(R.id.title);

        setBackgroundResource(R.color.theme);
    }

    /**
     * 如果设置了透明状态栏，调用这个方法，就可以了，不用设置fitswindows
     *
     * @Author: JACK-GU
     * @Date: 2018/3/13 13:35
     * @E-Mail: 528489389@qq.com
     */
    public void setTranslucentStatus() {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setPadding(0, statusBarHeight, 0, 0);
    }

    public ButtonLayout getButtonLayout_back() {
        return buttonLayout_back;
    }

    public ButtonLayout getButtonLayout_more() {
        return buttonLayout_more;
    }

    public TextView getMoreText() {
        return moreText;
    }

    public TextView getLeftText() {
        return leftText;
    }

    public TextView getTitle() {
        return title;
    }
}
