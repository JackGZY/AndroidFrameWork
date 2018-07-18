package com.jack.framework.util.network.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jack.framework.entity.BaseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * 包含所以的apiService
 * <p>
 * 每一个是JsonObject作为实体类，然后在Repository里面转成对应的实体
 * <p>
 * 适合公司框架使用，如果每个的uri地址不同，可以使用注解的方式传进来，配合MyJsonRepository使用
 * <p>
 * 比如：<p>
 *
 * @ POST()<p>
 * Observable<ResponseBody> postBody(@Url String url, @Body Object object);
 * </p>
 * 或者：<p>
 * @ FormUrlEncoded<p>
 * @ POST("{path}/satinApi")<p>
 * Observable<BaseEntity<JsonArray>> get(@Path("path") String path, @FieldMap HashMap<String,
 * Object> params);
 * @Author: JACK-GU
 * @Date: 2018-07-11 16:41
 * @see com.jack.framework.util.network.repository.MyJsonRepository
 * @E-Mail: 528489389@qq.com
 */
public interface ApiService {

    /**
     * 最基本的获取数据，但是结果是数组类型的
     *
     * @param params 参数
     */
    @FormUrlEncoded
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> postReturnArray(@FieldMap HashMap<String, Object> params);

    /**
     * 获取数据
     *
     * @param object 一个实体类
     */
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> postReturnArray(@Body Object object);

    /**
     * 获取数据，参数是json
     *
     * @param jsonBody 就送字符串的请求体
     */
    @POST("satinApi")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<BaseEntity<JsonArray>> postReturnArray(@Body RequestBody jsonBody);

    /**
     * 上传文件，参数是文件的part，不支持文件参数上传
     *
     * @param file 文件part
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> uploadFileReturnArray(@Part() MultipartBody.Part file);

    /**
     * 上传多个文件，参数是文件的RequestBody，
     * 支持携带参数
     *
     * @param maps map类型RequestBody
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> uploadFilesReturnArray(@PartMap() Map<String, RequestBody>
                                                                     maps);

    /**
     * 上传多个文件，参数是文件的part，
     * 支持携带参数
     *
     * @param parts 文件part的数组
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> uploadFilesReturnArray(@Part() List<MultipartBody.Part>
                                                                     parts);


    /**
     * 最基本的获取数据，但是结果是数组类型的
     *
     * @param params 参数
     * @param path   需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @FormUrlEncoded
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> postReturnArray(@FieldMap HashMap<String, Object> params
            , @Path("path") String path);

    /**
     * 获取数据
     *
     * @param object 一个实体类
     * @param path   需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> postReturnArray(@Body Object object
            , @Path("path") String path);

    /**
     * 获取数据，参数是json
     *
     * @param jsonBody 就送字符串的请求体
     * @param path     需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @POST("satinApi")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<BaseEntity<JsonArray>> postReturnArray(@Body RequestBody jsonBody
            , @Path("path") String path);

    /**
     * 上传文件，参数是文件的part，不支持文件参数上传
     *
     * @param file 文件part
     * @param path 需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> uploadFlieReturnArray(@Part() MultipartBody.Part file
            , @Path("path") String path);

    /**
     * 上传多个文件，参数是文件的RequestBody，
     * 支持携带参数
     *
     * @param maps map类型RequestBody
     * @param path 需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> uploadFilesReturnArray(
            @PartMap() Map<String, RequestBody> maps
            , @Path("path") String path);

    /**
     * 上传多个文件，参数是文件的part，
     * 支持携带参数
     *
     * @param parts 文件part的数组
     * @param path  需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonArray>> uploadFilesReturnArray(
            @Part() List<MultipartBody.Part> parts
            , @Path("path") String path);



    /**
     * 最基本的获取数据，但是结果是数组类型的
     *
     * @param params 参数
     */
    @FormUrlEncoded
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> post(@FieldMap HashMap<String, Object> params);

    /**
     * 获取数据,服务接收到的一个就送字符串
     *
     * @param object 一个实体类
     */
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> post(@Body Object object);

    /**
     * 获取数据，参数是json
     *
     * @param jsonBody 就送字符串的请求体
     */
    @POST("satinApi")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<BaseEntity<JsonObject>> post(@Body RequestBody jsonBody);

    /**
     * 上传文件，参数是文件的part，不支持文件参数上传
     *
     * @param file 文件part
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> uploadFlie(@Part() MultipartBody.Part file);

    /**
     * 上传多个文件，参数是文件的RequestBody，
     * 支持携带参数
     *
     * @param maps map类型RequestBody
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> uploadFiles(@PartMap() Map<String, RequestBody>
                                                                     maps);

    /**
     * 上传多个文件，参数是文件的part，
     * 支持携带参数
     *
     * @param parts 文件part的数组
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> uploadFiles(@Part() List<MultipartBody.Part>
                                                                     parts);


    /**
     * 最基本的获取数据，但是结果是数组类型的
     *
     * @param params 参数
     * @param path   需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @FormUrlEncoded
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> post(@FieldMap HashMap<String, Object> params
            , @Path("path") String path);

    /**
     * 获取数据,服务接收到的一个就送字符串
     *
     * @param object 一个实体类
     * @param path   需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> post(@Body Object object
            , @Path("path") String path);

    /**
     * 获取数据，参数是json
     *
     * @param jsonBody 就送字符串的请求体
     * @param path     需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @POST("satinApi")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<BaseEntity<JsonObject>> post(@Body RequestBody jsonBody
            , @Path("path") String path);

    /**
     * 上传文件，参数是文件的part，不支持文件参数上传
     *
     * @param file 文件part
     * @param path 需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> uploadFile(@Part() MultipartBody.Part file
            , @Path("path") String path);

    /**
     * 上传多个文件，参数是文件的RequestBody，
     * 支持携带参数
     *
     * @param maps map类型RequestBody
     * @param path 需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> uploadFiles(
            @PartMap() Map<String, RequestBody> maps
            , @Path("path") String path);

    /**
     * 上传多个文件，参数是文件的part，
     * 支持携带参数
     *
     * @param parts 文件part的数组
     * @param path  需要嵌入路径的path，需要自己嵌入，这里只是预留{path}
     */
    @Multipart
    @POST("satinApi")
    Observable<BaseEntity<JsonObject>> uploadFiles(
            @Part() List<MultipartBody.Part> parts
            , @Path("path") String path);
}
