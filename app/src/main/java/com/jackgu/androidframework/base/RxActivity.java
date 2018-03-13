package com.jackgu.androidframework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;


import com.jackgu.androidframework.enums.RxLifeEvent;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 根据Activity生命周期判断Retrofit的网络请求与否
 */

abstract class RxActivity extends FragmentActivity {
    protected final PublishSubject<RxLifeEvent> lifecycleSubject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(RxLifeEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(RxLifeEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(RxLifeEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(RxLifeEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(RxLifeEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(RxLifeEvent.DESTROY);
        super.onDestroy();
    }

    @NonNull
    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final RxLifeEvent event) {
        /*return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceObservable) {
                //发射满足条件的第一条数据
                Observable<RxLifeEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<RxLifeEvent, Boolean>() {
                            @Override
                            public Boolean call(RxLifeEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                //当compareLifecycleObservable发射数据后，sourceObservable(原数据源)就会舍弃后面的全部数据
                return sourceObservable.takeUntil(compareLifecycleObservable);
            }
        };*/

        return sourceObservable -> {
            //发射满足条件的第一条数据
            Observable<RxLifeEvent> compareLifecycleObservable =
                    lifecycleSubject.takeFirst(activityLifeCycleEvent -> activityLifeCycleEvent
                            .equals(event));
            //当compareLifecycleObservable发射数据后，sourceObservable(原数据源)就会舍弃后面的全部数据
            return sourceObservable.takeUntil(compareLifecycleObservable);
        };
    }
}
