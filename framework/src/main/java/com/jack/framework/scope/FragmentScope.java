package com.jack.framework.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 这个其实没有什么意义，但是能标记组件的使用范围，区别
 * <p>
 * <p>
 * 注意，你如果在module标记了这个，那么component也必须标记
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果相关联的Component，Scope不能一样，一般来说我们定义一个ActivityScope，一个FragmentScope就够了
 * dagger提供的@Singleton一般用于应用程序级别的Component，在应用程序中暴露出来就可以了
 *
 * @Author: JACK-GU
 * @Date: 2018-08-29 13:59
 * @E-Mail: 528489389@qq.com
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}