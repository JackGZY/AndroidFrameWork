package com.jackgu.androidframework.contract;


import android.arch.lifecycle.MutableLiveData;

import com.jack.framework.base.BaseMvpModel;
import com.jack.framework.base.BaseMvpPresenter;
import com.jack.framework.base.BaseMvpView;

import java.util.List;

/**
 * 测试的约束
 *
 * @Author: JACK-GU
 * @Date: 2018-08-29 10:33
 * @E-Mail: 528489389@qq.com
 */
public class TestContract {
    public interface TestIView extends BaseMvpView {
        //定义一些方法
    }

    public static abstract class TestIModel extends BaseMvpModel {
        //自己定义一些方法
        public abstract MutableLiveData<List<String>> getData();

        public abstract void refresh();

        public abstract void loadMore();
    }


    /**
     * 动态模板生成contractPresenter
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static abstract class TestIPresenter<T extends BaseMvpView, R extends BaseMvpModel>
            extends BaseMvpPresenter<T, R> {

        public TestIPresenter(T view, R model) {
            super(view, model);
        }

        //定义一些方法
        public abstract void clickOnAdd();
    }
}
