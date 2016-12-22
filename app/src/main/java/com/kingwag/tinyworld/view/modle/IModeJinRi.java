package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.JinRiBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/17.
 */

public interface IModeJinRi {
    Observable<JinRiBean> getAJinRiBean(String urlString);
}
