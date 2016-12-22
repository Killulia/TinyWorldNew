package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.JinRiShuaXinBean;
import com.kingwag.tinyworld.view.retrofitApi.RetrofitShuaApi;
import com.kingwag.tinyworld.view.utils.OkHttp3Utils;
import com.kingwag.tinyworld.view.utils.URLConstant;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public class ModleJinRiShuaXin implements IModeJinRiShuaXin {

    private RetrofitShuaApi retrofitShuaApi=null;
    @Override
    public Observable<ResponseBody> getJinRiShuaXinBean(String path) {
        initRetrofit();
        return retrofitShuaApi.getJinRiShuaXinBean(path);
    }
    private void initRetrofit() {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitShuaApi = retrofit.create(RetrofitShuaApi.class);
    }
}
