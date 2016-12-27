package com.kingwag.tinyworld.view.view.interfacese;

import com.kingwag.tinyworld.view.bean.ItemItemBean;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface IItemItemView {
    //hide
    void showProgress();
    //加载失败
    void showLoadFailMsg();
    //加载成功

    void loadData(ItemItemBean itemItemBean);
}
