package com.jack.framework.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

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
public abstract class BaseTitleActivity extends BaseActivity {
    private TitleBarLayout titleBarLayout;

    @Override
    protected void initView(Bundle savedInstanceState) {
        titleBarLayout = findViewById(R.id.titleBarLayout);
        if (isTranslucentStatus()) {
            titleBarLayout.setTranslucentStatus();
        }
        //设置默认返回
        titleBarLayout.setBackButton(ResourcesUtil.getString(R.string.title_back),
                v -> finish());
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
     * 设置背景
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void setTitleBackground(int res) {
        titleBarLayout.setBackgroundResource(res);
    }


    /**
     * 添加标题
     */
    @Override
    public void setTitle(int res) {
        titleBarLayout.setTitle(res);
    }

    /**
     * 添加标题
     */
    @Override
    public void setTitle(CharSequence title) {
        titleBarLayout.setTitle(title);
    }


    /**
     * 在右边添加按钮
     */
    protected void addRightButton(int res, View.OnClickListener onClickListener) {
        titleBarLayout.addRightButton(res, onClickListener);
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
}
