package com.jackgu.androidframework.util.network.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author: JACK-GU
 * @Date: 2017-07-24
 * @E-Mail: 528489389@qq.com
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DownloadProgressListener listener;

    public DownloadProgressInterceptor(DownloadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), listener))
                .build();
    }
}