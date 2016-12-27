package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.ItemItemBean;
import com.kingwag.tinyworld.view.retrofitApi.TwotTwoRetrofit;
import com.kingwag.tinyworld.view.utils.OkHttp3Utils;
import com.kingwag.tinyworld.view.utils.URLConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class ModleItemItem implements IModleItemitem {
    private TwotTwoRetrofit twotTwoRetrofit=null;
    @Override
    public Observable<ItemItemBean> getIitemItemBean(String urlString) {
        initRetrofit();
        return twotTwoRetrofit.getTwoTwoBean(urlString);
    }

    private void initRetrofit() {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        twotTwoRetrofit = retrofit.create(TwotTwoRetrofit.class);
    }
}
