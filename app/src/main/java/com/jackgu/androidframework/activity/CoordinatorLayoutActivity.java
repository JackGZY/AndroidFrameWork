package com.jackgu.androidframework.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.Gravity;

import com.jack.framework.base.BaseActivity;
import com.jackgu.androidframework.R;

/**
 * -
 *
 * @Author: JACK-GU
 * @Date: 2018-06-19 15:17
 * @E-Mail: 528489389@qq.com
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    public boolean isTranslucentStatus() {
        return true;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("测试");
        collapsingToolbar.setCollapsedTitleGravity(Gravity.CENTER);
        collapsingToolbar.setExpandedTitleGravity(Gravity.CENTER);

        setTitle("测试");
    }
}
