package com.jack.framework.util.network.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 下载文件的接口
 */
public interface DownLoadFileService {
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
