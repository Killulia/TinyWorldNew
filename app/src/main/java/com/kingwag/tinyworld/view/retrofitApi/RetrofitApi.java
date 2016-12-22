package com.kingwag.tinyworld.view.retrofitApi;


import com.kingwag.tinyworld.view.bean.JinRiBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface RetrofitApi {
    @GET
    Observable<JinRiBean> getJinRiBean(@Url String urlString);

}
