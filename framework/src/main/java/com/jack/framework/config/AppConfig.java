package com.jack.framework.config;

import android.Manifest;
import android.os.Environment;


/**
 * @Author: JACK-GU
 * @Date: 2018/1/11
 * @E-Mail: 528489389@qq.com
 * <p>
 * 配置类
 */

public interface AppConfig {
    //配置网络请求的url
    String BASE_URL = "https://www.apiopen.top/";
    //本地数据得文件名称
    String SHAREDPREFERNCES_NAME = "AndroidFramework";
    //需要的权限,注意配置文件需要权限，不然不会弹出来
    String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION};
    //数据库名字
    String DB_NAME = "AndroidFramework.db";

    //文件夹的名字
    String FILE_NAME = "AndroidFramework";
    //文件的路径基础
    String BASE_FILE = Environment.getExternalStorageDirectory()
            .getPath() + "/" + FILE_NAME;
    String DATA_BASE_FILE = BASE_FILE + "/" + "DB/";


    //数据获取成功的code
    int DATA_SUCCESS_CODE = 200;
}
