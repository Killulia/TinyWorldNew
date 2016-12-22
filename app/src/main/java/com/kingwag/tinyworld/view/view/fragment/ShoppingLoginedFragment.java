package com.kingwag.tinyworld.view.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.ShopAdapter;
import com.kingwag.tinyworld.view.bean.GoodsInfo;
import com.kingwag.tinyworld.view.bean.StoreInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwag on 2016/12/20.
 */

public class ShoppingLoginedFragment extends Fragment implements ShopAdapter.CheckInterface,View.OnClickListener{

    private Context mContext;
    private List<StoreInfo> storeInfos;
    private ExpandableListView expandableListView;
    private ShopAdapter adapter;
    private CheckBox allCheck;//全选按钮

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_has_login, container, false);
        mContext = getActivity();
        initData();
        initExpandList(view);
        initView(view);
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView(View view) {
        allCheck = (CheckBox) view.findViewById(R.id.all_chekbox);
        allCheck.setOnClickListener(this);
    }

    private void initExpandList(View view) {
        expandableListView = (ExpandableListView)view. findViewById(R.id.exListView);
        adapter = new ShopAdapter(mContext, storeInfos);
        expandableListView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        adapter.setCheckInterface(this);
    }

    /**
     * 模拟数据
     */
    private void initData() {
        storeInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StoreInfo storeInfo = new StoreInfo(i+"","天猫第"+(i+1)+"号店");
            List<GoodsInfo> goodsInfos = new ArrayList<>();
            for (int j = 0; j < i+1; j++) {
                int[] img = {R.drawable.goods1, R.drawable.goods2, R.drawable.goods3, R.drawable.goods4, R.drawable.goods5, R.drawable.goods6};
                GoodsInfo goodsInfo = new GoodsInfo(j+"","商品","第"+(j+1)+"号商品",(j+2)*2.0,j+1,"红","xm",img[j],(j+1)*1.5);
                goodsInfos.add(goodsInfo);
            }
            storeInfo.setGoodsInfos(goodsInfos);
            storeInfos.add(storeInfo);
        }
    }

    /**
     * 组选框状态
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo storeInfo = storeInfos.get(groupPosition);
        List<GoodsInfo> goodsInfos = storeInfo.getGoodsInfos();
        for (int i = 0; i < goodsInfos.size(); i++) {
            goodsInfos.get(i).setChoosed(isChecked);
        }
        if (isAllCheck()) {
            allCheck.setChecked(true);
        }else {
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 子选框状态
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        GoodsInfo goodsInfo = storeInfos.get(groupPosition).getGoodsInfos().get(childPosition);
        goodsInfo.setChoosed(isChecked);
        if(getGroupState(groupPosition)){
            storeInfos.get(groupPosition).setChoosed(true);
        }else {
            storeInfos.get(groupPosition).setChoosed(false);
        }
        if (isAllCheck()) {
            allCheck.setChecked(true);
        }else {
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 判断一组状态
     *
     *
     */
    private boolean getGroupState(int groupPosition){
        for (int i = 0; i < storeInfos.get(groupPosition).getGoodsInfos().size(); i++) {
            if (storeInfos.get(groupPosition).getGoodsInfos().get(i).isChoosed()==false){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否全选
     */
    private boolean isAllCheck(){
        for (StoreInfo storeInfo:storeInfos ) {
            if (!storeInfo.isChoosed()){
                return false;
            }
        }
        return true;
    }

    /**
     * 全选与反选
     */
    public void doAllCheck(){
        for (int i = 0; i < storeInfos.size(); i++) {
            StoreInfo storeInfo = storeInfos.get(i);
            storeInfo.setChoosed(allCheck.isChecked());
            List<GoodsInfo> goodsInfos = storeInfo.getGoodsInfos();
            for (int j = 0; j < goodsInfos.size(); j++) {
                GoodsInfo goodsInfo = goodsInfos.get(j);
                goodsInfo.setChoosed(allCheck.isChecked());
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_chekbox:
                doAllCheck();
                break;
        }
    }

}
