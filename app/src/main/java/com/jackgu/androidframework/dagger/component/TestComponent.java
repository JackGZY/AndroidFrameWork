package com.jackgu.androidframework.dagger.component;

import com.jack.framework.scope.FragmentScope;
import com.jackgu.androidframework.dagger.module.TestModule;
import com.jackgu.androidframework.ui.adapter.MainAdapter;
import com.jackgu.androidframework.ui.fragment.TestFragment;

import dagger.Component;

/**
 * -
 *
 * @Author: JACK-GU
 * @Date: 2018-08-29 10:47
 * @E-Mail: 528489389@qq.com
 */
@FragmentScope
@Component(modules = {TestModule.class})
public interface TestComponent {
    /**
     * 这个方法，表示这个注入的生命周期就是这个TestFragment，注意
     * 这里这个不可以是父类，不然无法注入
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    void inject(TestFragment testFragment);

    /**
     * 这个方法，可以为别的依赖这个Component的提供这个实例
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    MainAdapter getMainAdapter();
}
