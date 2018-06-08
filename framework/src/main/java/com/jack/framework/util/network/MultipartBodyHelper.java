package com.jack.framework.util.network;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 用于retrofit上传文件
 *
 * @Author: JACK-GU
 * @Date: 2018/4/9 10:41
 * @E-Mail: 528489389@qq.com
 */
public class MultipartBodyHelper {
    /**
     * 将文件封装成一个可以上传的MultipartBody.Part
     *
     * @param path 文件路径
     * @param key  服务器预定的key
     * @return MultipartBody.Part 上传文件的part,使用(service)：@Part() MultipartBody.Part part
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:41
     * @E-Mail: 528489389@qq.com
     */
    public static MultipartBody.Part transform(String path, String key) {
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }

    /**
     * 将文件封装成一个可以上传的MultipartBody.Part
     *
     * @param file 文件
     * @param key  服务器预定的key
     * @return MultipartBody.Part 上传文件的part,使用(service)：@Part() MultipartBody.Part part
     * @Author: JACK-GU
     * @Date: 2018/4/9 10:41
     * @E-Mail: 528489389@qq.com
     */
    public static MultipartBody.Part transform(File file, String key) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }

    /**
     * 上传文件,多个
     *
     * @param maps ，map的key是这个文件与服务器约定的key，value是文件的地址
     * @return MultipartBody 上传的文件，包含多个Part,使用(service)：@Body MultipartBody multipartBody
     * @Author: JACK-GU
     * @Date: 2018/4/9 11:24
     * @E-Mail: 528489389@qq.com
     */
    public static MultipartBody mapPathToMultipartBody(Map<String, String> maps) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String, String> map : maps.entrySet()) {
            File file = new File(map.getValue());
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    file);
            builder.addFormDataPart(map.getKey(), file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();

        return multipartBody;
    }


    /**
     * 上传文件,多个
     *
     * @param maps ，map的key是这个文件与服务器约定的key，value是文件
     * @return MultipartBody 上传的文件，包含多个Part,使用(service)：@Body MultipartBody multipartBody
     * @Author: JACK-GU
     * @Date: 2018/4/9 11:24
     * @E-Mail: 528489389@qq.com
     */
    public static MultipartBody mapFileToMultipartBody(Map<String, File> maps) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String, File> map : maps.entrySet()) {
            File file = map.getValue();
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    file);
            builder.addFormDataPart(map.getKey(), file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();

        return multipartBody;
    }


    /**
     * 使用MultipartBody传送多个文件，数组方式
     *
     * @param files 文件数组
     * @param key   key与服务器约定的
     * @return MultipartBody 上传的文件，包含多个Part,使用(service)：@Body MultipartBody multipartBody
     * @Author: JACK-GU
     * @Date: 2018/4/9 11:00
     * @E-Mail: 528489389@qq.com
     */
    public static MultipartBody filesToMultipartBody(List<File> files, String key) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();

        return multipartBody;
    }

    /**
     * 使用MultipartBody传送多个文件，数组方式
     *
     * @param paths 文件路径数组
     * @param key   key与服务器约定的
     * @return MultipartBody 上传的文件，包含多个Part,使用(service)：@Body MultipartBody multipartBody
     * @Author: JACK-GU
     * @Date: 2018/4/9 11:00
     * @E-Mail: 528489389@qq.com
     */
    public static MultipartBody pathsToMultipartBody(List<String> paths, String key) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String str : paths) {
            File file = new File(str);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }


        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();

        return multipartBody;
    }

    /**
     * 传送文件数组
     *
     * @param files 文件数组
     * @param key   key与服务器约定的
     * @return List<MultipartBody.Part> 上传的文件，包含多个Part,使用(service)：
     * <p>@Part() List<MultipartBody.Part> parts</p>
     * @Author: JACK-GU
     * @Date: 2018/4/9 11:08
     * @E-Mail: 528489389@qq.com
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files, String key) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            parts.add(transform(file, key));
        }
        return parts;
    }

    /**
     * 传送文件数组
     *
     * @param paths 文件路径数组
     * @param key   key与服务器约定的
     * @return List<MultipartBody.Part> 上传的文件，包含多个Part,使用(service)：
     * <p>@Part() List<MultipartBody.Part> parts</p>
     * @Author: JACK-GU
     * @Date: 2018/4/9 11:08
     * @E-Mail: 528489389@qq.com
     */
    public static List<MultipartBody.Part> pathsToMultipartBodyParts(List<String> paths, String
            key) {
        List<MultipartBody.Part> parts = new ArrayList<>(paths.size());

        for (String str : paths) {
            parts.add(transform(str, key));
        }
        return parts;
    }
}
