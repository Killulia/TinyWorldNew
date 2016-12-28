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
    //字段名常量
    public static final String KEY_ID = "_id";
    //public static final String KEY_CHOOSED = "isChoosed";
    public static final String KEY_NAME = "name";
    //public static final String KEY_IMGURL= "imageUrl";
    public static final String KEY_DESC= "desc";
    public static final String KEY_PRICE= "price";
    public static final String KEY_COUNT= "count";
    //public static final String KEY_POSITION= "position";
    public static final String KEY_COLOR= "color";
    public static final String KEY_SIZE= "size";
    public static final String KEY_GOODSI_MG= "goodsImg";
    public static final String KEY_DISCOUNT_PRICE= "discountPrice";

    private ShopDBhelper dBhelper;

    public GoodsManager(Context context) {
        dBhelper = new ShopDBhelper(context);
    }

    /**
     * 添加数据到购物车
     * @param goods
     * @throws Exception
     */
    public void insert(Goods goods)throws Exception{
        //打开数据库
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        //插入一条数据
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,goods.getName());
       // cv.put(KEY_CHOOSED,String.valueOf(goods.isChoosed()));
      //  cv.put(KEY_IMGURL,goods.getImageUrl());
        cv.put(KEY_DESC,goods.getDesc());
        cv.put(KEY_PRICE,goods.getPrice());
       // cv.put(KEY_COUNT,goods.getCount());
        //cv.put(KEY_POSITION,goods.getPosition());
        cv.put(KEY_COLOR,goods.getColor());
        cv.put(KEY_SIZE,goods.getSize());
        cv.put(KEY_GOODSI_MG,goods.getGoodsImg());
        cv.put(KEY_DISCOUNT_PRICE,goods.getDiscountPrice());
        db.insert(TABLE_NAME, null, cv);
        //关闭数据库
        db.close();
    }

    /**
     * 从购物车删除一条数据
     * @param id
     */
    public void delete(int id)throws Exception{
        //打开数据库
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        //删除数据
        db.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(id)});
        //关闭数据库
        db.close();
    }

    /**
     * 查询购物车内所有数据
     * @return
     */
    public List<Goods> quearyAll()throws Exception{
        List<Goods> goodsList = new ArrayList<>();
        //打开数据库
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        //查询数据
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        int idIndex = cursor.getColumnIndex(KEY_ID);
       // int chooseIndex = cursor.getColumnIndex(KEY_CHOOSED);
        int nameIndex = cursor.getColumnIndex(KEY_NAME);
       // int imgUrlIndex = cursor.getColumnIndex(KEY_IMGURL);
        int descIndex = cursor.getColumnIndex(KEY_DESC);
        int priceIndex = cursor.getColumnIndex(KEY_PRICE);
        int countIndex = cursor.getColumnIndex(KEY_COUNT);
       // int positionIndex = cursor.getColumnIndex(KEY_POSITION);
        int colorIndex = cursor.getColumnIndex(KEY_COLOR);
        int sizeIndex = cursor.getColumnIndex(KEY_SIZE);
        int goodsImgIndex = cursor.getColumnIndex(KEY_GOODSI_MG);
        int discountIndex = cursor.getColumnIndex(KEY_DISCOUNT_PRICE);
        while (cursor.moveToNext()){
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
           // boolean isChoosed = Boolean.valueOf(cursor.getString(chooseIndex));
           // String imgUrl = cursor.getString(imgUrlIndex);
            String desc = cursor.getString(descIndex);
            double price = cursor.getDouble(priceIndex);
            int count = cursor.getInt(countIndex);
            //int position = cursor.getInt(positionIndex);
            String color = cursor.getString(colorIndex);
            String size = cursor.getString(sizeIndex);
            int goodsImg = cursor.getInt(goodsImgIndex);
            double discount = cursor.getDouble(discountIndex);
            Goods goods = new Goods(String.valueOf(id),name,desc,price,count,color,size,goodsImg,discount);
            goodsList.add(goods);
            //关闭游标


        }
        cursor.close();
        //关闭数据库
        db.close();
        return goodsList;


    }


}
