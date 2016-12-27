package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.retrofitApi.FuZhuangRetrofitApi;
import com.kingwag.tinyworld.view.utils.OkHttp3Utils;
import com.kingwag.tinyworld.view.utils.URLConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */

public class ModleFuZhuang implements IModleFuZhuang{
    private FuZhuangRetrofitApi fuZhuangRetrofitApi=null;
    @Override
    public Observable<FuzhuangBean> getFuZhuangBean(String urlString) {
        fuZhuangRetrofitApi();
        return fuZhuangRetrofitApi.getJinRiBean(urlString);
    }
    private void fuZhuangRetrofitApi() {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fuZhuangRetrofitApi = retrofit.create(FuZhuangRetrofitApi.class);
    }
}
