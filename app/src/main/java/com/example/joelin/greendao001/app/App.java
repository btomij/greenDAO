package com.example.joelin.greendao001.app;

import android.app.Application;

import com.example.joelin.greendao001.Model.DaoMaster;
import com.example.joelin.greendao001.Model.DaoSession;
import com.example.joelin.greendao001.database.DBService;
import com.example.joelin.greendao001.factory.ServiceFactory;

import org.greenrobot.greendao.database.Database;

/**
 * Created by joelin on 2017/9/16.
 */

public class App extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */

    @Override
    public void onCreate() {
        super.onCreate();

        //greenDAO step 8 init DBService
        ServiceFactory.getDbService().init(this);
    }

}
