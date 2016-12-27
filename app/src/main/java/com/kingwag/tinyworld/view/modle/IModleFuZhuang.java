package com.kingwag.tinyworld.view.modle;

import com.kingwag.tinyworld.view.bean.FuzhuangBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface IModleFuZhuang {
    Observable<FuzhuangBean> getFuZhuangBean(String urlString);
}
