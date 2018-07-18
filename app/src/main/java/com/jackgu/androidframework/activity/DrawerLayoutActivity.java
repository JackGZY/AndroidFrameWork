package com.jackgu.androidframework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.jack.framework.base.BaseDrawerLayoutActivity;
import com.jackgu.androidframework.R;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/19
 * @E-Mail: 528489389@qq.com
 */
public class DrawerLayoutActivity extends BaseDrawerLayoutActivity {

    @Override
    protected void viewDrawFinished() {
        super.viewDrawFinished();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTitle("测试的侧边栏");
    }

    @Override
    protected int drawerFromGravity() {
        return Gravity.LEFT;
    }

    @Override
    protected Object getContentView() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    protected Object getDrawerView() {
        LinearLayout linearLayout1 = new LinearLayout(mContext);
        linearLayout1.setBackgroundColor(Color.parseColor("#55000000"));
        return linearLayout1;
    }

    @Override
    public boolean isTranslucentStatus() {
        return true;
    }

    @Override
    protected float getDrawerWidth() {
        return 300;
    }
}
