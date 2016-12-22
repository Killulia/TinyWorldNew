package com.kingwag.tinyworld.view.view.interfacese;

import com.kingwag.tinyworld.view.bean.JinRiShuaXinBean;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface IShuaXinView {
    //hide
    void showProgress();
    //加载失败
    void showLoadFailMsg();
    //加载成功

    void loadData(int page);
}
