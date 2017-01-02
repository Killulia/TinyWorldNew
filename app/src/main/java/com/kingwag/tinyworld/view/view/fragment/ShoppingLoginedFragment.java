package com.kingwag.tinyworld.view.view.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.ShopAdapter;
import com.kingwag.tinyworld.view.bean.Goods;
import com.kingwag.tinyworld.view.bean.Store;
import com.kingwag.tinyworld.view.helper.GoodsManager;
import com.kingwag.tinyworld.view.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwag on 2016/12/20.
 */

/**
 * 购物车账号已登录Fragment
 */
public class ShoppingLoginedFragment extends Fragment implements ShopAdapter.CheckInterface, View.OnClickListener, ShopAdapter.GroupEditorListener, ShopAdapter.ModifyCountInterface {

    private Context mContext;
    private List<Store> stores;
    private GoodsManager manager;//数据库操作类
    private ExpandableListView expandableListView;
    private ShopAdapter adapter;
    private CheckBox allCheck;//全选按钮
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private TextView tvTotalPrice;//总价格
    private TextView tvGoToPay;//总数量
    private View showView;
    private PopupWindow popupWindow;
    private LayoutInflater inflater;
    private TextView popTotal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_has_login, container, false);
        showView = view;
        mContext = getActivity();
        initDataFinal();
        initExpandList(view);
        initView(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        initDataFinal();
        initExpandList(showView);
        initView(showView);
        allCheck.setChecked(false);
        totalPrice = 0;
        totalCount = 0;
        calcilate();
        adapter.notifyDataSetChanged();
        Log.d("ShoppingLoginedFragment", "resume");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        stores.clear();
        totalCount = 0;
        totalPrice = 0;
        Log.d("ShoppingLoginedFragment", "destroy");
    }

    /**
     * 设置购物车产品数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < stores.size(); i++) {
            stores.get(i).setChoosed(allCheck.isChecked());
            Store group = stores.get(i);
            List<Goods> childs = group.getGoodses();
            for (Goods goodsInfo : childs) {

                count += 1;
            }
        }


        tvGoToPay.setText("购物车" + "(" + count + ")");

    }

    /**
     * 初始化视图
     */
    private void initView(View view) {
        allCheck = (CheckBox) view.findViewById(R.id.all_chekbox);
        allCheck.setOnClickListener(this);

        tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        tvGoToPay = (TextView) view.findViewById(R.id.tv_go_to_pay);
        tvGoToPay.setOnClickListener(this);
    }

    /**
     * 初始化折叠列表
     *
     * @param view 父View
     */
    private void initExpandList(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.exListView);
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
     * 初始化数据
     */
    private void initDataFinal() {
        manager = new GoodsManager(mContext);
        stores = new ArrayList<>();
        try {
            List<Goods> goodses = manager.quearyAll(1);
            for (int i = 0; i < goodses.size(); i++) {
                Store store = new Store(i + "", "第" + (i + 1) + "号店铺");
                List<Goods> goodses1 = new ArrayList<>();
                goodses1.add(goodses.get(i));
                store.setGoodses(goodses1);
                stores.add(store);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 组选框状态
     *
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
        } else {
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        calcilate();
    }

    /**
     * 子选框状态
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        Goods goods = stores.get(groupPosition).getGoodses().get(childPosition);
        goods.setChoosed(isChecked);
        if (getGroupState(groupPosition)) {
            stores.get(groupPosition).setChoosed(true);
        } else {
            stores.get(groupPosition).setChoosed(false);
        }
        if (isAllCheck()) {
            allCheck.setChecked(true);
        } else {
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        calcilate();
    }

    /**
     * 判断一组状态
     */
    private boolean getGroupState(int groupPosition) {
        for (int i = 0; i < stores.get(groupPosition).getGoodses().size(); i++) {
            if (stores.get(groupPosition).getGoodses().get(i).isChoosed() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否全选
     */
    private boolean isAllCheck() {
        for (Store store : stores) {
            if (!store.isChoosed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 全选与反选
     */
    public void doAllCheck() {
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
    private void calcilate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            List<Goods> goodsList = store.getGoodses();
            for (int j = 0; j < goodsList.size(); j++) {
                Goods goods = goodsList.get(j);
                if (goods.isChoosed()) {
                    totalCount++;
                    totalPrice += goods.getPrice() * goods.getCount();
                }
            }
        }
        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("结算(" + totalCount + ")");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_chekbox:
                doAllCheck();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount > 0) {
                    showPopupWindow();

                } else {
                    Toast.makeText(mContext, "请至少选择一件商品", Toast.LENGTH_SHORT).show();
                }
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

    /**
     * 删除一件商品
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     */
    @Override
    public void childDelete(int groupPosition, int childPosition) {

        Goods goods = stores.get(groupPosition).getGoodses().get(childPosition);

        stores.get(groupPosition).getGoodses().remove(childPosition);


        try {
            manager.delete(Integer.valueOf(goods.getId()), 1);
            //解决删除错乱
            initDataFinal();
            initExpandList(showView);
            initView(showView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        calcilate();
        adapter.notifyDataSetChanged();


    }


    /**
     * 支付自定义popupwindow
     */
    public void showPopupWindow() {

        inflater = LayoutInflater.from(getActivity());
        View popView = inflater.inflate(R.layout.shopcart_pop_layout, null);
        popTotal = (TextView) popView.findViewById(R.id.tv_pay_total);
        popTotal.setText(tvTotalPrice.getText().toString());
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        popupWindow.showAtLocation(tvGoToPay, Gravity.BOTTOM, 0, 0);
        // 这里检验popWindow里的button是否可以点击
        Button pay = (Button) popView.findViewById(R.id.bt_pop_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                for (int i = 0; i < stores.size(); i++) {
                    if (stores.get(i).isChoosed()) {
                        Goods good = stores.get(i).getGoodses().get(0);
                        stores.get(i).getGoodses().remove(0);
                        try {
                            manager.delete(Integer.valueOf(good.getId()), 1);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                //解决删除错乱
                initDataFinal();
                initExpandList(showView);
                initView(showView);
                calcilate();
                adapter.notifyDataSetChanged();
            }
        });

    }
}
