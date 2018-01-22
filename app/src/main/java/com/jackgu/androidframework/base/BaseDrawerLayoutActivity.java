package com.jackgu.androidframework.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jackgu.androidframework.util.DensityUtil;

/**
 * 快速集成DrawerLayout,可以选择是否开启透明状态栏
 *
 * @Author: JACK-GU
 * @Date: 2018/1/19
 * @E-Mail: 528489389@qq.com
 */

public abstract class BaseDrawerLayoutActivity extends BaseActivity {
    protected DrawerLayout drawerLayout;

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected View getLayoutView() {
        //创建view
        return createRootView();
    }

    /**
     * drawer在那边，不从写的话就是左边，只能是左边和右边
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected int drawerFromGravity() {
        return Gravity.LEFT;
    }


    /**
     * 获得ContentView，必须是int的资源或者是view
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected abstract Object getContentView();


    /**
     * 获得DrawerView，必须是int的资源或者是view
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected abstract Object getDrawerView();

    /**
     * 获得DrawerView的宽度DP
     *
     * @Author: JACK-GU
     * @Date: 2018/1/19
     * @E-Mail: 528489389@qq.com
     */
    protected abstract float getDrawerWidth();

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


    private DrawerLayout createRootView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isTranslucentStatus()) {
            setTranslucentStatus(true);
        }

        //创建DrawerLayout
        drawerLayout = new DrawerLayout(mContext);

        LinearLayout.LayoutParams drawerLayoutLayoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        drawerLayout.setLayoutParams(drawerLayoutLayoutParams);


        //这里就需要加入两个view，第一个是内容，第二个是drawerLayout的内容
        Object o = getContentView();
        View contentView = null;
        if (o instanceof View) {
            contentView = (View) o;
        } else if (o instanceof Integer) {
            contentView = LayoutInflater.from(mContext).inflate((int) o, null);
        }
        if (contentView != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(layoutParams);
            drawerLayout.addView(contentView, 0);
        }


        o = getDrawerView();
        View drawerView = null;
        if (o instanceof View) {
            drawerView = (View) o;
        } else if (o instanceof Integer) {
            drawerView = LayoutInflater.from(mContext).inflate((int) o, null);
        }
        if (drawerView != null) {
            DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams(ViewGroup
                    .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = drawerFromGravity();//不设置这个不得行，因为源码是根据左右判断的
            layoutParams.width = DensityUtil.dip2px(mContext, getDrawerWidth());
            drawerView.setLayoutParams(layoutParams);
            drawerLayout.addView(drawerView, 1);
        }

        return drawerLayout;
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
