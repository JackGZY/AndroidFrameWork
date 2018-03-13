package com.jackgu.androidframework.util.compress;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.jackgu.androidframework.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 图片压缩
 *
 * @Author:JACK-GU
 * @Date:2018/2/27 10:48
 * @E-Mail:528489389@qq.com
 */

public class CompressUtil {
    /**
     * 图片压缩，压缩完成后，图片会保存在同级目录中
     *
     * @param path            路径
     * @param isDeleteOldFile 是否删除原来的照片
     * @Author:JACK-GU
     * @Date:2018/2/27 10:52
     * @E-Mail:528489389@qq.com
     */
    public static Observable<String> compress(@NonNull String path, boolean isDeleteOldFile) {

        Observable<String> stringObservable = Observable.create(subscriber -> {
            File file = new File(path);
            if (!file.exists()) {
                subscriber.onError(new FileNotFoundException());

            } else {
                //文件存在，开始压缩
                //第一步，获取文件的根路径
                try {
                    String directoryPath = path.substring(0, path.lastIndexOf(File.separator));
                    String newName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
                    File compressedImage = new Compressor(MyApplication.getApplication())
                            .setQuality(50).setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .setDestinationDirectoryPath(directoryPath).compressToFile(file,
                                    newName);
                    //删除源文件
                    if (isDeleteOldFile && file != null && file.exists()) {
                        file.delete();
                    }

                    subscriber.onNext(compressedImage.getAbsolutePath());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                }
            }
        });

        stringObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        return stringObservable;
    }
}
