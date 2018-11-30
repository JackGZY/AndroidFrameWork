package com.jack.framework.view.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.framework.R;
import com.jack.framework.util.DensityUtil;
import com.jack.framework.view.ProgressDrawable;


/**
 * 网络请求的dialog
 *
 * @author Jack-GZY
 * @version 1.0
 * @data 2016年5月18日 下午8:55:48
 */
public class LoadingDialog extends BaseDialogFragment {
    private Context context;
    private ImageView imageView;
    private ProgressDrawable progressDrawable;
    private LinearLayout root;
    private TextView textView;
    private String message;

    public static LoadingDialog newInstance() {
        Bundle args = new Bundle();
        LoadingDialog fragment = new LoadingDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(R.style.MyDialog, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return getRootView();
    }


    private View getRootView() {
        if (root != null) {
            return root;
        }
        root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(DensityUtil.dip2px(30), (int) context.getResources()
                        .getDimension(R.dimen.activity_margin), DensityUtil.dip2px(30),
                (int) context.getResources().getDimension(R.dimen.activity_margin));

        root.setBackgroundResource(R.drawable.dialog_blank_shape);

        imageView = new ImageView(context);
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams
                (DensityUtil.dip2px
                        (30), DensityUtil.dip2px(30));
        imageView.setLayoutParams(imageViewLayoutParams);

        textView = new TextView(context);
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.topMargin = (int) context.getResources().getDimension(R.dimen
                .activity_margin);
        textView.setLayoutParams(textLayoutParams);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        textView.setTextSize(DensityUtil.px2sp(context.getResources().getDimension
                (R.dimen.text_size_small)));
        textView.setMaxWidth(DensityUtil.getWidth(context) / 2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setSingleLine(true);

        root.addView(imageView);
        root.addView(textView);


        progressDrawable = new ProgressDrawable();
        progressDrawable.setColor(context.getResources().getColor(R.color.white));

        imageView.animate().setInterpolator(new LinearInterpolator());
        imageView.setImageDrawable(progressDrawable);
        if (progressDrawable != null && !progressDrawable.isRunning()) {
            progressDrawable.start();
        }

        if (!TextUtils.isEmpty(message)) {
            textView.setText(message);
        }

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
        this.message = message;
        if (textView != null) {
            textView.setText("" + message);
        }
    }

    @Override
    public void onDestroyView() {
        if (progressDrawable != null) {
            progressDrawable.stop();
        }
        //每次消失后都会被销毁
        imageView = null;
        progressDrawable = null;
        root = null;
        textView = null;
        super.onDestroyView();
    }
}
