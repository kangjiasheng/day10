package com.example.db;

import android.app.Application;

import com.example.greendaodemo.db.DaoMaster;
import com.example.greendaodemo.db.DaoSession;

public class MyApp extends Application {
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "news_db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
