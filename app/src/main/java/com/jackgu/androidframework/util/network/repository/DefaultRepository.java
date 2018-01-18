package com.jackgu.androidframework.util.network.repository;


import com.jackgu.androidframework.util.network.RetrofitHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 标准的模式，可以直接调用这个，即service是：
 * @FormUrlEncoded
 * @POST("0a953068ff01781ce22c0822c075018c") Observable<Test> get(@FieldMap HashMap<String,
 * Object> params);
 * </p>
 */

public class DefaultRepository extends BaseRepository {
    private static volatile DefaultRepository instance = null;

    private DefaultRepository() {

    }


    /**
     * @return 返回实例
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 获取对象
     */
    public static DefaultRepository getInstance() {
        if (instance == null) {
            synchronized (DefaultRepository.class) {
                if (instance == null) {
                    instance = new DefaultRepository();
                }
            }
        }

        return instance;
    }

    /**
     * 本方法调用条件T为:
     * <p>
     * 1.retrofit的service;
     * <p>
     * 2.service的方法参数是map
     * <p>
     * 3.并且这个方法的返回是Observable<R>
     * <p>
     * 4.注意判断null
     *
     * @param c          service的class
     * @param r          返回的Observable<R>的R的class
     * @param methodName service的方法名字
     * @param map        参数
     * @return 返回一个 Observable<R> 对象,是切换了线程的可以直接更新UI
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     */
    public <T, R> Observable<R> submit(Class<T> c, Class<R> r, String methodName, Map<String,
            Object> map) {
        T t = RetrofitHelper.getInstance().createService(c);
        Observable<R> rObservable = null;
        try {
            Method declaredMethod = t.getClass().getDeclaredMethod(methodName, HashMap.class);
            rObservable = (Observable<R>) declaredMethod.invoke(t, map);
            //如果不为空
            if (rObservable != null) {
                rObservable = transform(rObservable);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }
}
