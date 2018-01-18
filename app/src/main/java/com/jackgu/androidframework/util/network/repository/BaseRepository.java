package com.jackgu.androidframework.util.network.repository;


import android.accounts.NetworkErrorException;

import com.jackgu.androidframework.util.network.RetrofitHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 基础的封装
 */

public class BaseRepository {
    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected <T> Observable<T> transform(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
