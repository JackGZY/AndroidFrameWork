package com.jack.framework.util.network;

import com.jack.framework.config.AppConfig;
import com.jack.framework.util.network.interceptor.DownloadProgressInterceptor;
import com.jack.framework.util.network.interceptor.DownloadProgressListener;

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
public class RetrofitDownloadHelper {
    private OkHttpClient.Builder builder;
    private Retrofit sRetrofit;

    public Retrofit getsRetrofit() {
        return sRetrofit;
    }

    public void setsRetrofit(Retrofit sRetrofit) {
        this.sRetrofit = sRetrofit;
    }

    public RetrofitDownloadHelper(CallBack callBack) {
        builder = new OkHttpClient.Builder().addInterceptor(new DownloadProgressInterceptor(new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                if (callBack != null) {
                    callBack.callBack(bytesRead, contentLength, done);
                }
            }
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

    public interface CallBack {
        void callBack(long bytesRead, long contentLength, boolean done);
    }

}
