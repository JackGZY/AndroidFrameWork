package com.jack.framework.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jack.framework.R;
import com.jack.framework.config.AppConfig;
import com.jack.framework.config.ConstCode;
import com.jack.framework.util.SystemBarTintManager;
import com.jack.framework.util.ToastUtil;
import com.jack.framework.view.dialog.LoadingDialog;
import com.jack.framework.view.dialog.MessageDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/22
 * @E-Mail: 528489389@qq.com
 */
public abstract class BaseActivity extends RxActivity {
    protected Activity mContext;

    private Unbinder mUnbinder;

    protected int isFirstBack;

    private Timer mQuitTimer;

    private LoadingDialog loadingDialog;

    //权限的回调
    private CallBack callBack;


    private SystemBarTintManager systemBarTintManager;

    @LayoutRes
    protected abstract int getLayout();

    /**
     * 重写了这个方法，setContentView不会调用getLayout
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected View getLayoutView() {
        return null;
    }


    /**
     * 是否开启滑动返回
     */
    protected boolean isNeedSwipeBack() {
        return false;
    }

    /**
     * 执行setContentView()方法前调用
     */
    protected void beforeSetView() {

    }

    /**
     * 在初始化控件前进行一些操作
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

    /**
     * 当Activity绘制完成了的回调，在这里你可以获得高度宽度等等，或者更改布局等等，不会触发再次调用的
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected void viewDrawFinished() {

    }


    /**
     * 是否开启透明状态栏
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    public boolean isTranslucentStatus() {
        return true;
    }


    protected abstract void initView(Bundle savedInstanceState);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isTranslucentStatus()) {
            setTranslucentStatus(true);
            systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarAlpha(0.5f);
            systemBarTintManager.setStatusBarTintColor(Color.parseColor("#000000"));
        }

        mContext = this;
        loadingDialog = new LoadingDialog(mContext);

        if (isNeedSwipeBack()) {
//            SwipeBackHelper.onCreate(this);
//            SwipeBackHelper.getCurrentPage(this)//get current instance
//                    .setSwipeBackEnable(true)//on-off
//                    .setSwipeEdge(100)
//                    .setSwipeSensitivity(0.15f)//sensitiveness of the gesture。0:slow  1:sensitive
//                    .setSwipeRelateEnable(true)//if should move together with the following Activity
//                    .setSwipeRelateOffset(500);//the Offset of following Activity when
        }
        beforeSetView();
        View view = getLayoutView();
        if (view == null) {
            setContentView(getLayout());
        } else {
            setContentView(view);
        }

        mUnbinder = ButterKnife.bind(this);
        beforeInitView();
        initView(savedInstanceState);
    }

    /**
     * 界面跳转
     *
     * @param class1   要跳转到的界面
     * @param isFinish 是否结束当前的Activity (true 结束，false 不结束)
     * @param bundle   不需要传参数的时候传入空即可
     * @author GZY
     */
    protected void turnActivity(@NonNull Class class1, @NonNull boolean isFinish, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, class1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    /**
     * @param callBack 成功后才会返回
     * @Author: JACK-GU
     * @Date: 2018/1/11
     * @E-Mail: 528489389@qq.com
     * <p>
     * 检查权限,屏蔽了整个过程更加方便
     */
    protected void checkPermission(CallBack callBack) {
        this.callBack = callBack;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查一下啊，同意没有
            List<String> strings = new ArrayList<>();
            for (String str : AppConfig.PERMISSIONS) {
                if (!isPermissionGranted(str)) {
                    strings.add(str);
                }
            }
            if (strings.size() > 0) {
                requestPermissions(strings.toArray(new String[strings.size()]), ConstCode
                        .PERMISSIONS_REQUEST_CODE);
            } else {
                if (callBack != null)
                    callBack.callBack(true, new ArrayList<>());
            }
        } else {
            if (callBack != null)
                callBack.callBack(true, new ArrayList<>());
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 显示进度对话框,带有消息
     */
    protected void showProgressDialog(String message) {
        loadingDialog.show();
        loadingDialog.setMessage(message);
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

    /**
     * 界面跳转
     *
     * @param class1      要跳转到的界面
     * @param isFinish    是否结束当前的Activity (true 结束，false 不结束)
     * @param bundle      不需要传参数的时候传入空即可
     * @param RequestCode 请求码
     * @author GZY
     */
    protected void turnActivityForResult(@NonNull Class class1, @NonNull boolean isFinish, Bundle
            bundle, @NonNull int RequestCode) {
        Intent intent = new Intent();
        intent.setClass(this, class1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, RequestCode);
        if (isFinish) {
            finish();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //if (isNeedSwipeBack()) SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       // if (isNeedSwipeBack()) SwipeBackHelper.onDestroy(this);
        mUnbinder.unbind();
    }

    //目前有问题，当一个App中有Activity在不同线程，则不能正确退出
    protected void quitApp() {
        if (isFirstBack == 0) {
            ToastUtil.showShortMessage(getResources().getString(R.string.exit));
            isFirstBack = 1;
            if (null == mQuitTimer) {
                mQuitTimer = new Timer();
            } else {
                mQuitTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isFirstBack = 0;
                    }
                }, 1000);
            }

        } else if (isFirstBack == 1) {
            finish();
            System.exit(0);
        }
    }

    protected interface CallBack {
        /**
         * 如果success为false，说明有权限（permissions）没同意，所以需要自己判断一下
         */
        void callBack(boolean success, List<String> permissions);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == ConstCode.PERMISSIONS_REQUEST_CODE) {
            //自己检查，这个权限的状态就好了
            List<String> strings = new ArrayList<>();
            for (String str : permissions) {
                strings.add(str);
            }

            boolean flag = false;
            for (String str : permissions) {
                if (isPermissionPermanentlyDenied(str)) {
                    //用户不同意，而且不许在弹出来了
                    flag = true;
                    //整理特别提示一下
                } else if (isPermissionGranted(str)) {
                    //用户同意了，然后就可以移除了
                    strings.remove(str);
                }
            }

            if (flag) {
                //有的话，谈对话框提示用户
                MessageDialog messageDialog = new MessageDialog(this, MessageDialog.TYPE_ONE_BUTTON);
                messageDialog.show();
                messageDialog.setContent(getResources().getString(R.string
                        .permission_really_declined));
                messageDialog.getCenterButton().getTextView().setText(getResources().getString(R
                        .string.i_know));
                messageDialog.setOneButtonClickListener(v -> {
                    messageDialog.cancel();
                    if (callBack != null)
                        callBack.callBack(false, strings);
                });
            } else {
                if (strings.size() > 0) {
                    //说明有没同意的权限
                    //提示用户
                    ToastUtil.showShortMessage(getResources().getString(R.string
                            .permission_declined));
                    if (callBack != null)
                        callBack.callBack(false, strings);
                } else {
                    if (callBack != null)
                        callBack.callBack(true, strings);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * return true if permission is declined, false otherwise.
     */
    private boolean isPermissionDeclined(@NonNull String permissionsName) {
        return ActivityCompat.checkSelfPermission(this, permissionsName) != PackageManager
                .PERMISSION_GRANTED;
    }

    /**
     * return true if permission is granted, false otherwise.
     */
    private boolean isPermissionGranted(@NonNull String permissionsName) {
        return ActivityCompat.checkSelfPermission(this, permissionsName) == PackageManager
                .PERMISSION_GRANTED;
    }

    /**
     * @return true if explanation needed.
     */
    private boolean isExplanationNeeded(@NonNull String permissionName) {
        return ActivityCompat.shouldShowRequestPermissionRationale(this, permissionName);
    }

    /**
     * @return true if the permission is patently denied by the user and only can be granted via
     * settings Screen
     * <p/>
     */
    private boolean isPermissionPermanentlyDenied(@NonNull String permission) {
        return isPermissionDeclined(permission) && !isExplanationNeeded(permission);
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
