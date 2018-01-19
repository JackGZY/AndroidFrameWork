package com.jackgu.androidframework.util.db;

import android.database.sqlite.SQLiteDatabase;

import com.jackgu.androidframework.MyApplication;
import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.dao.DaoMaster;
import com.jackgu.androidframework.dao.DaoSession;


/**
 * @Author: JACK-GU
 * @Date: 2017-08-04
 * @E-Mail: 528489389@qq.com
 */
public class GreenDaoUtil {
    private static MySQLiteOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;


    /**
     * 一定注意文件权限
     */
    public static void init() {
        setDatabase();
    }

    /**
     * 设置greenDao
     */
    private static synchronized void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new MySQLiteOpenHelper(new GreenDaoContext(MyApplication
                .getApplication()), AppConfig.DB_NAME, null);
        db = mHelper.getWritableDatabase();
        // mHelper.onUpgrade(db, 1, 5); //里面需要设置需要升级的表
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static synchronized DaoSession getDaoSession() {
        if (mDaoSession == null) {
            init();
        }
        return mDaoSession;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }
}
