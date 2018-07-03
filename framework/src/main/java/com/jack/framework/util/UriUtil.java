package com.jack.framework.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.jack.framework.FrameWorkApplication;

import java.io.File;

/**
 * 获取url
 *
 * @Author: JACK-GU
 * @Date: 2018-07-03 09:18
 * @E-Mail: 528489389@qq.com
 */
public class UriUtil {
    /**
     * 通过file获得uri，适配了版本的,前提是适配了8.0
     *
     * @param file 文件
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static Uri getUriFromFile(File file) {
        Uri uri = null;
        if (file == null) {
            return uri;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            String s = FrameWorkApplication.getApplication().getMainModuleID() + ".fileprovider";
            uri = FileProvider.getUriForFile(FrameWorkApplication.getApplication(), s, file);
        }

        return uri;
    }

    /**
     * 通过file获得uri，适配了版本的,前提是适配了8.0
     *
     * @param file   文件
     * @param intent 意图，如果不为null，会自动适配，加上权限
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static Uri getUriFromFile(File file, Intent intent) {
        if (intent != null) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        return getUriFromFile(file);
    }
}
