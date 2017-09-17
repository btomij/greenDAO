package com.example.joelin.greendao001.factory;

import com.example.joelin.greendao001.database.DBService;

/**
 * Created by joelin on 2017/9/16.
 */

//greenDAO step 7 Create a class to generate DBService instance
public class ServiceFactory {
    private static class DBServiceHolder {
        private static final DBService INSTANCE = new DBService();
    }
    public static final DBService getDbService() {
        return DBServiceHolder.INSTANCE;
    }
}
