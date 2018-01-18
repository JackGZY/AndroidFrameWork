package com.jackgu.androidframework.util.network;

import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.eventAction.DownloadFileMessageEvent;
import com.jackgu.androidframework.util.network.interceptor.DownloadProgressInterceptor;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 网络请求的Retrofit
 */
public class RetrofitHelper {
    private static volatile OkHttpClient.Builder builder;
    private static volatile Retrofit sRetrofit;
    private static volatile RetrofitHelper instance = null;

    private RetrofitHelper() {
        builder = new OkHttpClient.Builder().addInterceptor(new DownloadProgressInterceptor(
                (bytesRead, contentLength, done) -> {
                    //这里发送事件
                    EventBus.getDefault().post(new DownloadFileMessageEvent(bytesRead, contentLength,
                            done));
                }));

        OkHttpClient client = builder
                .retryOnConnectionFailure(true)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

        sRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(AppConfig.BASE_URL)
                .build();
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
     * @Author: JACK-GU
     * @Date: 2018/1/12
     * @E-Mail: 528489389@qq.com
     * <p>
     * 创建service
     */
    public <T> T createService(Class<T> apiService) {
        return RetrofitHelper.getInstance().sRetrofit.create(apiService);
    }

}
