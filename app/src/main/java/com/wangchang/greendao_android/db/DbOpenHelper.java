package com.wangchang.greendao_android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import static com.wangchang.greendao_android.db.DaoMaster.dropAllTables;

/**
 * Created by Administrator on 2017/9/5.
 */

public class DbOpenHelper extends DaoMaster.OpenHelper{

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        if (newVersion > oldVersion) {
            // 升级、数据库迁移操作
            MigrationHelper.getInstance().migrate(db, UserDao.class);
        }else {
            // 默认操作
            dropAllTables(db, true);
            onCreate(db);
        }

    }


}
