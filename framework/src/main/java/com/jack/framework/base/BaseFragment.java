package com.jack.framework.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.framework.util.ToastUtil;
import com.jack.framework.view.dialog.LoadingDialog;

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
        //这个得原理是，在Activity的绘制完成后，当前就是一个空的消息队列，这个时候就会调用addIdleHandler，
        Looper.myQueue().addIdleHandler(() -> {
            //这里可以回调方法
            viewDrawFinished();
            //返回false表示只调用一次，下次队列为空的时候不会调用，
            return false;
        });
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
        if (isFinish) {
            mContext.finish();
        }
    }


    /**
     * 当fragment绘制完成了的回调，在这里你可以获得高度宽度等等，或者更改布局等等，不会触发再次调用的
     * ，是晚于activity的
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected void viewDrawFinished() {

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
