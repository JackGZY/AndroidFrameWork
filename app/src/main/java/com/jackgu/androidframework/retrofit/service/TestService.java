package com.jackgu.androidframework.retrofit.service;


import com.jack.framework.entity.BaseEntity;
import com.jackgu.androidframework.entity.TestEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 测试的service
 *
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 */
public interface TestService {
    @FormUrlEncoded
    @POST("satinApi")
    Observable<BaseEntity<List<TestEntity>>> get(@FieldMap HashMap<String, Object> params);

    @POST("one")
    Call<ResponseBody> upload(@Body MultipartBody multipartBody);

    @FormUrlEncoded
    @POST("satinApi")
    Call<ResponseBody> getTest(@FieldMap HashMap<String, Object> params);
}
