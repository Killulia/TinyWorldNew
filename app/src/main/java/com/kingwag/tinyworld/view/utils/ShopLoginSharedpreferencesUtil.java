package com.kingwag.tinyworld.view.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kingwag.tinyworld.view.constants.ShopConstants;

/**
 * Created by kingwag on 2016/12/14.
 */
//判断购物车账号登陆状态
public class ShopLoginSharedpreferencesUtil {

    //获得SharedPreferences实例
    public static SharedPreferences getPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //存登陆状态
    public static void putLoginState(Context context,boolean state){
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ShopConstants.SHOP_LOGIN_KEY, state);
        editor.commit();
    }

    //取登陆状态
    public static boolean getLoginState(Context context){
        SharedPreferences preferences = getPreferences(context);
        boolean states = preferences.getBoolean(ShopConstants.SHOP_LOGIN_KEY, false);
        return states;
    }
}
