package com.jackgu.androidframework.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.util.DensityUtil;
import com.jackgu.androidframework.util.GlideUtil;
import com.jackgu.androidframework.view.ButtonLayout;

import java.util.List;

/**
 * <p>
 * 选择dialog
 * <p>
 * 通过传入List《SelectItem》来控制
 * <p>
 * 设置标题：SelectItem.name
 * <p>
 * 设置文字颜色：SelectItem.color 不传或者0使用默认的
 * <p>
 * 设置图标：SelectItem.res 不传或者0不显示
 * <p>
 * 设置文字位置：SelectItem.gravity 不传为居中,使用View的Gravity
 * <p>
 * 默认的content（可以认为是提示）不会显示，如果设置文字会显示，
 * 或者构造方法传入boolean类型的showContent
 * <p>
 * 设置监听setOnItemClick，回调按钮的index
 *
 * @Author: JACK-GU
 * @Date: 2018/1/16
 * @E-Mail: 528489389@qq.com
 */

public class SelectDialog extends Dialog {
    private Context context;
    private LinearLayout rootLinearLayout;
    private TextView textViewTitle;
    private TextView textViewContent;
    private List<SelectItem> selectItems;
    private OnItemClick onItemClick;
    private boolean showContent;

    //dialog距离两边的宽度
    private final static int MARGIN_DEVICE = 20;


    /**
     * 设置标题
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setTitle(String title) {
        if (textViewTitle != null) {
            textViewTitle.setText(title);
        }
    }

    /**
     * 设置内容
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setContent(String content) {
        if (textViewContent != null) {
            showContent = true;
            textViewContent.setVisibility(View.VISIBLE);
            textViewContent.setText(content);
        }
    }


    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public void setTextViewTitle(TextView textViewTitle) {
        this.textViewTitle = textViewTitle;
    }

    public TextView getTextViewContent() {
        return textViewContent;
    }

    public void setTextViewContent(TextView textViewContent) {
        this.textViewContent = textViewContent;
    }

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public SelectDialog(@NonNull Context context, @NonNull List<SelectItem> selectItems, boolean
            showContent) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.selectItems = selectItems;
        this.showContent = showContent;
    }

    public SelectDialog(@NonNull Context context, @NonNull List<SelectItem> selectItems) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.selectItems = selectItems;
        this.showContent = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getRootView());
    }

    /**
     * 获得整个的根布局
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    private LinearLayout getRootView() {
        int padding = (int) context.getResources().getDimension(R.dimen.activity_margin);


        //创建最外层的rootLinearLayout
        rootLinearLayout = new LinearLayout(context);
        rootLinearLayout.setOrientation(LinearLayout.VERTICAL);

        //设置布局
        int width = DensityUtil.getDeviceWidth() - 2 * DensityUtil.dip2px(context,
                MARGIN_DEVICE);
        LinearLayout.LayoutParams rootLayoutParams = new LinearLayout.LayoutParams(width,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        rootLayoutParams.width = width;
        rootLinearLayout.setLayoutParams(rootLayoutParams);

        //设置背景
        rootLinearLayout.setBackgroundResource(R.drawable.white_shape);

        //标题
        textViewTitle = new TextView(context);
        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewTitle.setLayoutParams(titleLayoutParams);

        textViewTitle.setText(context.getResources().getText(R.string.message_dialog_title));
        textViewTitle.setTextSize(DensityUtil.px2sp(context, context.getResources().getDimension
                (R.dimen.textSize_big)));
        textViewTitle.setPadding(padding, padding, padding, padding);
        textViewTitle.setTextColor(context.getResources().getColor(R.color.theme));
        rootLinearLayout.addView(textViewTitle);


        //内容
        textViewContent = new TextView(context);
        LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewContent.setLayoutParams(contentLayoutParams);
//        textViewContent.setGravity(Gravity.CENTER);
        textViewContent.setText("*" + context.getResources().getText(R.string
                .message_dialog_content));
        textViewContent.setTextSize(DensityUtil.px2sp(context, context.getResources().getDimension
                (R.dimen.textSize_small)));
        textViewContent.setPadding(padding, 0, padding, padding);
        textViewContent.setTextColor(context.getResources().getColor(R.color.red));
        rootLinearLayout.addView(textViewContent);

        if (showContent) {
            textViewContent.setVisibility(View.VISIBLE);
        } else {
            textViewContent.setVisibility(View.GONE);
        }

        for (SelectItem selectItem : selectItems) {
            //一条线
            View view = new View(context);
            LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams(width,
                    (int) context.getResources().getDimension(R.dimen.view_driver));
            view.setLayoutParams(viewLayoutParams);
            view.setBackgroundResource(R.color.gray);
            rootLinearLayout.addView(view);
            if (selectItem.getRes() == 0) {
                rootLinearLayout.addView(getButtonLayout(getTextView(selectItem), selectItem));
            } else {
                getItem(selectItem);
            }
        }

        return rootLinearLayout;
    }

    //获得由效果的layout
    private ButtonLayout getButtonLayout(View view, SelectItem selectItem) {
        ButtonLayout buttonLayout = new ButtonLayout(context);
        buttonLayout.addView(view);
        buttonLayout.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.click(selectItems.indexOf(selectItem));
            }
        });

        if (selectItems.indexOf(selectItem) == selectItems.size() - 1) {
            buttonLayout.setLeftBottomRadius(DensityUtil.dip2px(context, 5));
            buttonLayout.setRightBottomRadius(DensityUtil.dip2px(context, 5));
        } else {
            buttonLayout.setRadius(DensityUtil.dip2px(context, 0));
        }

        return buttonLayout;
    }

    //获得text
    private TextView getTextView(SelectItem selectItem) {
        int padding = (int) context.getResources().getDimension(R.dimen.activity_margin);
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(titleLayoutParams);

        textView.setText(selectItem.getName());
        textView.setTextSize(DensityUtil.px2sp(context, context.getResources().getDimension
                (R.dimen.textSize_normal)));
        textView.setGravity(selectItem.getGravity());

        textView.setPadding(padding, padding, padding, padding);
        if (selectItem.getColor() == 0) {
            textView.setTextColor(context.getResources().getColor(R.color.text_color));
        } else {
            textView.setTextColor(selectItem.getColor());
        }


        return textView;
    }


    //获得带有图片的item
    private void getItem(SelectItem selectItem) {
        int padding = (int) context.getResources().getDimension(R.dimen.activity_margin);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        //图片
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) context
                .getResources()
                .getDimension(R.dimen.select_item_img_wh), (int) context.getResources().getDimension
                (R.dimen.select_item_img_wh));
        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(padding, 0, 0, 0);
        GlideUtil.load(selectItem.getRes(), imageView);
        linearLayout.addView(imageView);


        linearLayout.addView(getTextView(selectItem));


        ButtonLayout buttonLayout = new ButtonLayout(context);
        buttonLayout.addView(linearLayout);
        buttonLayout.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.click(selectItems.indexOf(selectItem));
            }
        });

        if (selectItems.indexOf(selectItem) == selectItems.size() - 1) {
            buttonLayout.setLeftBottomRadius(DensityUtil.dip2px(context, 5));
            buttonLayout.setRightBottomRadius(DensityUtil.dip2px(context, 5));
        } else {
            buttonLayout.setRadius(DensityUtil.dip2px(context, 0));
        }

        rootLinearLayout.addView(buttonLayout);
    }


    public static class SelectItem {
        private String name;//标题
        private int color;//字体颜色
        private int res;//图片资源，不设置就不显示，显示在左边
        private int gravity;

        public SelectItem(String name) {
            this.name = name;
            this.color = 0;
            this.res = 0;
            this.gravity = Gravity.CENTER;
        }

        public SelectItem(String name, int color, int res) {
            this.name = name;
            this.color = color;
            this.res = res;
            this.gravity = Gravity.CENTER;
        }

        public SelectItem(String name, int color) {
            this.name = name;
            this.color = color;
            this.res = 0;
            this.gravity = Gravity.CENTER;
        }

        public SelectItem(String name, int color, int res, int gravity) {
            this.name = name;
            this.color = color;
            this.res = res;
            this.gravity = gravity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public int getGravity() {
            return gravity;
        }

        public void setGravity(int gravity) {
            this.gravity = gravity;
        }
    }

    public interface OnItemClick {
        void click(int index);
    }
}