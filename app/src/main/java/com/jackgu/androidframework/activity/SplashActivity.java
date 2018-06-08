package com.jackgu.androidframework.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jack.framework.util.GlideUtil;
import com.jack.framework.util.SystemBarTintManager;
import com.jackgu.androidframework.R;

/**
 * 开屏页
 *
 * @Author: JACK-GU
 * @Date: 2018/3/19 13:51
 * @E-Mail: 528489389@qq.com
 */

public class SplashActivity extends Activity {
    ImageView imageView1;
    private SystemBarTintManager systemBarTintManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView(savedInstanceState);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void initView(Bundle savedInstanceState) {
        imageView1 = findViewById(R.id.imageView1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarAlpha(0.5f);
            systemBarTintManager.setStatusBarTintColor(Color.parseColor("#000000"));
        }


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
