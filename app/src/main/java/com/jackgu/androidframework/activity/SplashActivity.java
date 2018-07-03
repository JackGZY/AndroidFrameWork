package com.jackgu.androidframework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.jack.framework.base.BaseActivity;
import com.jack.framework.util.GlideUtil;
import com.jackgu.androidframework.R;

import butterknife.BindView;

/**
 * 开屏页
 *
 * @Author: JACK-GU
 * @Date: 2018/3/19 13:51
 * @E-Mail: 528489389@qq.com
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.imageView1)
    ImageView imageView1;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        GlideUtil.load(R.mipmap.splash, imageView1);

        new Thread(() -> {
            try {
                Thread.sleep(1 * 1000);
                goToNext();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void goToNext() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void finish() {
        super.finish();
    }
}
