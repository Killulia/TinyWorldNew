package com.kingwag.tinyworld.view.view.interfacese;

import com.kingwag.tinyworld.view.bean.JinRiBean;

/**
 * Created by Administrator on 2016/12/17.
 */

public interface IView {
    //hide
    void showProgress();
    //加载失败
    void showLoadFailMsg();
    //加载成功

    void loadData(JinRiBean ajinRiBean);
}
