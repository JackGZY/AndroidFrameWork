package com.jackgu.androidframework.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.jack.framework.base.BaseDrawerLayoutActivity;
import com.jack.framework.view.ButtonHaveSelect;
import com.jackgu.androidframework.R;

import butterknife.BindView;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/19
 * @E-Mail: 528489389@qq.com
 */
public class DrawerLayoutActivity extends BaseDrawerLayoutActivity {

    @BindView(R.id.open)
    ButtonHaveSelect open;

    @Override
    protected void viewDrawFinished() {
        super.viewDrawFinished();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        open.setOnClickListener(v -> drawerLayout.openDrawer(Gravity.RIGHT));
    }

    @Override
    protected int drawerFromGravity() {
        return Gravity.RIGHT;
    }

    @Override
    protected Object getContentView() {
//        LinearLayout linearLayout1 = new LinearLayout(mContext);
//        linearLayout1.setBackgroundColor(getResources().getColor(R.color.theme));
        return R.layout.activity_drawer_layout;
    }

    @Override
    protected Object getDrawerView() {
        LinearLayout linearLayout1 = new LinearLayout(mContext);
        linearLayout1.setBackgroundColor(getResources().getColor(R.color.orange));
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
