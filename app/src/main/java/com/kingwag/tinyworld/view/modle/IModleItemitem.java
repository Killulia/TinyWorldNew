package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.ItemItemBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface IModleItemitem {
    Observable<ItemItemBean> getIitemItemBean(String urlString);
}
