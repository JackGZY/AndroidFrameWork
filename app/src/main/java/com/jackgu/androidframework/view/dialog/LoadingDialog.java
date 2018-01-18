package com.jackgu.androidframework.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.view.ProgressDrawable;


/**
 * 网络请求的dialog
 *
 * @author Jack-GZY
 * @version 1.0
 * @data 2016年5月18日 下午8:55:48
 */
public class LoadingDialog extends Dialog {
    private Context context;
    private ImageView imageView;
    private ProgressDrawable progressDrawable;

    public LoadingDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_loading);
        imageView = (ImageView) findViewById(R.id.p);

        progressDrawable = new ProgressDrawable();
        progressDrawable.setColor(context.getResources().getColor(R.color.white));
        imageView.animate().setInterpolator(new LinearInterpolator());
        imageView.setImageDrawable(progressDrawable);
    }

    @Override
    public void show() {
        super.show();
        progressDrawable.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        progressDrawable.stop();
    }

    @Override
    public void cancel() {
        super.cancel();
        progressDrawable.stop();
    }
}
