package com.jack.framework.util.network.repository;


import android.accounts.NetworkErrorException;

import com.jack.framework.config.AppConfig;
import com.jack.framework.entity.BaseEntity;
import com.jack.framework.util.RefreshHelper;
import com.jack.framework.util.network.CodeException;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

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
        return observable.compose(baseEntityObservable -> baseEntityObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<BaseEntity<T>, ObservableSource<T>>) tBaseEntity -> {
                    if (tBaseEntity == null) {
                        return Observable.error(new NetworkErrorException());
                    } else {
                        if (tBaseEntity.getCode() == AppConfig.DATA_SUCCESS_CODE) {
                            Observable<T> tObservable = Observable.create(emitter -> {
                                if (emitter.isDisposed()) {
                                    emitter.onNext(tBaseEntity.getData());
                                }
                            });
                            return tObservable;
                        } else {
                            //具体处理异常的问题,返回msg
                            return Observable.error(new CodeException(tBaseEntity.getMsg(),
                                    tBaseEntity.getCode()));
                        }
                    }
                }));
    }


    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected <T> Observable<T> transformResult(Observable<BaseEntity<T>> observable,
                                                RefreshHelper refreshHelper) {
        return observable.compose(baseEntityObservable -> baseEntityObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<BaseEntity<T>, ObservableSource<T>>) tBaseEntity -> {
                    if (tBaseEntity == null) {
                        return Observable.error(new NetworkErrorException());
                    } else {
                        if (tBaseEntity.getCode() == AppConfig.DATA_SUCCESS_CODE) {
                            Observable<T> tObservable = Observable.create(emitter -> {
                                if (emitter.isDisposed()) {
                                    emitter.onNext(tBaseEntity.getData());
                                }
                            });
                            return tObservable;
                        } else {
                            //具体处理异常的问题,返回msg
                            return Observable.error(new CodeException(tBaseEntity.getMsg(),
                                    tBaseEntity.getCode()));
                        }
                    }
                }));
    }


    /**
     * 添加固定参数
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void addParams(Map<String, Object> hashMap) {
    }

    /**
     * 添加固定参数
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void addParamsRequestBody(Map<String, RequestBody> hashMap) {
    }

}
