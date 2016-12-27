package com.kingwag.tinyworld.view.presenter;

import android.util.Log;

import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.modle.ModleFuZhuang;
import com.kingwag.tinyworld.view.view.interfacese.IFuzhuangView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/22.
 */

public class PresenterFuZhuang implements IPresenterFuZhuang{
    private ModleFuZhuang modleFuZhuang;
    private IFuzhuangView fuzhuangView;
    public PresenterFuZhuang(IFuzhuangView fuzhuangView) {
        this.modleFuZhuang =new ModleFuZhuang();
        this.fuzhuangView = fuzhuangView;
    }
    @Override
    public void LoadFuZhuangData(String urlString) {
        modleFuZhuang.getFuZhuangBean(urlString)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FuzhuangBean>() {
                    @Override
                    public void onCompleted() {
                        fuzhuangView.showProgress();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FuzhuangBean fuzhuangBean) {
                        fuzhuangView.loadData(fuzhuangBean);

                    }
                });

    }
}
