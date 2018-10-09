package com.jackgu.androidframework.model;

import android.arch.lifecycle.MutableLiveData;

import com.jackgu.androidframework.contract.TestContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试的model
 *
 * @Author: JACK-GU
 * @Date: 2018-08-30 13:47
 * @E-Mail: 528489389@qq.com
 */
public class TestModel extends TestContract.TestIModel {
    MutableLiveData<List<String>> liveData = null;

    public MutableLiveData<List<String>> getData() {
        //这个代码很关键是重汇页面后保存的数据恢复的关键代码，因为我们继承的是ViewModel
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }


    @Override
    public void refresh() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //模拟数据库拿数据
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                strings.add("refresh[ " + i + " ]");
            }
            //setValue必须在主线程调用，postValue可以在子线程，都会使订阅的页面处于活跃的时候收到更新
            liveData.postValue(strings);
        }).start();
    }

    @Override
    public void loadMore() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //模拟数据库拿数据
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                strings.add("loadMore[ " + i + " ]");
            }
            //setValue必须在主线程调用，postValue可以在子线程，都会使订阅的页面处于活跃的时候收到更新
            liveData.postValue(strings);
        }).start();
    }
}
