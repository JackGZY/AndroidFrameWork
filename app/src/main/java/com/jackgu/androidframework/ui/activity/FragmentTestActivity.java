package com.jackgu.androidframework.ui.activity;

import android.os.Bundle;

import com.jack.framework.base.BaseActivity;
import com.jack.framework.util.LoggerUtil;
import com.jackgu.androidframework.R;
import com.jackgu.androidframework.ui.fragment.TestFragment;

/**
 * 测试fragment的
 *
 * @Author: JACK-GU
 * @Date: 2018/1/30 14:16
 * @E-Mail: 528489389@qq.com
 */

public class FragmentTestActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_fragment_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new TestFragment())
                .commit();
    }

    @Override
    protected void viewDrawFinished() {
        super.viewDrawFinished();
        LoggerUtil.e("FragmentTestActivity绘制完成");
    }
}
