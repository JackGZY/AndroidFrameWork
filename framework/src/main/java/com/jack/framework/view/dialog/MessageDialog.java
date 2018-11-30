package com.jack.framework.view.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.framework.R;
import com.jack.framework.util.DensityUtil;
import com.jack.framework.util.ResourcesUtil;
import com.jack.framework.view.ButtonHaveSelect;

/**
 * <p>
 * 消息提示的dialog，可以设置type，一个按钮，两个按钮，或者三个按钮，一个按钮是默认不可取消的
 * <p>
 * 一个按钮是中间，两个按钮是左右
 *
 * @Author: JACK-GU
 * @Date: 2018/1/16
 * @E-Mail: 528489389@qq.com
 */

public class MessageDialog extends BaseDialogFragment {
    //定义三种类型的dialog
    public static final int TYPE_ONE_BUTTON = 0;
    public static final int TYPE_TWO_BUTTON = 1;
    public static final int TYPE_THREE_BUTTON = 2;

    //dialog距离两边的宽度
    private static final int MARGIN_DEVICE = 20;
    private Context context;
    private LinearLayout rootLinearLayout;
    private LinearLayout buttonLinearLayout;

    private TextView textViewTitle;
    private TextView textViewContent;

    private ButtonHaveSelect leftButton;
    private ButtonHaveSelect centerButton;
    private ButtonHaveSelect rightButton;

    private int messageDialogType;

    private final static String MESSAGE_DIALOG_TYPE = "MESSAGE_DIALOG_TYPE";


    //定义那些操作
    private View.OnClickListener leftOnClickListener;
    private View.OnClickListener rightOnClickListener;
    private View.OnClickListener centerOnClickListener;
    private String strTitle;
    private String strContent;
    private String strLeftButtonText;
    private String strCenterButtonText;
    private String strRightButtonText;


    /**
     * 对话框按钮的个数
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static MessageDialog newInstance(int messageDialogType) {
        Bundle args = new Bundle();
        args.putInt(MESSAGE_DIALOG_TYPE, messageDialogType);
        MessageDialog fragment = new MessageDialog();
        fragment.setArguments(args);
        return fragment;
    }


    public static MessageDialog newInstance() {
        return newInstance(TYPE_TWO_BUTTON);
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        messageDialogType = args.getInt(MESSAGE_DIALOG_TYPE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(R.style.MyDialog, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return getRootView();
    }

    /**
     * 设置标题
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setTitle(String title) {
        this.strTitle = title;
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
        this.strContent = content;
        if (textViewContent != null) {
            textViewContent.setText(content);
        }
    }

    /**
     * 设置
     *
     * @Author: JACK-GU
     * @Date: 2018/4/12 09:35
     * @E-Mail: 528489389@qq.com
     */
    public void setButtonTexts(String... args) {
        switch (args.length) {
            case 1: {
                //分两种情况，如果只有一个按钮，就设置中间的，否则就是设置左边的
                if (messageDialogType == TYPE_ONE_BUTTON) {
                    if (centerButton != null) {
                        centerButton.setText(args[0]);
                    }
                    strCenterButtonText = args[0];
                } else {
                    if (leftButton == null) {
                        leftButton.setText(args[0]);
                    }
                    strLeftButtonText = args[0];
                }
                break;
            }
            case 2: {
                //这种情况也要判断,
                if (messageDialogType == TYPE_ONE_BUTTON) {
                    //如果只是一个按钮，我们就设置第一个值
                    if (centerButton != null) {
                        centerButton.setText(args[0]);
                    }
                    strCenterButtonText = args[0];
                } else if (messageDialogType == TYPE_TWO_BUTTON) {
                    //两个按钮设置左右
                    if (leftButton != null) {
                        leftButton.setText(args[0]);
                    }
                    strLeftButtonText = args[0];

                    if (rightButton != null) {
                        rightButton.setText(args[1]);
                    }
                    strRightButtonText = args[1];
                } else if (messageDialogType == TYPE_THREE_BUTTON) {
                    //三个按钮，左中
                    if (leftButton != null) {
                        leftButton.setText(args[0]);
                    }
                    strLeftButtonText = args[0];

                    if (centerButton != null) {
                        centerButton.setText(args[1]);
                    }
                    strCenterButtonText = args[1];
                }

                break;
            }
            case 3: {
                //这种情况也要判断,
                if (messageDialogType == TYPE_ONE_BUTTON) {
                    //如果只是一个按钮，我们就设置第一个值
                    if (centerButton != null) {
                        centerButton.setText(args[1]);
                    }
                    strCenterButtonText = args[1];
                } else if (messageDialogType == TYPE_TWO_BUTTON) {
                    //两个按钮设置左右
                    if (leftButton != null) {
                        leftButton.setText(args[0]);
                    }
                    strLeftButtonText = args[0];

                    if (rightButton != null) {
                        rightButton.setText(args[2]);
                    }
                    strRightButtonText = args[2];
                } else if (messageDialogType == TYPE_THREE_BUTTON) {
                    //三个按钮，左中
                    if (leftButton != null) {
                        leftButton.setText(args[0]);
                    }
                    strLeftButtonText = args[0];

                    if (centerButton != null) {
                        centerButton.setText(args[1]);
                    }
                    strCenterButtonText = args[1];

                    if (rightButton != null) {
                        rightButton.setText(args[2]);
                    }
                    strRightButtonText = args[2];
                }


                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * 设置左边按钮文字，当只有两个按钮或者三个按钮的时候
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setLeftButtonText(String text) {
        if (leftButton != null) {
            leftButton.getTextView().setText(text);
        }
        strLeftButtonText = text;
    }

    /**
     * 设置中间按钮文字，当只有一个按钮或者三个按钮的时候
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setCenterButtonText(String text) {
        if (centerButton != null) {
            centerButton.getTextView().setText(text);
        }
        strCenterButtonText = text;
    }


    /**
     * 设置右边按钮文字，当只有两个按钮或者三个按钮的时候
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setRightButtonText(String text) {
        if (rightButton != null) {
            rightButton.getTextView().setText(text);
        }
        strRightButtonText = text;
    }

    /**
     * 只有一个按钮的时候是在监听
     * MessageDialogType.ONE_BUTTON
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setOneButtonClickListener(View.OnClickListener oneButtonClickListener) {
        if (centerButton != null) {
            centerButton.setOnClickListener(v -> {
                if (oneButtonClickListener != null)
                    oneButtonClickListener.onClick(v);
            });
        }
        centerOnClickListener = oneButtonClickListener;
    }

    /**
     * 只有两个按钮的设置监听
     * MessageDialogType.TWO_BUTTON
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setTwoButtonClickListener(View.OnClickListener leftButtonClickListener, View
            .OnClickListener rightButtonClickListener) {
        if (leftButton != null) {
            leftButton.setOnClickListener(v -> {
                if (leftButtonClickListener != null)
                    leftButtonClickListener.onClick(v);
            });
        }

        this.leftOnClickListener = leftButtonClickListener;

        if (rightButton != null) {
            rightButton.setOnClickListener(v -> {
                if (rightButtonClickListener != null)
                    rightButtonClickListener.onClick(v);
            });
        }

        this.rightOnClickListener = rightButtonClickListener;
    }

    /**
     * 三个按钮设置监听
     * MessageDialogType.THREE_BUTTON
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    public void setThreeButtonClickListener(View.OnClickListener leftButtonClickListener, View
            .OnClickListener centerButtonClickListener, View.OnClickListener
                                                    rightButtonClickListener) {
        if (leftButton != null) {
            leftButton.setOnClickListener(v -> {
                if (leftButtonClickListener != null)
                    leftButtonClickListener.onClick(v);
            });
        }
        this.leftOnClickListener = leftButtonClickListener;
        if (centerButton != null) {
            centerButton.setOnClickListener(v -> {
                if (centerButtonClickListener != null)
                    centerButtonClickListener.onClick(v);
            });
        }
        this.centerOnClickListener = centerButtonClickListener;

        if (rightButton != null) {
            rightButton.setOnClickListener(v -> {
                if (rightButtonClickListener != null)
                    rightButtonClickListener.onClick(v);
            });
        }
        this.rightOnClickListener = rightButtonClickListener;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }


    public TextView getTextViewContent() {
        return textViewContent;
    }


    public ButtonHaveSelect getLeftButton() {
        return leftButton;
    }


    public ButtonHaveSelect getCenterButton() {
        return centerButton;
    }


    public ButtonHaveSelect getRightButton() {
        return rightButton;
    }


    /**
     * 获得整个的根布局
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    private LinearLayout getRootView() {
        if (rootLinearLayout != null) {
            return rootLinearLayout;
        }
        int padding = (int) context.getResources().getDimension(R.dimen.activity_margin);


        //创建最外层的rootLinearLayout
        rootLinearLayout = new LinearLayout(context);
        rootLinearLayout.setOrientation(LinearLayout.VERTICAL);

        //设置布局
        int width = DensityUtil.getDeviceWidth() - 2 * DensityUtil.dip2px(MARGIN_DEVICE);
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

        textViewTitle.setText(!TextUtils.isEmpty(strTitle) ? strTitle :
                context.getResources().getText(R.string.message_dialog_title));
        textViewTitle.setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(R.dimen.text_size_big)));
        textViewTitle.setGravity(Gravity.CENTER);
        textViewTitle.setPadding(padding, padding, padding, padding);
        textViewTitle.setTextColor(context.getResources().getColor(R.color.theme));
        rootLinearLayout.addView(textViewTitle);


        //内容
        textViewContent = new TextView(context);
        LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewContent.setLayoutParams(contentLayoutParams);
//        textViewContent.setGravity(Gravity.CENTER);
        textViewContent.setText(!TextUtils.isEmpty(strContent) ? strContent :
                context.getResources().getText(R.string.message_dialog_content));
        textViewContent.setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(R.dimen.text_size_normal)));
        textViewContent.setPadding(padding, 0, padding, padding);
        textViewContent.setTextColor(context.getResources().getColor(R.color.text_color));
        rootLinearLayout.addView(textViewContent);

        //一条线
        View view = new View(context);
        LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams(width,
                (int) context.getResources().getDimension(R.dimen.view_driver));
        view.setLayoutParams(viewLayoutParams);
        view.setBackgroundResource(R.color.gray);
        rootLinearLayout.addView(view);


        //按钮
        rootLinearLayout.addView(getButton());


        //默认，一个按钮不可以取消
        if (messageDialogType == TYPE_ONE_BUTTON) {
            setCancelable(false);
        } else {
            setCancelable(true);
        }
        return rootLinearLayout;
    }

    /**
     * 下面的按钮，最多是三个
     *
     * @Author: JACK-GU
     * @Date: 2018/1/17
     * @E-Mail: 528489389@qq.com
     */
    private LinearLayout getButton() {
        int padding = (int) context.getResources().getDimension(R.dimen.activity_margin);
        buttonLinearLayout = new LinearLayout(context);
        buttonLinearLayout.setGravity(Gravity.CENTER);
        buttonLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rootLayoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonLinearLayout.setLayoutParams(rootLayoutParams);


        if (messageDialogType == TYPE_TWO_BUTTON || messageDialogType ==
                TYPE_THREE_BUTTON) {
            leftButton = new ButtonHaveSelect(context);

            leftButton.getTextView().setText(!TextUtils.isEmpty(strLeftButtonText) ? strLeftButtonText :
                    context.getResources().getText(R.string.message_dialog_left_button));
            leftButton.getTextView().setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(
                    R.dimen.text_size_normal)));
            leftButton.getTextView().setTextColor(context.getResources().getColor(R.color.gray));
            leftButton.setPaddingCode(0, padding, 0, padding);
            LinearLayout.LayoutParams centerButtonLayoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams
                            .WRAP_CONTENT, 1);
            leftButton.setLayoutParams(centerButtonLayoutParams);
            leftButton.getButtonLayout().setLeftBottomRadius((int) ResourcesUtil.getPx(R.dimen.radius));
            buttonLinearLayout.addView(leftButton);

            leftButton.setOnClickListener(leftOnClickListener);
        }

        if (messageDialogType == TYPE_THREE_BUTTON) {
            View view = new View(context);
            LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams((int)
                    context.getResources().getDimension(R.dimen.view_driver), ViewGroup
                    .LayoutParams.MATCH_PARENT);
            view.setLayoutParams(viewLayoutParams);
            view.setBackgroundResource(R.color.gray);
            buttonLinearLayout.addView(view);
        }


        if (messageDialogType == TYPE_ONE_BUTTON || messageDialogType ==
                TYPE_THREE_BUTTON) {
            centerButton = new ButtonHaveSelect(context);
            centerButton.getTextView().setText(!TextUtils.isEmpty(strCenterButtonText) ?
                    strCenterButtonText : context.getResources().getText(R.string.message_dialog_center_button));
            centerButton.getTextView().setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(
                    R.dimen.text_size_normal)));
            centerButton.getTextView().setTextColor(context.getResources().getColor(R.color.black));
            centerButton.setPaddingCode(0, padding, 0, padding);
            LinearLayout.LayoutParams centerButtonLayoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams
                            .WRAP_CONTENT, 1);
            centerButton.setLayoutParams(centerButtonLayoutParams);

            if (TYPE_ONE_BUTTON == messageDialogType) {
                //设置圆角
                centerButton.getTextView().setTextColor(context.getResources().getColor(R.color
                        .theme));
                centerButton.getButtonLayout().setLeftBottomRadius((int) ResourcesUtil.getPx(R.dimen.radius));
                centerButton.getButtonLayout().setRightBottomRadius((int) ResourcesUtil.getPx(R.dimen.radius));
            }
            buttonLinearLayout.addView(centerButton);

            centerButton.setOnClickListener(centerOnClickListener);
        }


        if (messageDialogType == TYPE_THREE_BUTTON || messageDialogType ==
                TYPE_TWO_BUTTON) {
            View view = new View(context);
            LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams((int)
                    context.getResources().getDimension(R.dimen.view_driver), ViewGroup
                    .LayoutParams.MATCH_PARENT);
            view.setLayoutParams(viewLayoutParams);
            view.setBackgroundResource(R.color.gray);
            buttonLinearLayout.addView(view);
        }

        if (messageDialogType == TYPE_TWO_BUTTON || messageDialogType ==
                TYPE_THREE_BUTTON) {
            rightButton = new ButtonHaveSelect(context);


            rightButton.getTextView().setText(!TextUtils.isEmpty(strRightButtonText) ? strRightButtonText :
                    context.getResources().getText(R.string.message_dialog_right_button));
            rightButton.getTextView().setTextSize(DensityUtil.px2sp(ResourcesUtil.getPx(
                    R.dimen.text_size_normal)));
            rightButton.getTextView().setTextColor(context.getResources().getColor(R.color.theme));
            rightButton.setPaddingCode(0, padding, 0, padding);
            LinearLayout.LayoutParams centerButtonLayoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams
                            .WRAP_CONTENT, 1);
            rightButton.setLayoutParams(centerButtonLayoutParams);
            rightButton.getButtonLayout().setRightBottomRadius((int) ResourcesUtil.getPx(R.dimen.radius));

            buttonLinearLayout.addView(rightButton);

            rightButton.setOnClickListener(rightOnClickListener);
        }


        return buttonLinearLayout;
    }

    @Override
    public void onDestroyView() {
        //销毁
        rootLinearLayout = null;
        buttonLinearLayout = null;
        textViewTitle = null;
        textViewContent = null;
        leftButton = null;
        centerButton = null;
        rightButton = null;
        super.onDestroyView();
    }
}