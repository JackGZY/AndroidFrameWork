package com.jackgu.androidframework.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.jack.framework.base.BaseTitleFragment;
import com.jack.framework.util.ItemDecorationUtil;
import com.jack.framework.util.RefreshHelper;
import com.jackgu.androidframework.R;
import com.jackgu.androidframework.contract.TestContract;
import com.jackgu.androidframework.dagger.component.DaggerTestComponent;
import com.jackgu.androidframework.dagger.module.TestModule;
import com.jackgu.androidframework.presenter.TestPresenter;
import com.jackgu.androidframework.ui.adapter.MainAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 测试的fragment
 *
 * @Author: JACK-GU
 * @Date: 2018/1/30 14:18
 * @E-Mail: 528489389@qq.com
 */
public class TestFragment extends BaseTitleFragment implements TestContract.TestIView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    //关联
    @Inject
    protected MainAdapter mainAdapter;
    @Inject
    protected RefreshHelper<String> refreshHelper;
    @Inject
    protected TestPresenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        setTitle("MVP+Dagger2示列");

        //注入
        DaggerTestComponent.builder().testModule(
                new TestModule(recyclerView, smartRefreshLayout,
                        mContext, this)).build().inject(this);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView
                    .State state) {
                ItemDecorationUtil.topAuto(outRect, view, parent, 10);
            }
        });

        mPresenter.init(savedInstanceState);
    }
}
