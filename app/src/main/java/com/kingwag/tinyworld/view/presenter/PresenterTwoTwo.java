package com.kingwag.tinyworld.view.presenter;

import com.kingwag.tinyworld.view.bean.ItemItemBean;
import com.kingwag.tinyworld.view.modle.ModleItemItem;
import com.kingwag.tinyworld.view.view.interfacese.IItemItemView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/26.
 */

public class PresenterTwoTwo implements IPresenterTwoTwo {
    private ModleItemItem modleItemItem;
    private IItemItemView iItemItemView;
    public PresenterTwoTwo(IItemItemView iItemItemView) {
        this.modleItemItem = new ModleItemItem();
        this.iItemItemView = iItemItemView;
    }
    @Override
    public void LoadTwoTwoData(String urlString) {
        modleItemItem.getIitemItemBean(urlString)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ItemItemBean>() {
                    @Override
                    public void onCompleted() {
                        iItemItemView.showProgress();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ItemItemBean itemItemBean) {
                        iItemItemView.loadData(itemItemBean);

                    }
                });


    }
}
