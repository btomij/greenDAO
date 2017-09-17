package com.example.joelin.greendao001.database;

import android.content.Context;
import android.util.Log;

import com.example.joelin.greendao001.Model.DaoMaster;
import com.example.joelin.greendao001.Model.DaoSession;
import com.example.joelin.greendao001.Model.NoteDao;

import org.greenrobot.greendao.database.Database;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

/**
 * Created by joelin on 2017/9/16.
 */

//greenDAO step 6 建立一個DBService or DBHelper(建議使用，DB操作要singleton才安全)
public class DBService {
    private static final String DB_NAME = "database.db";
    private static final String DB_Pass = "1";
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    public void init(Context context) {
        try {

            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
            Database db = ENCRYPTED ? helper.getEncryptedWritableDb(DB_Pass) : helper.getWritableDb();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }catch (Exception ex){
            Log.d(TAG, "init: " + ex.getMessage());
        }
    }

    public NoteDao getNoteDao(){
        return daoSession.getNoteDao();
    }
}
