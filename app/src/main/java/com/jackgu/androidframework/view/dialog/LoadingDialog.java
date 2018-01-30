package com.jackgu.androidframework.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.util.DensityUtil;
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
    private LinearLayout root;
    private TextView textView;


    public LoadingDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getView());

        progressDrawable = new ProgressDrawable();
        progressDrawable.setColor(context.getResources().getColor(R.color.white));

        imageView.animate().setInterpolator(new LinearInterpolator());
        imageView.setImageDrawable(progressDrawable);
    }

    private View getView() {
        root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(DensityUtil.dip2px(context, 30), (int) context.getResources()
                        .getDimension(R.dimen.activity_margin), DensityUtil.dip2px(context, 30),
                (int) context.getResources().getDimension(R.dimen.activity_margin));

        root.setBackgroundResource(R.drawable.dialog_blank_shape);

        imageView = new ImageView(context);
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams
                (DensityUtil.dip2px
                        (context, 30), DensityUtil.dip2px(context, 30));
        imageView.setLayoutParams(imageViewLayoutParams);

        textView = new TextView(context);
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.topMargin = (int) context.getResources().getDimension(R.dimen
                .activity_margin);
        textView.setLayoutParams(textLayoutParams);
        textView.setTextColor(getContext().getResources().getColor(R.color.white));
        textView.setTextSize(DensityUtil.px2sp(context, getContext().getResources().getDimension
                (R.dimen.textSize_small)));
        textView.setMaxWidth(DensityUtil.getWidth(context) /2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setSingleLine(true);

        root.addView(imageView);
        root.addView(textView);

        return root;
    }

    /**
     * 设置消息提示
     *
     * @Author: JACK-GU
     * @Date: 2018/1/24
     * @E-Mail: 528489389@qq.com
     */
    public void setMessage(String message) {
        if (textView != null) {
            textView.setText("" + message);
        }
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
