package com.jack.framework.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;

import com.jack.framework.enums.RxLifeEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;

/**
 * 基础的Model
 * <p>
 * 我们使用google的这个ViewModel帮助我们管理数据，这个是很有好的
 * <p>
 * 而且我们可以在Fragment之间共享数据的，如果你使用BaseMvpPresenter的话，需要在dagger的module中进行提供
 * <p>
 * ViewModelProviders.of((Fragment) mMvpView).get(TestContract.TestIModel.class)
 * <p>
 * 使用的时候，我们需要注意数据的恢复
 * <p>
 * public LiveData<List<User>> getUsers() {
 * <p>
 * //判断一下，不为空说明有数据，直接返回，比如屏幕旋转引起的操作
 * <p>
 * if (users == null) {
 * <p>
 * users = new MutableLiveData<List<User>>();
 * <p>
 * //加载数据，随便你怎么获取
 * <p>
 * }
 * <p>
 * return users;
 * <p>
 * }
 *
 * @Author: JACK-GU
 * @Date: 2018-08-30 13:39
 * @E-Mail: 528489389@qq.com
 * @see BaseMvpPresenter
 */
public abstract class BaseMvpModel extends ViewModel implements LifecycleObserver {
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
}
