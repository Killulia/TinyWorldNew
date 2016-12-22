package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.GoodsInfo;
import com.kingwag.tinyworld.view.bean.StoreInfo;

import java.util.List;

/**
 * Created by kingwag on 2016/12/21.
 */

public class ShopAdapter extends BaseExpandableListAdapter {

    private List<StoreInfo> storeInfos;
    private Context context;
    private LayoutInflater inflater;
    private CheckInterface checkInterface;
    public ShopAdapter(Context context, List<StoreInfo> storeInfos) {
        this.context = context;
        this.storeInfos = storeInfos;
        inflater = LayoutInflater.from(context);
    }

    public void setCheckInterface(CheckInterface checkInterface){
        this.checkInterface = checkInterface;
    }

    @Override
    public int getGroupCount() {
        return storeInfos.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return storeInfos.get(position).getGoodsInfos().size();
    }

    @Override
    public Object getGroup(int position) {
        return storeInfos.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return storeInfos.get(groupPosition).getGoodsInfos().get(childPosition);
    }

    @Override
    public long getGroupId(int groupID) {
        return 0;
    }

    @Override
    public long getChildId(int groupID, int childID) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder groupHolder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_shopcart_group, viewGroup, false);
            groupHolder = new GroupHolder(view);
            view.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) view.getTag();
        }
        final StoreInfo storeInfo = storeInfos.get(groupPosition);
        groupHolder.groupName.setText(storeInfo.getName());
        groupHolder.groupCheck.setChecked(storeInfo.isChoosed());
        groupHolder.groupCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeInfo.setChoosed(!storeInfo.isChoosed());
                checkInterface.checkGroup(groupPosition,((CheckBox)view).isChecked());
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder childHolder = null;
        if (view == null){
            view = inflater.inflate(R.layout.item_shopcart_product, viewGroup, false);
            childHolder = new ChildHolder(view);
            view.setTag(childHolder);
        }else {
            childHolder = (ChildHolder) view.getTag();
        }
        final GoodsInfo goodsInfo = storeInfos.get(groupPosition).getGoodsInfos().get(childPosition);
        if (goodsInfo != null) {
            childHolder.goosIcon.setImageResource(goodsInfo.getGoodsImg());
            childHolder.descripTxt.setText(goodsInfo.getDesc());
            childHolder.styleTxt.setText("颜色：" + goodsInfo.getColor() + "," + "尺码：" + goodsInfo.getSize() + "瓶/斤");
            childHolder.priceTxt.setText("￥"+ String.valueOf(goodsInfo.getPrice()));
            childHolder.discountTxt.setText("￥"+ String.valueOf(goodsInfo.getDiscountPrice()));
            childHolder.numTxt.setText("x"+goodsInfo.getCount());
            childHolder.childCheck.setChecked(goodsInfo.isChoosed());
            childHolder.childCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goodsInfo.setChoosed(!goodsInfo.isChoosed());
                    checkInterface.checkChild(groupPosition,childPosition,((CheckBox)view).isChecked());
                }
            });
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }





    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * StoreInfo组的ViewHolder
     */
    static class GroupHolder{

        CheckBox groupCheck;
        TextView groupName;
        Button groupEdit;

        public GroupHolder(View rootView) {
            groupCheck = (CheckBox) rootView.findViewById(R.id.determine_chekbox);
            groupName = (TextView) rootView.findViewById(R.id.tv_source_name);
            groupEdit = (Button) rootView.findViewById(R.id.tv_store_edtor);
        }
    }

    /**
     * GoodsInfo子项的ViewHolder
     */
    static class ChildHolder{

        CheckBox childCheck;
        ImageView goosIcon;
        TextView descripTxt;
        TextView styleTxt;
        TextView priceTxt;
        TextView discountTxt;
        TextView numTxt;

        public ChildHolder(View rootView) {
            childCheck = (CheckBox) rootView.findViewById(R.id.check_box);
            goosIcon = (ImageView) rootView.findViewById(R.id.iv_adapter_list_pic);
            descripTxt = (TextView) rootView.findViewById(R.id.tv_intro);
            styleTxt = (TextView) rootView.findViewById(R.id.tv_color_size);
            priceTxt = (TextView) rootView.findViewById(R.id.tv_price);
            discountTxt = (TextView) rootView.findViewById(R.id.tv_discount_price);
            numTxt = (TextView) rootView.findViewById(R.id.tv_buy_num);
        }
    }

}
