package com.jack.framework.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.jack.framework.R;
import com.jack.framework.util.PhoneUtil;
import com.jack.framework.util.ResourcesUtil;


/**
 * 高度等于Android状态栏高度的view，默认颜色是透明的，只是拿来占位置的
 * <p>
 * 所以这个你设置高度是没用的
 *
 * @Author: JACK-GU
 * @Date: 2018/4/10 15:23
 * @E-Mail: 528489389@qq.com
 */
public class BarHeightView extends LinearLayout {

    public BarHeightView(Context context) {
        super(context);
        initView();
    }

    public BarHeightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BarHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BarHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        setBackgroundColor(ResourcesUtil.getColor(R.color.transparent));
        post(() -> {
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, PhoneUtil.getStatusBarHeight());
            setLayoutParams(layoutParams);
        });
    }
}
