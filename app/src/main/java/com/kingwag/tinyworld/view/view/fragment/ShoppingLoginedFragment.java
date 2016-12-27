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
import android.widget.TextView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.ShopAdapter;
import com.kingwag.tinyworld.view.bean.Goods;
import com.kingwag.tinyworld.view.bean.Store;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwag on 2016/12/20.
 */

/**
 * 购物车账号已登录Fragment
 */
public class ShoppingLoginedFragment extends Fragment implements ShopAdapter.CheckInterface,View.OnClickListener,ShopAdapter.GroupEditorListener,ShopAdapter.ModifyCountInterface{

    private Context mContext;
    private List<Store> stores;
    private ExpandableListView expandableListView;
    private ShopAdapter adapter;
    private CheckBox allCheck;//全选按钮
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private TextView tvTotalPrice;//总价格
    private TextView tvGoToPay;//总数量

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
        tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        tvGoToPay = (TextView) view.findViewById(R.id.tv_go_to_pay);
    }

    private void initExpandList(View view) {
        expandableListView = (ExpandableListView)view. findViewById(R.id.exListView);
        adapter = new ShopAdapter(mContext, stores);
        adapter.setmListener(this);
        adapter.setModifyCountInterface(this);// 设置数量增减接口
        adapter.setCheckInterface(this);
        expandableListView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        View emptyView = view.findViewById(R.id.layout_cart_empty);
        expandableListView.setEmptyView(emptyView);
    }

    /**
     * 模拟数据
     */
    private void initData() {
        stores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Store store = new Store(i+"","第"+(i+1)+"号店");
            List<Goods> goodses = new ArrayList<>();
            for (int j = 0; j < i+1; j++) {
                int[] img = {R.drawable.goods1, R.drawable.goods2, R.drawable.goods3, R.drawable.goods4, R.drawable.goods5, R.drawable.goods6};
                Goods goods = new Goods(j+"","商品","第"+(j+1)+"号商品",(j+2)*2.0,j+1,"红","xm",img[j],(j+1)*1.5);
                goodses.add(goods);
            }
            store.setGoodses(goodses);
            stores.add(store);
        }
    }

    /**
     * 组选框状态
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        Store store = stores.get(groupPosition);
        List<Goods> goodses = store.getGoodses();
        for (int i = 0; i < goodses.size(); i++) {
            goodses.get(i).setChoosed(isChecked);
        }
        if (isAllCheck()) {
            allCheck.setChecked(true);
        }else {
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        calcilate();
    }

    /**
     * 子选框状态
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        Goods goods = stores.get(groupPosition).getGoodses().get(childPosition);
        goods.setChoosed(isChecked);
        if(getGroupState(groupPosition)){
            stores.get(groupPosition).setChoosed(true);
        }else {
            stores.get(groupPosition).setChoosed(false);
        }
        if (isAllCheck()) {
            allCheck.setChecked(true);
        }else {
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        calcilate();
    }

    /**
     * 判断一组状态
     *
     *
     */
    private boolean getGroupState(int groupPosition){
        for (int i = 0; i < stores.get(groupPosition).getGoodses().size(); i++) {
            if (stores.get(groupPosition).getGoodses().get(i).isChoosed()==false){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否全选
     */
    private boolean isAllCheck(){
        for (Store store : stores) {
            if (!store.isChoosed()){
                return false;
            }
        }
        return true;
    }

    /**
     * 全选与反选
     */
    public void doAllCheck(){
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            store.setChoosed(allCheck.isChecked());
            List<Goods> goodses = store.getGoodses();
            for (int j = 0; j < goodses.size(); j++) {
                Goods goods = goodses.get(j);
                goods.setChoosed(allCheck.isChecked());
            }
        }
        adapter.notifyDataSetChanged();
        calcilate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calcilate(){
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            List<Goods> goodsList = store.getGoodses();
            for (int j = 0; j < goodsList.size(); j++) {
                Goods goods = goodsList.get(j);
                if (goods.isChoosed()){
                    totalCount ++;
                    totalPrice += goods.getPrice()* goods.getCount();
                }
            }
        }
        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("结算(" + totalCount + ")");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_chekbox:
                doAllCheck();
                break;
        }
    }

    @Override
    public void groupEdit(int groupPosition) {
        stores.get(groupPosition).setEdtor(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        Goods goods = (Goods) adapter.getChild(groupPosition, childPosition);
        int currentCount = goods.getCount();
        currentCount++;
        goods.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        adapter.notifyDataSetChanged();
        calcilate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        Goods goods = (Goods) adapter.getChild(groupPosition, childPosition);
        int currentCount = goods.getCount();
        if (currentCount == 1)
            return;
        currentCount--;
        goods.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        adapter.notifyDataSetChanged();
        calcilate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        stores.get(groupPosition).getGoodses().remove(childPosition);
        Store store = stores.get(groupPosition);
        List<Goods> goodsList = store.getGoodses();
        if (goodsList.size()==0){
            stores.remove(groupPosition);
        }
        adapter.notifyDataSetChanged();
        calcilate();
    }
}
