package com.jack.framework.util.network.repository;

import com.jack.framework.util.network.RetrofitHelper;
import com.jack.framework.util.network.service.ApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 传入需要实体，配合ApiService，T为需要获取的实体类，不需要包装BaseEntity,如果返回直接是数组，
 * 请使用带ReturnArray的方法，返回List< T >意思是不需要，泛型传入T
 *
 * @Author: JACK-GU
 * @Date: 2018-07-11 17:11
 * @E-Mail: 528489389@qq.com
 * @see com.jack.framework.util.network.service.ApiService
 */
public class MyJsonRepository<T> extends BaseRepository<T> {

    public MyJsonRepository(Class<T> tClass) {
        this.settClass(tClass);
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param params 参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<List<T>> postReturnArray(HashMap<String, Object> params) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.postReturnArray(params));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param object 参数实体类
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<List<T>> postReturnArray(Object object) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.postReturnArray(object));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param jsonBody 参数请body,如果是传送就送没有key的可以选择这种。raw
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<List<T>> postReturnArray(RequestBody jsonBody) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.postReturnArray(jsonBody));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param file 参数请file，值能穿一个文件，不携带参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<List<T>> uploadFileReturnArray(MultipartBody.Part file) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.uploadFileReturnArray(file));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param maps 参数的map,上传文件，文件数组，多个可以，也可以携带参数,
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<List<T>> uploadFilesReturnArray(Map<String, RequestBody> maps) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.uploadFilesReturnArray(maps));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param parts 参数的part的part，上传文件，文件数组，多个可以，也可以携带参数,
     *              part如果key相同就是数组的元素了
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<List<T>> uploadFilesReturnArray(List<MultipartBody.Part> parts) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.uploadFilesReturnArray(parts));
        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param params 参数
     * @param path   需要嵌入地址的path {path}
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<List<T>> postReturnArray(HashMap<String, Object> params, String path) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.postReturnArray(params, path));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param path   需要嵌入地址的path {path}
     * @param object 参数实体类
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<List<T>> postReturnArray(Object object, String path) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.postReturnArray(object, path));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param path     需要嵌入地址的path {path}
     * @param jsonBody 参数请body,如果是传送就送没有key的可以选择这种。raw
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<List<T>> postReturnArray(RequestBody jsonBody, String path) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.postReturnArray(jsonBody, path));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param path 需要嵌入地址的path {path}
     * @param file 参数请file，值能穿一个文件，不携带参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<List<T>> uploadFilesReturnArray(MultipartBody.Part file, String path) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.uploadFileReturnArray(file, path));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param path 需要嵌入地址的path {path}
     * @param maps 参数的map,上传文件，文件数组，多个可以，也可以携带参数,
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<List<T>> uploadFilesReturnArray(Map<String, RequestBody> maps, String path) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.uploadFilesReturnArray(maps, path));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonArray>
     *
     * @param path  需要嵌入地址的path {path}
     * @param parts 参数的part的part，上传文件，文件数组，多个可以，也可以携带参数,
     *              part如果key相同就是数组的元素了
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<List<T>> uploadFilesReturnArray(List<MultipartBody.Part> parts
            , String path) {
        Observable<List<T>> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonArray(apiService.uploadFilesReturnArray(parts, path));
        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param params 参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> post(HashMap<String, Object> params) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.post(params));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param object 参数实体类
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> post(Object object) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.post(object));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param jsonBody 参数请body,如果是传送就送没有key的可以选择这种。raw
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> post(RequestBody jsonBody) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.post(jsonBody));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param file 参数请file，值能穿一个文件，不携带参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<T> uploadFiles(MultipartBody.Part file) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.uploadFile(file));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param maps 参数的map,上传文件，文件数组，多个可以，也可以携带参数,
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<T> uploadFiles(Map<String, RequestBody> maps) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.uploadFiles(maps));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param parts 参数的part的part，上传文件，文件数组，多个可以，也可以携带参数,
     *              part如果key相同就是数组的元素了
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<T> uploadFiles(List<MultipartBody.Part> parts) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.uploadFiles(parts));
        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param params 参数
     * @param path   需要嵌入地址的path {path}
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> post(HashMap<String, Object> params, String path) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.post(params, path));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param path   需要嵌入地址的path {path}
     * @param object 参数实体类
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> post(Object object, String path) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.post(object, path));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param path     需要嵌入地址的path {path}
     * @param jsonBody 参数请body,如果是传送就送没有key的可以选择这种。raw
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public Observable<T> post(RequestBody jsonBody, String path) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.post(jsonBody, path));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param path 需要嵌入地址的path {path}
     * @param file 参数请file，值能穿一个文件，不携带参数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<T> uploadFile(MultipartBody.Part file, String path) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.uploadFile(file, path));

        return rObservable;
    }


    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param path 需要嵌入地址的path {path}
     * @param maps 参数的map,上传文件，文件数组，多个可以，也可以携带参数,
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<T> uploadFiles(Map<String, RequestBody> maps, String path) {
        Observable<T> rObservable = null;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.uploadFiles(maps, path));
        return rObservable;
    }

    /**
     * 开始请求,Service返回必须是BaseEntity<JsonObject>
     *
     * @param path  需要嵌入地址的path {path}
     * @param parts 参数的part的part，上传文件，文件数组，多个可以，也可以携带参数,
     *              part如果key相同就是数组的元素了
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     * @see com.jack.framework.util.network.MultipartBodyHelper
     */
    public Observable<T> uploadFiles(List<MultipartBody.Part> parts
            , String path) {
        Observable<T> rObservable;
        ApiService apiService = RetrofitHelper.getInstance().createService(ApiService.class);
        rObservable = transformResultJsonObject(apiService.uploadFiles(parts, path));
        return rObservable;
    }
}
