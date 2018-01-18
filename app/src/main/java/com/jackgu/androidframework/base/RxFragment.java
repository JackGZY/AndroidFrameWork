package com.jackgu.androidframework.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


import com.jackgu.androidframework.enums.RxLifeEvent;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

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
    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final RxLifeEvent event) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceObservable) {
                Observable<RxLifeEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<RxLifeEvent, Boolean>() {
                            @Override
                            public Boolean call(RxLifeEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return sourceObservable.takeUntil(compareLifecycleObservable);
            }
        };
    }
}
