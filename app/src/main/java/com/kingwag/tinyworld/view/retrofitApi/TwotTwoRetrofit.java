package com.kingwag.tinyworld.view.retrofitApi;

import com.kingwag.tinyworld.view.bean.ItemItemBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface TwotTwoRetrofit {
    @GET
    Observable<ItemItemBean> getTwoTwoBean(@Url String urlString);
}
