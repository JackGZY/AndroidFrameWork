package com.jack.framework.util;

import android.text.TextUtils;

import com.jack.framework.util.network.RetrofitDownloadHelper;
import com.jack.framework.util.network.service.DownLoadFileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 下载文件的工具类
 */
public class DownLoadFileUtil {

    public static void downLoadFile(String url, String filePath, FileDownLoadCallBack
            fileDownLoadCallBack) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }

        //如果没有，就用url的
        String strs[] = url.split("/");
        String name = filePath + "/" + strs[strs.length - 1];

        RetrofitDownloadHelper retrofitDownloadHelper = new RetrofitDownloadHelper((
                bytesRead, contentLength, done) -> {
            if (fileDownLoadCallBack != null) {
                fileDownLoadCallBack.callBack(bytesRead, contentLength, done);
            }
        });

        retrofitDownloadHelper.getsRetrofit().create(DownLoadFileService.class).downloadFile
                (url).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody>
                    response) {
                if (response.code() == 200) {
                    dowLoadFile(fileDownLoadCallBack, response.body(), name);
                } else {
                    //失败
                    if (fileDownLoadCallBack != null)
                        fileDownLoadCallBack.callBack("下载失败,错误码：" + response.code(), false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (fileDownLoadCallBack != null)
                    fileDownLoadCallBack.callBack(t.getMessage(), false);
            }
        });
    }

    //成功的处理
    private static void dowLoadFile(FileDownLoadCallBack fileDownLoadCallBack, ResponseBody
            responseBody, String name) {
        Observable<String> stringObservable = Observable.create(subscriber -> {
            if (writeResponseBodyToDisk(responseBody, name)) {
                //成功
                subscriber.onComplete();
            } else {
                //失败
                subscriber.onError(new NullPointerException("写入文件失败！"));
            }
        });
        stringObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {
                if (fileDownLoadCallBack != null)
                    fileDownLoadCallBack.callBack(e.getMessage(), false);
            }

            @Override
            public void onComplete() {
                if (fileDownLoadCallBack != null)
                    fileDownLoadCallBack.callBack("下载完成！", true);
            }
        });
    }

    /**
     * 写到磁盘
     */
    private static boolean writeResponseBodyToDisk(ResponseBody body, String name) {
        boolean flag = false;
        try {
            File futureStudioIconFile = new File(name);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                }

                outputStream.flush();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
                return flag;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public interface FileDownLoadCallBack {
        void callBack(String msg, boolean success);
        void callBack(long bytesRead, long contentLength, boolean done);
    }
}
