package com.jackgu.androidframework.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jackgu.androidframework.util.ToastUtil;
import com.jackgu.androidframework.view.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Marno on 2016/3/14/13:53.
 * 所有Fragment的基类
 */
public abstract class BaseFragment extends RxFragment {
    protected Activity mContext;

    private LoadingDialog loadingDialog;
    private Unbinder mUnbinder;

    protected View mContentView;

    protected abstract int getLayout();

    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 在初始化视图前做一些操作
     */
    protected void beforeInitView() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
        loadingDialog = new LoadingDialog(mContext);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        mContentView = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mContentView);
        beforeInitView();
        initView(mContentView, savedInstanceState);
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    /**
     * 界面跳转
     *
     * @param class1   要跳转到的界面
     * @param isFinish 是否结束当前的Activity (true 结束，false 不结束)
     * @param bundle   不需要传参数的时候传入空即可
     * @author GZY
     */
    public void turnActivity(Class class1, boolean isFinish, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, class1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        mContext.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim
                .slide_out_right);
        if (isFinish) {
            mContext.finish();
        }
    }

    /**
     * 界面跳转
     *
     * @param class1      要跳转到的界面
     * @param isFinish    是否结束当前的Activity (true 结束，false 不结束)
     * @param bundle      不需要传参数的时候传入空即可
     * @param RequestCode 请求码
     * @author GZY
     */
    public void turnActivityForResult(Class class1, boolean isFinish, Bundle bundle, int
            RequestCode) {
        Intent intent = new Intent();
        intent.setClass(mContext, class1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, RequestCode);
        mContext.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim
                .slide_out_right);
        if (isFinish) {
            mContext.finish();
        }
    }

    /**
     * 显示进度对话框,带有消息
     */
    protected void showProgressDialog(String message) {
        loadingDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    protected void closeProgressDialog() {
        loadingDialog.dismiss();
    }


    /**
     * 显示短消息
     */
    protected void showShortToast(String message) {
        ToastUtil.showShortMessage(message);
    }

    /**
     * 显示长消息
     */
    protected void showLongToast(String message) {
        ToastUtil.showLongMessage(message);
    }
}
