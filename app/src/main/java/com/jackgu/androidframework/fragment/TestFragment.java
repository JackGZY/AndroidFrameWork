package com.jackgu.androidframework.fragment;

import android.os.Bundle;
import android.view.View;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.base.BaseFragment;
import com.jackgu.androidframework.util.LoggerUtil;

/**
 * 测试的fragment
 *
 * @Author: JACK-GU
 * @Date: 2018/1/30 14:18
 * @E-Mail: 528489389@qq.com
 */
public class TestFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
    }

    @Override
    protected void viewDrawFinished() {
        super.viewDrawFinished();
        LoggerUtil.e("TestFragment绘制完成");
    }
}
