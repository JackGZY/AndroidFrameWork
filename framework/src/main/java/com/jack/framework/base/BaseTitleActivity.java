package com.jack.framework.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.jack.framework.R;
import com.jack.framework.view.TitleBarLayout;


/**
 * xml中必须有com.jackgu.androidframework.view.TitleBarLayout
 *
 * @Author: JACK-GU
 * @Date: 2018/1/11 15:55
 * @E-Mail: 528489389@qq.com
 */

public abstract class BaseTitleActivity extends BaseActivity {
    protected TitleBarLayout titleBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleBarLayout.getButtonLayout_more().setVisibility(View.GONE);
        titleBarLayout.getButtonLayout_back().setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        titleBarLayout = findViewById(R.id.titleBarLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isTranslucentStatus()) {
            titleBarLayout.setTranslucentStatus();
        }

    }

    public void setTitle(String title) {
        titleBarLayout.getTitle().setText(title);
    }

    public void setleftTitle(String title) {
        titleBarLayout.getLeftText().setText(title);
    }


    /**
     * 设置左边的返回不可见
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    public void setBackVisibility(boolean backVisibility) {
        titleBarLayout.getButtonLayout_back().setVisibility(backVisibility ? View.INVISIBLE :
                View.VISIBLE);
    }

    public void setRightTitle(String title, View.OnClickListener onClickListener) {
        titleBarLayout.getMoreText().setText(title);
        titleBarLayout.getButtonLayout_more().setVisibility(View.VISIBLE);
        if (onClickListener != null) {
            titleBarLayout.getButtonLayout_more().setOnClickListener(onClickListener);
        }
    }
}
