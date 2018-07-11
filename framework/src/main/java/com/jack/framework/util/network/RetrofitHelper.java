package com.jack.framework.util.network;


import com.jack.framework.config.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 网络请求的Retrofit
 */
public class RetrofitHelper {
    private static volatile OkHttpClient.Builder clientBuilder;
    private static volatile Retrofit.Builder retrofitBuilder;
    private static volatile RetrofitHelper instance = null;

    private RetrofitHelper() {
        //添加一些默认的参数配置
        clientBuilder = new OkHttpClient.Builder();

        clientBuilder.retryOnConnectionFailure(true)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                //.addInterceptor(new AddParamsInterceptor()) //添加拦截器
                //.cookieJar(new CookieManger()) //添加cookie本地持久化
                .writeTimeout(20, TimeUnit.SECONDS);

        //添加一些默认的参数配置
        retrofitBuilder = new Retrofit.Builder();

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConfig.BASE_URL);
    }

    /**
     * @return 返回实例
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 获取对象
     */
    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }


    /**
     * 获取OkHttpClient实例
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private OkHttpClient getOkHttpClient() {
        return clientBuilder.build();
    }

    /**
     * OkHttpClient.Builder实例用于扩展,这个是新的builder不会影响基础的，
     * 所以你不能直接使用createService(Class<T> apiService)方法，因为这个是使用的基础的
     * Builder，需要使用createService(Class<T> apiService, OkHttpClient.Builder builder)
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return clientBuilder.build().newBuilder();
    }


    /**
     * Retrofit.Builder实例用于扩展,这个是新的builder不会影响基础的，
     * 所以你不能直接使用createService(Class<T> apiService)方法，因为这个是使用的基础的
     * Builder，需要使用createService(Class<T> apiService, Retrofit.Builder builder)
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Retrofit.Builder getRetrofitBuilder() {
        return retrofitBuilder.client(getOkHttpClient()).build().newBuilder();
    }

    /**
     * 获取Retrofit实例
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private Retrofit getRetrofit() {
        return retrofitBuilder.client(getOkHttpClient()).build();
    }

    /**
     * 创建service
     *
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     */
    public <T> T createService(Class<T> apiService) {
        return RetrofitHelper.getInstance().getRetrofit().create(apiService);
    }


    /**
     * 创建service
     *
     * @param apiService service接口的class
     * @param builder    okHttpClient的Builder
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     */
    public static <T> T createService(Class<T> apiService, OkHttpClient.Builder builder) {
        return RetrofitHelper.getInstance().getRetrofitBuilder()
                .client(builder.build())
                .build().create(apiService);
    }

    /**
     * 创建service
     *
     * @param apiService          service接口的class
     * @param okHttpClientBuilder okHttpClient的Builder
     * @param retrofitBuilder     retrofit的Builder
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     */
    public static <T> T createService(Class<T> apiService
            , OkHttpClient.Builder okHttpClientBuilder, Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.client(okHttpClientBuilder.build())
                .build().create(apiService);
    }


    /**
     * 创建service
     *
     * @param apiService service接口的class
     * @param builder    retrofit的Builder
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     */
    public static <T> T createService(Class<T> apiService, Retrofit.Builder builder) {
        return builder.build().create(apiService);
    }

}
