package com.kingwag.tinyworld.view.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MSI on 2016/12/27.
 */

public class DBHelper extends SQLiteOpenHelper {
    //数据库名称
    public static final String DB_NAME = "tinyworld.db";
    //数据库版本
    private static final int DB_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (_id INTEGER PRIMARY KEY AUTOINCREMENT , userName TEXT, password TEXT, introduce TEXT,mobilePhoneNumber TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
