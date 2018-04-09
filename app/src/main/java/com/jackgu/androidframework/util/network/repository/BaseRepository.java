package com.jackgu.androidframework.util.network.repository;


import android.accounts.NetworkErrorException;


import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.entity.BaseEntity;
import com.jackgu.androidframework.util.network.CodeException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 基础的封装
 */

public class BaseRepository {
    protected <T> Observable<T> transform(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected <T> Observable<T> transformResult(Observable<BaseEntity<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(result -> {
                    if (result == null) {
                        return Observable.error(new NetworkErrorException());
                    } else {
                        if (result.getCode() == AppConfig.DATA_SUCCESS_CODE) {
                            return Observable.just(result.getData());
                        } else {
                            //具体处理异常的问题,返回msg
                            return Observable.error(new CodeException(result.getMsg(), result
                                    .getCode()));
                        }
                    }
                });
    }


}
