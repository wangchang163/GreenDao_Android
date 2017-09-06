package com.wangchang.greendao_android;

import android.app.Application;

import com.amitshekhar.DebugDB;
import com.wangchang.greendao_android.db.AppDbHelper;
import com.wangchang.greendao_android.db.DbOpenHelper;
import com.wangchang.greendao_android.utils.Config;

/**
 * Created by Administrator on 2017/9/6.
 */

public class MyApplication extends Application {

    public static AppDbHelper appDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        DebugDB.getAddressLog();
        appDbHelper = new AppDbHelper(new DbOpenHelper(this, Config.DB_NAME));
    }
}
