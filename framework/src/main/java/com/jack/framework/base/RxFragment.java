package com.jack.framework.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jack.framework.enums.RxLifeEvent;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;


/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 根据Fragment生命周期判断Retrofit的网络请求与否
 */
abstract class RxFragment extends Fragment {
    protected final PublishSubject<RxLifeEvent> lifecycleSubject = PublishSubject.create();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        lifecycleSubject.onNext(RxLifeEvent.ATTACH);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(RxLifeEvent.CREATE);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(RxLifeEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(RxLifeEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(RxLifeEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(RxLifeEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(RxLifeEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(RxLifeEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(RxLifeEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(RxLifeEvent.DETACH);
        super.onDetach();
    }

    //监听Frament声明周期，当Fragment销毁后，停止网络请求
    @NonNull
    public <T> ObservableTransformer<T, T> bindUntilEvent(@NonNull final RxLifeEvent event) {
       /* return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<RxLifeEvent> compareLifecycleObservable =
                        lifecycleSubject.filter(new Predicate<RxLifeEvent>() {
                            @Override
                            public boolean test(RxLifeEvent rxLifeEvent) {
                                return rxLifeEvent.equals(event);
                            }
                        }).take(1);
                return upstream.takeUntil(compareLifecycleObservable);
            }
        };*/

        return upstream -> {
            Observable<RxLifeEvent> compareLifecycleObservable =
                    lifecycleSubject.filter(rxLifeEvent -> rxLifeEvent.equals(event)).take(1);
            return upstream.takeUntil(compareLifecycleObservable);
        };


        //这个是rx1的写法
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
        };
        return sourceObservable -> {
            //发射满足条件的第一条数据
            Observable<RxLifeEvent> compareLifecycleObservable =
                    lifecycleSubject.takeFirst(activityLifeCycleEvent -> activityLifeCycleEvent
                            .equals(event));
            //当compareLifecycleObservable发射数据后，sourceObservable(原数据源)就会舍弃后面的全部数据
            return sourceObservable.takeUntil(compareLifecycleObservable);
        };*/
    }
}
