package com.jack.framework.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.jack.framework.enums.RxLifeEvent;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;

/**
 * 基础的处理器
 * <p>
 * 我们这里绑定了生命周期的东西
 * <p>
 * presenter 必须持有view和model的引用，所以我们用泛型，这个样子的话，就比较好
 *
 * @Author: JACK-GU
 * @Date: 2018-08-29 10:19
 * @E-Mail: 528489389@qq.com
 */
public abstract class BaseMvpPresenter<T extends BaseMvpView, R extends BaseMvpModel>
        implements LifecycleObserver {
    public BaseMvpPresenter(T view, R model) {
        this.mRootView = view;
        this.mModel = model;
        registerLifecycleObserver();
    }

    @Inject
    protected T mRootView;
    @Inject
    protected R mModel;

    protected final PublishSubject<RxLifeEvent> lifecycleSubject = PublishSubject.create();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        lifecycleSubject.onNext(RxLifeEvent.CREATE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        lifecycleSubject.onNext(RxLifeEvent.START);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        lifecycleSubject.onNext(RxLifeEvent.RESUME);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        lifecycleSubject.onNext(RxLifeEvent.PAUSE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        lifecycleSubject.onNext(RxLifeEvent.STOP);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        lifecycleSubject.onNext(RxLifeEvent.DESTROY);
        unRegisterLifecycleObserver();
    }

    /**
     * 这个是所有的都会调用的哦
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
    }

    /**
     * 用于绑定retrofit的这个
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    @NonNull
    public <T> ObservableTransformer<T, T> bindUntilEvent(@NonNull final RxLifeEvent event) {
        return upstream -> {
            Observable<RxLifeEvent> compareLifecycleObservable =
                    lifecycleSubject.filter(rxLifeEvent -> rxLifeEvent.equals(event)).take(1);
            return upstream.takeUntil(compareLifecycleObservable);
        };
    }

    /**
     * 这个方法需要调用哦，通知
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public abstract void init(Bundle savedInstanceState);


    /**
     * 注册生命周期的检测
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void registerLifecycleObserver() {
        if (mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
        }

        if (mModel instanceof LifecycleOwner) {
            ((LifecycleOwner) mModel).getLifecycle().addObserver(this);
        }
    }

    /**
     * 取消注册生命周期的检查
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    protected void unRegisterLifecycleObserver() {
        if (mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().removeObserver(this);
        }

        if (mModel instanceof LifecycleOwner) {
            ((LifecycleOwner) mModel).getLifecycle().removeObserver(this);
        }
    }
}
