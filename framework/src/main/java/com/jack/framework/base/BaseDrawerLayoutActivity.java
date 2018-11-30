package com.jack.framework.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jack.framework.util.DensityUtil;


/**
 * 快速集成DrawerLayout,可以选择是否开启透明状态栏
 *
 * @Author: JACK-GU
 * @Date: 2018/1/19
 * @E-Mail: 528489389@qq.com
 */

public abstract class BaseDrawerLayoutActivity extends BaseTitleActivity {
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
     * 获得DrawerArrowDrawable的颜色，默认白色
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected int getDrawerArrowDrawableColor() {
        return Color.WHITE;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setBackOnClickListener(v -> drawerLayout.openDrawer(Gravity.LEFT));

        DrawerArrowDrawable drawerArrowDrawable = new DrawerArrowDrawable(mContext);
        drawerArrowDrawable.setColor(getDrawerArrowDrawableColor());
        setBackImageDrawable(drawerArrowDrawable);
        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                drawerArrowDrawable.setProgress(slideOffset);
            }
        });
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
            layoutParams.width = DensityUtil.dip2px(getDrawerWidth());
            drawerView.setLayoutParams(layoutParams);
            drawerLayout.addView(drawerView, 1);

            //什么都不做，防止覆盖了没写点击事件，响应覆盖层下面的事件
            drawerView.setOnClickListener(v -> {
            });
        }

        return drawerLayout;
    }
}
