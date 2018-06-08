package com.jack.framework.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.jack.framework.R;
import com.jack.framework.view.TitleBarLayout;

/**
 * 有title的fragment
 * xml中必须有com.jackgu.androidframework.view.TitleBarLayout,并且ID是titleBarLayout
 *
 * @Author: JACK-GU
 * @Date: 2018/3/19 14:21
 * @E-Mail: 528489389@qq.com
 */

public abstract class BaseTitleFragment extends BaseFragment {
    protected TitleBarLayout titleBarLayout;

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        titleBarLayout = view.findViewById(R.id.titleBarLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isTranslucentStatus()) {
            titleBarLayout.setTranslucentStatus();
        }
        titleBarLayout.getButtonLayout_more().setVisibility(View.GONE);
        titleBarLayout.getButtonLayout_back().setOnClickListener(v -> {
            getActivity().finish();
        });
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

    /**
     * 是否开启透明状态栏
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    public boolean isTranslucentStatus() {
        return true;
    }

}
