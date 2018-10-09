package com.jackgu.androidframework.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.os.Bundle;

import com.jack.framework.scope.FragmentScope;
import com.jack.framework.util.LoggerUtil;
import com.jack.framework.util.RefreshHelper;
import com.jackgu.androidframework.contract.TestContract;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import javax.inject.Inject;

/**
 * 测试的控制器
 *
 * @Author: JACK-GU
 * @Date: 2018-08-29 10:38
 * @E-Mail: 528489389@qq.com
 */
@FragmentScope
public class TestPresenter extends TestContract.TestIPresenter
        <TestContract.TestIView, TestContract.TestIModel> {

    @Inject
    protected RefreshHelper<String> refreshHelper;

    private boolean isRefresh = false;

    /**
     * 用 @Inject来提供这个实例，好处是即使我们TestPresenter没有使用注入，
     * 但是成员被标记了的@Inject也会被注入实例
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    @Inject
    public TestPresenter(TestContract.TestIView view, TestContract.TestIModel model) {
        super(view, model);
    }


    @Override
    public void clickOnAdd() {
        //添加数据
        refreshHelper.add("test-clickOnAdd");
    }

    @Override
    public void init(Bundle savedInstanceState) {
        refreshHelper.setTotal(12);
        refreshHelper.setOnRefreshAndLoadMoreListener(new RefreshHelper
                .OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000, true);
                isRefresh = true;
                mModel.refresh();
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000, true, false);
                isRefresh = false;
                mModel.loadMore();
            }
        });


        //只有当有新的数据设置进来后才会有新的数据变化，并且是当前的页面是处于活跃状态
        //mRootView是fragment所以是没问题的
        mModel.getData().observe((LifecycleOwner) mRootView, strings -> {
            LoggerUtil.e("有新的数据变化：" + strings.size());
            if (isRefresh) {
                refreshHelper.reSet(strings);
            } else {
                refreshHelper.add(strings);
            }
        });


        //自动刷新
        refreshHelper.autoRefresh();


        /***********************************************
         *  一下功能只是演示用法 by JACK-GU 2018-08-30 15:55
         ***********************************************/

        /****** 伟大的分割线 ****** LiveData转换 ****** start ******/
        LiveData<Integer> integerLiveData =
                Transformations.map(mModel.getData(), input -> Integer.parseInt("1"));
        /****** 伟大的分割线 ****** LiveData转换 ****** end ******/

        /****** 伟大的分割线 ****** 合并LiveData ****** start ******/
        //当一个liveData的数据发生变化的时候所有的都可以收到这个
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        //添加liveData
        mediatorLiveData.addSource(integerLiveData, o -> {

        });
        /****** 伟大的分割线 ****** 合并LiveData ****** end ******/

    }
}
