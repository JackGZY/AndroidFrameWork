package com.jack.framework.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jack.framework.enums.RxLifeEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;


/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 根据Activity生命周期判断Retrofit的网络请求与否
 */

abstract class RxActivity extends AppCompatActivity {
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
        closeKeyboard();
        super.onDestroy();
    }

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

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
