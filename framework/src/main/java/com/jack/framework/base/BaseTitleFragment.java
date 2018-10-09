package com.jack.framework.base;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jack.framework.R;
import com.jack.framework.util.ResourcesUtil;
import com.jack.framework.view.TitleBarLayout;

/**
 * -
 *
 * @Author: JACK-GU
 * @Date: 2018-07-13 15:44
 * @E-Mail: 528489389@qq.com
 */
public abstract class BaseTitleFragment extends BaseFragment {
    private TitleBarLayout titleBarLayout;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        titleBarLayout = view.findViewById(R.id.titleBarLayout);
        if (isTranslucentStatus()) {
            titleBarLayout.setTranslucentStatus();
        }
        //设置默认返回
        titleBarLayout.setBackButton(ResourcesUtil.getString(R.string.title_back),
                v -> getActivity().finish());
    }

    protected boolean isTranslucentStatus() {
        return true;
    }


    /**
     * 设置左右标题是否相等
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void setNeedLREqual(boolean needLREqual) {
        titleBarLayout.setNeedLREqual(needLREqual);
    }


    /**
     * 设置阴影
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void setTitleElevation(int e) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            titleBarLayout.setElevation(e);
        }
    }

    /**
     * 设置返回键的图片
     *
     * @Author: JACK-GU
     * @Date: 2018-07-17 09:37
     * @E-Mail: 528489389@qq.com
     */
    protected void setBackImageRes(int res) {
        titleBarLayout.setBackImageRes(res);
    }

    /**
     * 设置返回键的图片
     *
     * @Author: JACK-GU
     * @Date: 2018-07-17 09:37
     * @E-Mail: 528489389@qq.com
     */
    public void setBackImageDrawable(Drawable drawable) {
        titleBarLayout.setBackImageDrawable(drawable);
    }

    /**
     * 设置返回监听
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void setBackOnClickListener(View.OnClickListener onClickListener) {
        titleBarLayout.setBackOnClickListener(onClickListener);
    }

    /**
     * 设置左边按钮文字
     */
    protected void setBackButtonText(int res) {
        titleBarLayout.setBackButtonText(res);
    }

    /**
     * 设置左边按钮文字
     */
    protected void setBackButtonText(CharSequence left) {
        titleBarLayout.setBackButtonText(left);
    }


    /**
     * 设置返回按钮可见不
     */
    protected void setBackVisibility(boolean backVisibility) {
        titleBarLayout.setBackVisibility(backVisibility);
    }


    /**
     * 添加标题
     */
    protected void setTitle(int res) {
        titleBarLayout.setTitle(res);
    }

    /**
     * 添加标题
     */
    protected void setTitle(CharSequence title) {
        titleBarLayout.setTitle(title);
    }


    /**
     * 在右边添加按钮
     */
    protected void addRightButton(int res, View.OnClickListener onClickListener) {
        titleBarLayout.addRightButton(res, onClickListener);
    }


    /**
     * 设置背景
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void setTitleBackground(int res) {
        titleBarLayout.setBackgroundResource(res);
    }


    /**
     * 自定义中间的view，如果开启自定义的话，自动设置为不相等左右距离，比如搜索的bar
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setTitleCenterView(View view) {
        titleBarLayout.setCenterView(view);
    }

    /**
     * 自定义中间的view，如果开启自定义的话，自动设置为不相等左右距离，比如搜索的bar
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setTitleCenterView(View view, RelativeLayout.LayoutParams layoutParams) {
        titleBarLayout.setCenterView(view, layoutParams);
    }
}
