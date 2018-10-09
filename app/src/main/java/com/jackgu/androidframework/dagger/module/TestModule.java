package com.jackgu.androidframework.dagger.module;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.jack.framework.scope.FragmentScope;
import com.jack.framework.util.RefreshHelper;
import com.jackgu.androidframework.R;
import com.jackgu.androidframework.contract.TestContract;
import com.jackgu.androidframework.model.TestModel;
import com.jackgu.androidframework.ui.adapter.MainAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import dagger.Module;
import dagger.Provides;

/**
 * -
 *
 * @Author: JACK-GU
 * @Date: 2018-08-29 10:47
 * @E-Mail: 528489389@qq.com
 */
@Module
public class TestModule {
    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;
    Context mContext;
    TestContract.TestIView mMvpView;

    public TestModule(RecyclerView recyclerView, SmartRefreshLayout smartRefreshLayout
            , Context context, TestContract.TestIView baseMvpView) {
        this.recyclerView = recyclerView;
        this.smartRefreshLayout = smartRefreshLayout;
        this.mContext = context;
        this.mMvpView = baseMvpView;
    }

    @FragmentScope
    @Provides
    RefreshHelper<String> getRefreshHelper(MainAdapter mainAdapter) {
        return new RefreshHelper<>(smartRefreshLayout, recyclerView, mainAdapter, mContext);
    }

    @FragmentScope
    @Provides
    TestContract.TestIModel getBaseMvpModel() {
        return ViewModelProviders.of((Fragment) mMvpView).get(TestModel.class);
    }


    @FragmentScope
    @Provides
    TestContract.TestIView getBaseMvpView() {
        return this.mMvpView;
    }


    @FragmentScope
    @Provides
    MainAdapter getMainAdapter() {
        return new MainAdapter(R.layout.item_main);
    }
}
