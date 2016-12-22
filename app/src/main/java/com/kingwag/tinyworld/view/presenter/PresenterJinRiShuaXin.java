package com.kingwag.tinyworld.view.presenter;

import android.util.Log;

import com.kingwag.tinyworld.view.bean.JinRiShuaXinBean;
import com.kingwag.tinyworld.view.modle.ModleJinRiShuaXin;
import com.kingwag.tinyworld.view.view.interfacese.IShuaXinView;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/21.
 */

public class PresenterJinRiShuaXin implements IPresenterJinRiShuaXin {
    private ModleJinRiShuaXin modleJinRiShuaXin;
    private IShuaXinView iShuaXinView;

    public PresenterJinRiShuaXin(IShuaXinView iShuaXinView) {
        this.modleJinRiShuaXin =new ModleJinRiShuaXin();
        this.iShuaXinView = iShuaXinView;
    }
    @Override
    public void LoadAJinRiDate(String path) {

        Log.e("Tag", "path-------------->" + path);
        modleJinRiShuaXin.getJinRiShuaXinBean(path)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Tag", "-------------->  e" + e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody response) {
                        try {
                            String reuslt = response.string();
                            Log.e("Tag", "-------------->  reuslt" + reuslt);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
