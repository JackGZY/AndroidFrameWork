package com.jack.framework.util.network.repository;


import android.accounts.NetworkErrorException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jack.framework.config.AppConfig;
import com.jack.framework.entity.BaseEntity;
import com.jack.framework.util.RefreshHelper;
import com.jack.framework.util.network.CodeException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 基础的封装
 */

public class BaseRepository<T> {
    private Class<T> tClass = null;

    /**
     * 如果你需要使用transformResultJsonObject或者transformResultJsonArray
     * 方法必须设置
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see MyJsonRepository
     */
    protected void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    protected Observable<T> transform(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected Observable<T> transformResultJsonObject(Observable<BaseEntity<JsonObject>>
                                                              observable) {
        return observable.compose(baseEntityObservable -> baseEntityObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<BaseEntity<JsonObject>, ObservableSource<T>>)
                        jsonObjectBaseEntity -> {
                            if (jsonObjectBaseEntity == null) {
                                return Observable.error(new NetworkErrorException());
                            } else {
                                if (jsonObjectBaseEntity.getCode()
                                        == AppConfig.DATA_SUCCESS_CODE) {
                                    Observable<T> tObservable = Observable.create(emitter -> {
                                        if (!emitter.isDisposed()) {
                                            emitter.onNext(getT(jsonObjectBaseEntity.getData()));
                                        }
                                    });
                                    return tObservable;
                                } else {
                                    //具体处理异常的问题,返回msg
                                    return Observable.error(new CodeException(
                                            jsonObjectBaseEntity.getMsg(),
                                            jsonObjectBaseEntity.getCode()));
                                }
                            }
                        }));
    }

    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected Observable<List<T>> transformResultJsonArray(Observable<BaseEntity<JsonArray>>
                                                                   observable) {
        return observable.compose(baseEntityObservable -> baseEntityObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<BaseEntity<JsonArray>, ObservableSource<List<T>>>)
                        jsonArrayBaseEntity -> {
                            if (jsonArrayBaseEntity == null) {
                                return Observable.error(new NetworkErrorException());
                            } else {
                                if (jsonArrayBaseEntity.getCode() == AppConfig.DATA_SUCCESS_CODE) {
                                    Observable<List<T>> tObservable =
                                            Observable.create(emitter -> {
                                                if (!emitter.isDisposed()) {
                                                    emitter.onNext(
                                                            getTFromArray(jsonArrayBaseEntity));
                                                }
                                            });
                                    return tObservable;
                                } else {
                                    //具体处理异常的问题,返回msg
                                    return Observable.error(new CodeException(
                                            jsonArrayBaseEntity.getMsg(),
                                            jsonArrayBaseEntity.getCode()));
                                }
                            }
                        }));
    }

    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected Observable<T> transformResult(Observable<BaseEntity<T>> observable) {
        return transformResult(observable, null);
    }


    //这里面主要是切换线程，如果我们的接口按照规则写，我们也可以在里面进行过滤操作
    protected Observable<T> transformResult(Observable<BaseEntity<T>> observable,
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
                                if (!emitter.isDisposed()) {
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
     * 获取T的实例
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected T getT(JsonObject jsonObject) {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonObject, tClass);
        return t;
    }

    /**
     * 获取T的实例
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected List<T> getTFromArray(BaseEntity<JsonArray> jsonArrayBaseEntity) {
        JsonArray jsonArray = jsonArrayBaseEntity.getData();
        List<T> ts = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            ts.add(getT(jsonArray.get(i).getAsJsonObject()));
        }

        return ts;
    }
}
