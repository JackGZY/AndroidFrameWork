package com.jack.framework.util.network.repository;

import com.jack.framework.entity.BaseEntity;
import com.jack.framework.util.RefreshHelper;
import com.jack.framework.util.network.RetrofitHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * 响应,T表示最后需要的数据，不用创建那么多的repository
 * <p>
 * 建议使用这个来进行网络请求，上次文件，已经完成封装了
 * <p>
 * 普通请求：get
 * <p>
 * 上传文件：uploadFile
 * <p>
 * 列表操作：getList
 *
 * @Author: JACK-GU
 * @Date: 2018-06-12 10:21
 * @E-Mail: 528489389@qq.com
 */
public class MyRepository<T> extends BaseRepository {
    //默认的方法名字,可以少传一个参数，最好是保持一致的
    private final static String DEFAULT_METHOD_NAME = "get";

    /**
     * 开始请求,返回必须是BaseEntity<T>
     *
     * @param serviceClass service的class
     * @param methodName   service的方法名字
     * @param params       参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> get(Class serviceClass, String methodName, Map<String, Object> params) {
        Observable<T> rObservable = null;
        try {
            addParams(params);
            Object o = RetrofitHelper.getInstance().createService(serviceClass);
            Method declaredMethod = o.getClass().getDeclaredMethod(methodName, HashMap.class);
            Observable<BaseEntity<T>> baseEntityObservable = (Observable<BaseEntity<T>>)
                    declaredMethod.invoke(o, params);
            rObservable = transformResult(baseEntityObservable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }


    /**
     * 开始请求，配合refreshHelper,你也可以在这里设置total,会自动添加一个参数，叫page
     * ,返回必须是BaseEntity<T>
     *
     * @param serviceClass  service的class
     * @param methodName    service的方法名字
     * @param params        参数
     * @param refreshHelper 刷新辅助类，现在只是穿进来，你可以在里面做些操作，比如设置total，获取页数,
     *                      具体看接口的实现
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> getList(Class serviceClass, String methodName, Map<String, Object> params,
                                 RefreshHelper refreshHelper) {
        Observable<T> rObservable = null;
        try {
            addParams(params);
            Object o = RetrofitHelper.getInstance().createService(serviceClass);
            Method declaredMethod = o.getClass().getDeclaredMethod(methodName, HashMap.class);
            Observable<BaseEntity<T>> baseEntityObservable = (Observable<BaseEntity<T>>)
                    declaredMethod.invoke(o, params);
            rObservable = transformResult(baseEntityObservable, refreshHelper);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }


    /**
     * 开始请求,返回必须是BaseEntity<T>
     * <p>
     * 1. 放入一个普通参数
     * <p>
     * map.put("mobile", RequestBody.create(MediaType.parse("multipart/form-data"),
     * AppConfig.userInfo.getUsername()));
     * <p>
     * 2. 放入一个文件参数
     * <p>
     * MultipartBody.Part part = MultipartBodyHelper.transform(path, "header");
     * <p>
     * 定义service的时候
     * <p>
     * \@Multipart
     * <p>
     * \@POST("UserCenter/UpdateUserHeader.ashx")
     * <p>
     * Observable<BaseEntity<UserInfo>> get(@PartMap HashMap<String, RequestBody> params)
     * <p>
     * </p>
     *
     * @param serviceClass service的class
     * @param methodName   service的方法名字
     * @param params       参数，这个适合传文件的时候使用
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> uploadFile(Class serviceClass, String methodName,
                                    Map<String, RequestBody> params) {
        Observable<T> rObservable = null;
        try {
            addParamsRequestBody(params);
            Object o = RetrofitHelper.getInstance().createService(serviceClass);
            Method declaredMethod = o.getClass().getDeclaredMethod(methodName, HashMap.class);
            Observable<BaseEntity<T>> baseEntityObservable = (Observable<BaseEntity<T>>)
                    declaredMethod.invoke(o, params);
            rObservable = transformResult(baseEntityObservable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }


    /**
     * 开始请求,返回必须是BaseEntity<T>,默认的service方法：请看DEFAULT_METHOD_NAME
     *
     * @param serviceClass service的class
     * @param params       参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> get(Class serviceClass, Map<String, Object> params) {
        Observable<T> rObservable = null;
        try {
            addParams(params);
            Object o = RetrofitHelper.getInstance().createService(serviceClass);
            Method declaredMethod = o.getClass().getDeclaredMethod(DEFAULT_METHOD_NAME,
                    HashMap.class);
            Observable<BaseEntity<T>> baseEntityObservable = (Observable<BaseEntity<T>>)
                    declaredMethod.invoke(o, params);
            rObservable = transformResult(baseEntityObservable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }


    /**
     * 开始请求，配合refreshHelper,你也可以在这里设置total,会自动添加一个参数，叫page
     * ,返回必须是BaseEntity<T>,默认的service方法：请看DEFAULT_METHOD_NAME
     *
     * @param serviceClass  service的class
     * @param params        参数
     * @param refreshHelper 刷新辅助类，现在只是穿进来，你可以在里面做些操作，比如设置total，获取页数,
     *                      具体看接口的实现
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> getList(Class serviceClass, Map<String, Object> params,
                                 RefreshHelper refreshHelper) {
        Observable<T> rObservable = null;
        try {
            addParams(params);
            Object o = RetrofitHelper.getInstance().createService(serviceClass);
            Method declaredMethod = o.getClass().getDeclaredMethod(DEFAULT_METHOD_NAME, HashMap.class);
            Observable<BaseEntity<T>> baseEntityObservable = (Observable<BaseEntity<T>>)
                    declaredMethod.invoke(o, params);
            rObservable = transformResult(baseEntityObservable, refreshHelper);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }


    /**
     * 开始请求,返回必须是BaseEntity<T>,默认的service方法：请看DEFAULT_METHOD_NAME
     * <p>
     * 1. 放入一个普通参数
     * <p>
     * map.put("mobile", RequestBody.create(MediaType.parse("multipart/form-data"),
     * AppConfig.userInfo.getUsername()));
     * <p>
     * 2. 放入一个文件参数
     * <p>
     * MultipartBody.Part part = MultipartBodyHelper.transform(path, "header");
     * <p>
     * 定义service的时候
     * <p>
     * \@Multipart
     * <p>
     * \@POST("UserCenter/UpdateUserHeader.ashx")
     * <p>
     * Observable<BaseEntity<UserInfo>> get(@PartMap HashMap<String, RequestBody> params)
     * <p>
     * </p>
     *
     * @param serviceClass service的class
     * @param params       参数，这个适合传文件的时候使用
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> uploadFile(Class serviceClass, Map<String, RequestBody> params) {
        Observable<T> rObservable = null;
        try {
            addParamsRequestBody(params);
            Object o = RetrofitHelper.getInstance().createService(serviceClass);
            Method declaredMethod = o.getClass().getDeclaredMethod(DEFAULT_METHOD_NAME,
                    HashMap.class);
            Observable<BaseEntity<T>> baseEntityObservable = (Observable<BaseEntity<T>>)
                    declaredMethod.invoke(o, params);
            rObservable = transformResult(baseEntityObservable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return rObservable;
        }
    }
}
