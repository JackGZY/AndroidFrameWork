package com.jackgu.androidframework.retrofit.service;


import junit.framework.Test;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 测试的service
 */
public interface TestService {
    @FormUrlEncoded
    @POST("0a953068ff01781ce22c0822c075018c")
    Observable<Test> get(@FieldMap HashMap<String, Object> params);
}
