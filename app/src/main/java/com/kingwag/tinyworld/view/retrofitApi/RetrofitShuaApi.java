package com.kingwag.tinyworld.view.retrofitApi;

import com.kingwag.tinyworld.view.bean.JinRiShuaXinBean;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface RetrofitShuaApi {

    @GET("{Path}")
    Observable<ResponseBody> getJinRiShuaXinBean(
            @Path("Path") String path);
}
