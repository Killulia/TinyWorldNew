package com.kingwag.tinyworld.view.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.kingwag.tinyworld.view.bean.Goods;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwag on 2016/12/27.
 * 购物车数据库操作类
 */

public class GoodsManager {

    //表名常量
    public static final String TABLE_NAME = "goods";
    public static final String TABLE_NAME_COLLECT = "collect";
    public static final String TABLE_NAME_RECORD= "record";
    //字段名常量
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMGURL= "imageUrl";
    public static final String KEY_PRICE= "price";
    public static final String KEY_DISCOUNT_PRICE= "discountPrice";
    public static final String KEY_STYLE= "style";


    private ShopDBhelper dBhelper;

    public GoodsManager(Context context) {
        dBhelper = new ShopDBhelper(context);
    }

    /**
     * 添加数据到购物车
     * @param goods
     * @throws Exception
     */
    public boolean insert(Goods goods,int type)throws Exception{
        //打开数据库
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        //插入一条数据
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,goods.getName());
        cv.put(KEY_PRICE,goods.getPrice());
        cv.put(KEY_DISCOUNT_PRICE,goods.getDiscountPrice());
        cv.put(KEY_IMGURL,goods.getImageUrl());
        cv.put(KEY_STYLE,goods.getStyle());
        long result;
        //购物车的表
        if (type==1){
            result = db.insert(TABLE_NAME, null, cv);
        }else if (type == 2){
            result = db.insert(TABLE_NAME_COLLECT, null, cv);
        }else {
            result = db.insert(TABLE_NAME_RECORD, null, cv);
        }

        //关闭数据库
        db.close();
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 从购物车删除一条数据
     * @param id
     */
    public void delete(int id,int type)throws Exception{
        //打开数据库
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        //删除数据
        if (type==1){
            db.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(id)});
        }else if (type==2){
            db.delete(TABLE_NAME_COLLECT, "_id = ?", new String[]{String.valueOf(id)});
        }else {
            db.delete(TABLE_NAME_RECORD, "_id = ?", new String[]{String.valueOf(id)});
        }

        //关闭数据库
        db.close();
    }

    /**
     * 查询购物车内所有数据
     * @return
     */
    public List<Goods> quearyAll(int type)throws Exception{
        List<Goods> goodsList = new ArrayList<>();
        //打开数据库
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        //查询数据
        Cursor cursor;
        if (type==1){
            cursor = db.query(
                    TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }else if (type==2){
            cursor = db.query(
                    TABLE_NAME_COLLECT,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }else {
            cursor = db.query(
                    TABLE_NAME_RECORD,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

        int idIndex = cursor.getColumnIndex(KEY_ID);
        int nameIndex = cursor.getColumnIndex(KEY_NAME);
        int imgUrlIndex = cursor.getColumnIndex(KEY_IMGURL);
        int priceIndex = cursor.getColumnIndex(KEY_PRICE);
        int discountIndex = cursor.getColumnIndex(KEY_DISCOUNT_PRICE);
        int styleIndex = cursor.getColumnIndex(KEY_STYLE);
        while (cursor.moveToNext()){
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            double price = cursor.getDouble(priceIndex);
            String imgUrl = cursor.getString(imgUrlIndex);
            double discount = cursor.getDouble(discountIndex);
            String style = cursor.getString(styleIndex);
            Goods goods = new Goods();
            goods.setId(String.valueOf(id));
            goods.setName(name);
            goods.setImageUrl(imgUrl);
            goods.setPrice(price);
            goods.setDiscountPrice(discount);
            goods.setStyle(style);
            goodsList.add(goods);
            //关闭游标


        }
        cursor.close();
        //关闭数据库
        db.close();
        return goodsList;


    }

    public void dropTable()throws Exception{
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        db.execSQL("drop table record");
    }


}
