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
    @BindView(R.id.buttonLayout_back)
    protected ButtonLayout buttonLayout_back;
    @BindView(R.id.buttonLayout_more)
    protected ButtonLayout buttonLayout_more;
    @BindView(R.id.moreText)
    protected TextView moreText;
    @BindView(R.id.leftText)
    protected TextView leftText;
    @BindView(R.id.title)
    protected TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        buttonLayout_back = findViewById(R.id.buttonLayout_back);
//        buttonLayout_more = findViewById(R.id.buttonLayout_more);
//        moreText = findViewById(R.id.moreText);
//        leftText = findViewById(R.id.leftText);
//        title = findViewById(R.id.title);


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


    /**
     * 设置左边的返回不可见
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    public void setBackVisibility(boolean backVisibility) {
        buttonLayout_back.setVisibility(backVisibility ? View.INVISIBLE : View.VISIBLE);
    }

    public void setRightTitle(String title, View.OnClickListener onClickListener) {
        this.moreText.setText(title);
        buttonLayout_more.setVisibility(View.VISIBLE);
        if (onClickListener != null) {
            buttonLayout_more.setOnClickListener(onClickListener);
        }
    }
}
