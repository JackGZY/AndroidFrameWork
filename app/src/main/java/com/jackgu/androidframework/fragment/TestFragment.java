package com.jackgu.androidframework.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jack.framework.base.BaseTitleFragment;
import com.jack.framework.util.ItemDecorationUtil;
import com.jack.framework.util.RefreshHelper;
import com.jack.framework.util.ToastUtil;
import com.jackgu.androidframework.R;
import com.jackgu.androidframework.adapter.MainAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * 测试的fragment
 *
 * @Author: JACK-GU
 * @Date: 2018/1/30 14:18
 * @E-Mail: 528489389@qq.com
 */
public class TestFragment extends BaseTitleFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private RefreshHelper<String> refreshHelper;
    private MainAdapter mainAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView
                    .State state) {
                ItemDecorationUtil.topAuto(outRect, view, parent, 10);
            }
        });
        mainAdapter = new MainAdapter(R.layout.item_main);
        refreshHelper = new RefreshHelper<>(smartRefreshLayout, recyclerView, mainAdapter,
                mContext);
        refreshHelper.setTotal(12);

        refreshHelper.setOnRefreshAndLoadMoreListener(new RefreshHelper
                .OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                List<String> strings = new ArrayList<>();
                                for (int i = 0; i < 10; i++) {
                                    strings.add("" + i);
                                }
                                refreshLayout.finishRefresh();
                                refreshHelper.reSet(strings);
                            }
                        });
                    }
                }, 3 * 1000);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                List<String> strings = new ArrayList<>();
                                for (int i = 0; i < 10; i++) {
                                    strings.add("" + i);
                                }
                                refreshLayout.finishLoadMore();
                                refreshHelper.reSet(strings);
                            }
                        });
                    }
                }, 3 * 1000);
            }
        });

        addRightButton(R.drawable.menu_add, v -> ToastUtil.showShortMessage("add"));
    }
}
