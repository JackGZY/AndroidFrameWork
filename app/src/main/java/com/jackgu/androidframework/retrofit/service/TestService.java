package com.jackgu.androidframework.retrofit.service;


import com.jack.framework.entity.BaseEntity;
import com.jackgu.androidframework.entity.TestEntity;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
    @POST("satinApi")
    Observable<BaseEntity<List<TestEntity>>> get(@FieldMap HashMap<String, Object> params);

    @FormUrlEncoded
    @POST("satinApi")
    Call<ResponseBody> getTest(@FieldMap HashMap<String, Object> params);
}
