package com.kingwag.tinyworld.view.presenter;

import android.util.Log;

import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.modle.ModleJinRi;
import com.kingwag.tinyworld.view.view.interfacese.IView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/17.
 */

public class PresenterJinRi implements IPresenterJinRi{
    private ModleJinRi modeJinRi;
    private IView iView;

    public PresenterJinRi(IView iView) {
        this.modeJinRi =new ModleJinRi();
        this.iView = iView;
    }
    @Override
    public void LoadAJinRiDate(String urlString) {
        modeJinRi.getAJinRiBean(urlString)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JinRiBean>() {
                    @Override
                    public void onCompleted() {
                        iView.showProgress();
                        Log.e("tag","成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag","失败");
                    }

                    @Override
                    public void onNext(JinRiBean jinRiBean) {
                        iView.loadData(jinRiBean);

                    }
                });

    }

}
