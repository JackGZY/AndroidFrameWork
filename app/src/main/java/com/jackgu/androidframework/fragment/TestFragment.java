package com.jackgu.androidframework.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.base.BaseFragment;
import com.jackgu.androidframework.util.LoggerUtil;
import com.jackgu.androidframework.view.TitleBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 测试的fragment
 *
 * @Author: JACK-GU
 * @Date: 2018/1/30 14:18
 * @E-Mail: 528489389@qq.com
 */
public class TestFragment extends BaseFragment {
    @BindView(R.id.titleBarLayout)
    TitleBarLayout titleBarLayout;

    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        titleBarLayout.setTranslucentStatus();
    }

    @Override
    protected void viewDrawFinished() {
        super.viewDrawFinished();
        LoggerUtil.e("TestFragment绘制完成");
    }
}
