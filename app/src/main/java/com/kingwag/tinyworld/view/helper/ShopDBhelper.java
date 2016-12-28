package com.kingwag.tinyworld.view.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kingwag on 2016/12/27.
 */

/**
 * 购物车数据库
 */

public class ShopDBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shopcart.db";

    //数据库版本
    public static final int DB_VERSION = 1;

    public ShopDBhelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE goods (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,desc TEXT,price DOUBLE,imageUrl TEXT,discountPrice DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
