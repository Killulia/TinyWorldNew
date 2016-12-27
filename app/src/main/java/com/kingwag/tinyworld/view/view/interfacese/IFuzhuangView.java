package com.kingwag.tinyworld.view.view.interfacese;

import com.kingwag.tinyworld.view.bean.FuzhuangBean;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface IFuzhuangView {
    //hide
    void showProgress();
    //加载失败
    void showLoadFailMsg();
    //加载成功

    void loadData(FuzhuangBean fuzhuangBean);
}
