package com.jack.framework.util.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.jack.framework.config.AppConfig;

import java.io.File;


/**
 * 该类主要用于基于GreenDao框架自定义数据库路径
 *
 * @Author: JACK-GU
 * @E-Mail: 528489389@qq.com
 */
public class GreenDaoContext extends ContextWrapper {

    private String currentUserId;
    private Context mContext;

    public GreenDaoContext(Context context) {
        super(context);
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        return new File(AppConfig.DATA_BASE_FILE + dbName);
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     * SQLiteDatabase.CursorFactory,
     * DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase
            .CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

}
