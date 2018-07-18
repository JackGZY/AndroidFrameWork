package com.jackgu.androidframework.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.jack.framework.base.BaseTitleActivity;
import com.jackgu.androidframework.R;

/**
 * 测试页面，主要测试一些新的控件
 *
 * @Author: JACK-GU
 * @Date: 2018-07-13 13:58
 * @E-Mail: 528489389@qq.com
 */
public class TestActivity extends BaseTitleActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setBackButtonText("");
        setBackImageRes(R.drawable.title_back_theme);
        setTitleBackground(R.color.bg);

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_title_search, null);
        setTitleCenterView(view);
    }


}
