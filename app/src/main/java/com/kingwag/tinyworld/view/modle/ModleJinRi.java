package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.retrofitApi.RetrofitApi;
import com.kingwag.tinyworld.view.utils.OkHttp3Utils;
import com.kingwag.tinyworld.view.utils.URLConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/17.
 */

public class ModleJinRi implements IModeJinRi{

    private RetrofitApi retrofitapi=null;
    @Override
    public Observable<JinRiBean> getAJinRiBean(String urlString) {
        initRetrofit();
        return retrofitapi.getJinRiBean(urlString);
    }

    private void initRetrofit() {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitapi = retrofit.create(RetrofitApi.class);
    }
}
