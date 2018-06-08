package com.jack.framework.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jackgu.androidframework.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * @Author: JACK-GU
 * @Date: 2017-08-07
 * @E-Mail: 528489389@qq.com
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //MigrationHelper.migrate(db, GreenTruckDao.class); //这里是需要升级的文件
    }
}
