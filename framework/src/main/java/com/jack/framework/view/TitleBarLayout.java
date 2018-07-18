package com.jack.framework.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jack.framework.R;
import com.jack.framework.util.DensityUtil;
import com.jack.framework.util.ResourcesUtil;
import com.jack.framework.util.ViewUtil;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * google的状态栏，好处就是方便以后实现效果
 * <p>
 * 自动计算高度，适配变色状态栏
 *
 * @Author: JACK-GU
 * @Date: 2018-07-13 13:52
 * @E-Mail: 528489389@qq.com
 */
public class TitleBarLayout extends RelativeLayout {
    ImageView imageViewBack;
    TextView textViewBack;
    TextView textViewTitle;
    private Context mContext;
    LinearLayout linearLayoutLeft;//左边的返回按钮
    LinearLayout linearLayoutCenter; //中间的
    LinearLayout linearLayoutRight; //这里面添加右边的按钮
    boolean needLREqual = true; //默认左右是相等的距离
    int padding = 0;//默认的按钮的左右边距
    //默认屏幕的0.3，左右的按钮最多占有，两个字符
    public static int MAX_WIDTH = -1;
    private ButtonLayout buttonLayoutBack;

    public TitleBarLayout(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 设置是否需要左右两边的宽度相同
     * 一般适合于自定义右边或者中间的view，比如搜索功能
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setNeedLREqual(boolean needLREqual) {
        this.needLREqual = needLREqual;
    }

    /**
     * 初始化
     *
     * @param context 上下文
     * @param attrs   属性
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        padding = (int) ResourcesUtil.getPx(R.dimen.activity_margin);
        setBackgroundResource(R.color.theme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(ResourcesUtil.getPx(R.dimen.elevation));
        }


        //左边得
        linearLayoutLeft = new LinearLayout(mContext);
        linearLayoutLeft.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams leftRootLayoutParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        addView(linearLayoutLeft, leftRootLayoutParams);


        linearLayoutRight = new LinearLayout(context);
        linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams rightRootLayoutParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        rightRootLayoutParams.addRule(ALIGN_PARENT_RIGHT);
        addView(linearLayoutRight, rightRootLayoutParams);


        linearLayoutCenter = new LinearLayout(mContext);
        LayoutParams rightCenterLayoutParams = new LayoutParams(
                MATCH_PARENT, MATCH_PARENT);
        linearLayoutCenter.setLayoutParams(rightCenterLayoutParams);
        linearLayoutCenter.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayoutCenter);

        getTitle();

        if (MAX_WIDTH == -1) {
            //计算默认宽度
            MAX_WIDTH = ViewUtil.getViewWidth(getBackButton(mContext, "返回"));
        }


        ViewGroup.LayoutParams layoutParamsRoot = new ViewGroup.LayoutParams(
                MATCH_PARENT,
                (int) ResourcesUtil.getPx(android.support.v7
                        .appcompat.R.dimen.abc_action_bar_default_height_material));
        setLayoutParams(layoutParamsRoot);
    }


    private void getTitle() {
        textViewTitle = new TextView(mContext);
        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams
                (MATCH_PARENT, MATCH_PARENT);
        textViewTitle.setLayoutParams(titleLayoutParams);
        textViewTitle.setGravity(Gravity.CENTER);
        textViewTitle.setTextColor(ResourcesUtil.getColor(R.color.white));
        textViewTitle.setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(R.dimen.text_size_big)));
        textViewTitle.setSingleLine(true);
        textViewTitle.setEllipsize(TextUtils.TruncateAt.END);
        textViewTitle.setPadding(padding, 0, padding, 0);

        linearLayoutCenter.addView(textViewTitle);
    }


    /**
     * 添加左侧的返回按钮，不调用是没有的,不建议调用多次
     *
     * @param text            需要显示的提示，默认“返回"
     * @param onClickListener 点击事件
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setBackButton(String text, OnClickListener onClickListener) {
        LayoutParams layoutParams = new LayoutParams(
                WRAP_CONTENT,
                MATCH_PARENT);
        linearLayoutLeft.removeAllViews();

        buttonLayoutBack = getBackButton(mContext, text);

        linearLayoutLeft.addView(buttonLayoutBack, layoutParams);

        //不添加左侧的返回，不好看
//        linearLayoutLeft.addView(textViewBack,
//                new LayoutParams(WRAP_CONTENT, MATCH_PARENT));

        buttonLayoutBack.setOnClickListener(onClickListener);

        deal();
    }

    /**
     * 添加标题
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setTitle(CharSequence title) {
        textViewTitle.setText(title);
        deal();
    }

    /**
     * 设置返回按钮可见不
     */
    public void setBackVisibility(boolean backVisibility) {
        linearLayoutLeft.setVisibility(backVisibility ? VISIBLE : GONE);
    }

    /**
     * 添加标题
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setTitle(int res) {
        textViewTitle.setText(res);
        deal();
    }


    /**
     * 在右侧添加按钮，先到先得
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void addRightButton(int res, OnClickListener onClickListener) {
        LayoutParams layoutParamsRightButton = new LayoutParams(
                WRAP_CONTENT,
                MATCH_PARENT);
        ButtonLayout buttonLayoutRight = getRightButton(mContext, res);
        linearLayoutRight.addView(buttonLayoutRight, layoutParamsRightButton);
        buttonLayoutRight.setOnClickListener(onClickListener);

        deal();
    }

    /**
     * 获取返回的按钮
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private ButtonLayout getBackButton(Context context, String text) {
        int wh = (int) ResourcesUtil.getPx(android.support.v7
                .appcompat.R.dimen.abc_action_bar_default_height_material);
        ButtonLayout buttonLayout = new ButtonLayout(context);
        buttonLayout.setRippleType(ButtonLayout.RIPPLE_TYPE_CIRCLE);


        //开始布局
        RelativeLayout relativeLayoutBack = new RelativeLayout(context);
        FrameLayout.LayoutParams linearLayoutLayoutParams = new FrameLayout.LayoutParams(
                wh, wh);
        relativeLayoutBack.setLayoutParams(linearLayoutLayoutParams);

        //按钮
        imageViewBack = new ImageView(context);
        imageViewBack.setImageResource(R.drawable.title_back_white);
        RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(
                (int) (wh * 0.5), (int) (wh * 0.5));
        imageLayoutParams.addRule(CENTER_IN_PARENT);
        relativeLayoutBack.addView(imageViewBack, imageLayoutParams);

        textViewBack = new TextView(context);

        textViewBack.setText(text == null ? ResourcesUtil.getString(R.string.title_back) : text);
        textViewBack.setTextColor(ResourcesUtil.getColor(R.color.white));
        textViewBack.setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(R.dimen.text_size_normal)));
        textViewBack.setSingleLine(true);
        textViewBack.setGravity(Gravity.CENTER);
        textViewBack.setEllipsize(TextUtils.TruncateAt.END);

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                WRAP_CONTENT, WRAP_CONTENT);
        textViewParams.gravity = Gravity.CENTER_VERTICAL;
        // linearLayoutBack.addView(textViewBack, textViewParams);

        buttonLayout.addView(relativeLayoutBack);
        return buttonLayout;
    }

    /**
     * 设置返回键的图片
     *
     * @Author: JACK-GU
     * @Date: 2018-07-17 09:37
     * @E-Mail: 528489389@qq.com
     */
    public void setBackImageRes(int res) {
        imageViewBack.setImageResource(res);
        deal();
    }

    /**
     * 设置返回键的图片
     *
     * @Author: JACK-GU
     * @Date: 2018-07-17 09:37
     * @E-Mail: 528489389@qq.com
     */
    public void setBackImageDrawable(Drawable backBackground) {
        imageViewBack.setImageDrawable(backBackground);
        deal();
    }

    /**
     * 设置返回监听
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setBackOnClickListener(OnClickListener onClickListener) {
        buttonLayoutBack.setOnClickListener(onClickListener);
    }


    /**
     * 设置左边按钮文字
     */
    public void setBackButtonText(int res) {
        if (textViewBack != null) {
            textViewBack.setText(res);
            deal();
        }
    }

    /**
     * 设置左边按钮文字
     */
    public void setBackButtonText(CharSequence left) {
        if (textViewBack != null) {
            textViewBack.setText(left);
            deal();
        }
    }

    /**
     * 右边的按钮
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private ButtonLayout getRightButton(Context context, int resIcon) {
        int wh = (int) ResourcesUtil.getPx(android.support.v7
                .appcompat.R.dimen.abc_action_bar_default_height_material);
        ButtonLayout buttonLayout = new ButtonLayout(context);
        buttonLayout.setRippleType(ButtonLayout.RIPPLE_TYPE_CIRCLE);


        //开始布局
        RelativeLayout relativeLayout = new RelativeLayout(context);
        FrameLayout.LayoutParams linearLayoutLayoutParams = new FrameLayout.LayoutParams(
                wh, MATCH_PARENT);
        relativeLayout.setLayoutParams(linearLayoutLayoutParams);

        //按钮
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(resIcon);
        RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(
                (int) (wh * 0.5), (int) (wh * 0.5));
        imageLayoutParams.addRule(CENTER_IN_PARENT);
        relativeLayout.addView(imageView, imageLayoutParams);

        buttonLayout.addView(relativeLayout);
        return buttonLayout;
    }

    /**
     * 如果设置了透明状态栏，调用这个方法，就可以了，不用设置fitswindows
     *
     * @Author: JACK-GU
     * @Date: 2018/3/13 13:35
     * @E-Mail: 528489389@qq.com
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = 0;
            try {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                int height = Integer.parseInt(clazz.getField("status_bar_height")
                        .get(object).toString());
                statusBarHeight = getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //设置默认高度
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = (int) (statusBarHeight + ResourcesUtil.getPx(
                    android.support.v7.appcompat.R.dimen.abc_action_bar_default_height_material));
            setLayoutParams(layoutParams);

            setPadding(0, statusBarHeight, 0, 0);
        }
    }

    /**
     * 计算处理，引起变化后应当计算
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private void deal() {
        //计算左边的宽度
        int leftWidth = getLeftWidth();
        //计算右边的宽度
        int rightWidth = getRightWidth();

        //linearLayoutCenter.setBackgroundResource(R.color.red);

        LayoutParams layoutParams = (LayoutParams) linearLayoutCenter.getLayoutParams();
        if (!needLREqual) {
            //不需要左右相等
            layoutParams.setMargins(leftWidth, 0, rightWidth, 0);
        } else {
            //使用最大值，但是如果是左边最大，不得超过设置的最大值
            if (leftWidth > MAX_WIDTH) {
                //leftWidth = MAX_WIDTH;
            }

            int w = leftWidth > rightWidth ? leftWidth : rightWidth;
            layoutParams.setMargins(w, 0, w, 0);

            //更新左边的宽度，就是w
            LayoutParams layoutParamsLeft = (LayoutParams) linearLayoutLeft.getLayoutParams();
            layoutParamsLeft.width = w;
            linearLayoutLeft.setLayoutParams(layoutParamsLeft);
        }

    }

    /**
     * 获取左边的宽度
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public int getLeftWidth() {
        return ViewUtil.getViewWidth(linearLayoutLeft);
    }


    /**
     * 获取右边的宽度
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public int getRightWidth() {
        return ViewUtil.getViewWidth(linearLayoutRight);
    }


    /**
     * 自定义中间的view，如果开启自定义的话，自动设置为不相等左右距离
     * 目前只提供这个，右边的不提供
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setCenterView(View view) {
        setNeedLREqual(false);
        linearLayoutCenter.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (MATCH_PARENT, MATCH_PARENT);
        linearLayoutCenter.addView(view, layoutParams);

        deal();
    }
}
