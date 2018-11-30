package com.jack.framework.view;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 防止崩溃
 *
 * @Author: JACK-GU
 * @Date: 2018/3/30 10:05
 * @E-Mail: 528489389@qq.com
 */
public class WrapContentGridLayoutManager extends GridLayoutManager{
    public WrapContentGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WrapContentGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public WrapContentGridLayoutManager(Context context, int spanCount, int orientation, boolean
            reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
