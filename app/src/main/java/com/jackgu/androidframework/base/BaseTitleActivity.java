package com.jackgu.androidframework.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.view.ButtonLayout;

import butterknife.BindView;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11 15:55
 * @E-Mail: 528489389@qq.com
 */

public abstract class BaseTitleActivity extends BaseActivity {
    protected ButtonLayout buttonLayout_back;
    protected ButtonLayout buttonLayout_more;
    protected TextView moreText;
    protected TextView leftText;
    protected TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonLayout_back = findViewById(R.id.buttonLayout_back);
        buttonLayout_more = findViewById(R.id.buttonLayout_more);
        moreText = findViewById(R.id.moreText);
        leftText = findViewById(R.id.leftText);
        title = findViewById(R.id.title);


        buttonLayout_more.setVisibility(View.GONE);
        buttonLayout_back.setOnClickListener(v -> {
            finish();
        });
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setleftTitle(String title) {
        this.leftText.setText(title);
    }


    public void setRightTitle(String title, View.OnClickListener onClickListener) {
        this.moreText.setText(title);
        buttonLayout_more.setVisibility(View.VISIBLE);
        if (onClickListener != null) {
            buttonLayout_more.setOnClickListener(onClickListener);
        }
    }
}
