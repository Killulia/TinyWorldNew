package com.kingwag.tinyworld.view.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kingwag.tinyworld.view.bean.MyUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 2016/12/27.
 */

public class UserManager {
    //表名常量
    public static final String TABLE_NAME = "user";
    //字段名常量
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "userName";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_INTRODUCE = "introduce";
    public static final String KEY_MOBILEPHONENUMBER = "mobilePhoneNumber";

    private DBHelper dbHelper;
    public UserManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * 插入一条数据
     * @param user
     * @throws Exception
     */
    public void insert (MyUser user)throws Exception {
        //创建或打开数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,user.getUserName());
        cv.put(KEY_PASSWORD,user.getPassword());
        cv.put(KEY_INTRODUCE,user.getIntroduce());
        cv.put(KEY_MOBILEPHONENUMBER,user.getMobilePhoneNumber());

        db.insert(TABLE_NAME, null, cv);

        db.close();
    }


    //修改数据
    public void update(int id,MyUser newUser) {
        //打开数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //执行更新操作
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,newUser.getUserName());
        cv.put(KEY_PASSWORD,newUser.getPassword());
        cv.put(KEY_INTRODUCE,newUser.getIntroduce());
        db.update(TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(id)});

        //关闭数据库
        db.close();
    }

    //查询
    public List<MyUser> queryAll() {
        List<MyUser> users = new ArrayList<>();
        //打开数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //执行查询操作
        Cursor cursor = db.query(
                TABLE_NAME,//表名
                null,
                null,
                null,
                null,
                null,
                null,
                null

        );
        //根据列的名字，获取列的下标
        int idIndex = cursor.getColumnIndex(KEY_ID);
        int nameIndex = cursor.getColumnIndex(KEY_NAME);
        int passwordIndex = cursor.getColumnIndex(KEY_PASSWORD);
        int phoneNumberIndex = cursor.getColumnIndex(KEY_MOBILEPHONENUMBER);
        int introduceIndex = cursor.getColumnIndex(KEY_INTRODUCE);

        //循环从Cursor中取数据
        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String password = cursor.getString(passwordIndex);
            String phoneNumber = cursor.getString(phoneNumberIndex);
            String introduce = cursor.getString(introduceIndex);
            MyUser user = new MyUser();
            user.set_id(id);
            user.setUserName(name);
            user.setMobilePhoneNumber(phoneNumber);
            user.setPassword(password);
            user.setIntroduce(introduce);
            users.add(user);
        }
        //关闭游标
        cursor.close();
        //关闭数据库
        db.close();
        return users;
    }
}
