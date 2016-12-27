package com.kingwag.tinyworld.view.retrofitApi;

import com.kingwag.tinyworld.view.bean.FuzhuangBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface FuZhuangRetrofitApi {
    @GET
    Observable<FuzhuangBean> getJinRiBean(@Url String urlString);
}
