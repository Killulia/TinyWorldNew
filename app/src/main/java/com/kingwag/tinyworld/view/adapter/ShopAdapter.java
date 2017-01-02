package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.Goods;
import com.kingwag.tinyworld.view.bean.Store;
import com.kingwag.tinyworld.view.helper.GoodsManager;
import com.kingwag.tinyworld.view.custom.CustomDialog;
import java.util.List;

/**
 * Created by kingwag on 2016/12/21.
 */

/**
 * 购物车适配器
 */
public class ShopAdapter extends BaseExpandableListAdapter {

    private List<Store> stores;
    private Context context;
    private LayoutInflater inflater;
    private CheckInterface checkInterface;
    private GroupEditorListener mListener;
    private ModifyCountInterface modifyCountInterface;
    private GoodsManager manager;


    public ShopAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
        inflater = LayoutInflater.from(context);
        manager = new GoodsManager(context);
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    public GroupEditorListener getmListener() {
        return mListener;
    }

    public void setmListener(GroupEditorListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getGroupCount() {
        return stores.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return stores.get(position).getGoodses().size();
    }

    @Override
    public Object getGroup(int position) {
        return stores.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return stores.get(groupPosition).getGoodses().get(childPosition);
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
        final GroupHolder groupHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_shopcart_group, viewGroup, false);
            groupHolder = new GroupHolder(view);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) view.getTag();
        }
        final Store store = stores.get(groupPosition);
        groupHolder.groupName.setText(store.getName());
        //groupHolder.groupCheck.setChecked(store.isChoosed());
        groupHolder.groupCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store.setChoosed(((CheckBox) view).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) view).isChecked());
            }
        });
        //后加，上边setchoosed后改，原来:!store.ischecked
        groupHolder.groupCheck.setChecked(store.isChoosed());
        if (store.isEdtor()) {
            groupHolder.groupEdit.setText("完成");
        } else {
            groupHolder.groupEdit.setText("编辑");
        }
        groupHolder.groupEdit.setOnClickListener(new GroupViewClick(groupPosition, groupHolder.groupEdit, store));
        notifyDataSetChanged();
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean b, View view, ViewGroup viewGroup){
        final ChildHolder childHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_shopcart_product, viewGroup, false);
            childHolder = new ChildHolder(view);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }
        if (stores.get(groupPosition).isEdtor() == true) {
            childHolder.llEdit.setVisibility(View.VISIBLE);
            childHolder.llnoEdit.setVisibility(View.GONE);
        } else {
            childHolder.llEdit.setVisibility(View.GONE);
            childHolder.llnoEdit.setVisibility(View.VISIBLE);
        }
        final Goods goods = stores.get(groupPosition).getGoodses().get(childPosition);
        if (goods != null) {
            ImageView icon = childHolder.goosIcon;
            Glide.with(context).load(goods.getImageUrl()).into(icon);
            childHolder.descripTxt.setText(goods.getName());
            childHolder.styleTxt.setText(goods.getStyle());
            childHolder.priceTxt.setText("￥" + String.valueOf(goods.getPrice()));
            //原价格加横线
            SpannableString spanString = new SpannableString("￥" + String.valueOf(goods.getDiscountPrice()));
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, 0, String.valueOf(goods.getDiscountPrice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (childHolder.discountTxt.getText().toString().length() > 0) {
                childHolder.discountTxt.setText("");
            }
            childHolder.discountTxt.append(spanString);
            childHolder.numTxt.setText("x" + goods.getCount());
            childHolder.childCheck.setChecked(goods.isChoosed());
            childHolder.childCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //原版
                    //goods.setChoosed(!goods.isChoosed());
                    goods.setChoosed(((CheckBox) view).isChecked());
                    childHolder.childCheck.setChecked(((CheckBox) view).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) view).isChecked());
                }
            });
            childHolder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, childHolder.tvNum, childHolder.childCheck.isChecked());
                }
            });
            childHolder.tvReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, childHolder.tvNum, childHolder.childCheck.isChecked());
                }
            });
            childHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new CustomDialog(context) {
                        @Override
                        public void out()  {
                            modifyCountInterface.childDelete(groupPosition, childPosition);


                            dismiss();
                            notifyDataSetChanged();



                        }
                    }.show();
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
     * 监听编辑状态
     */
    public interface GroupEditorListener {
        void groupEdit(int groupPosition);
    }

    /**
     * 使某个组处于编辑状态
     * <p/>
     * groupPosition组的位置
     */
    class GroupViewClick implements View.OnClickListener {
        private int groupPosition;
        private Button edtor;
        private Store group;

        public GroupViewClick(int groupPosition, Button edtor, Store group) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
                if (group.isEdtor()) {
                    group.setEdtor(false);
                } else {
                    group.setEdtor(true);

                }
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param groupPosition
         * @param childPosition
         */
        void childDelete(int groupPosition, int childPosition);
    }

    /**
     * StoreInfo组的ViewHolder
     */
    static class GroupHolder {

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
    static class ChildHolder {

        CheckBox childCheck;
        ImageView goosIcon;
        TextView descripTxt;
        TextView styleTxt;
        TextView priceTxt;
        TextView discountTxt;
        TextView numTxt;
        RelativeLayout llnoEdit;
        LinearLayout llEdit;
        TextView tvReduce;
        TextView tvAdd;
        TextView tvDelete;
        TextView tvNum;

        public ChildHolder(View rootView) {
            childCheck = (CheckBox) rootView.findViewById(R.id.check_box);
            goosIcon = (ImageView) rootView.findViewById(R.id.iv_adapter_list_pic);
            descripTxt = (TextView) rootView.findViewById(R.id.tv_intro);
            styleTxt = (TextView) rootView.findViewById(R.id.tv_color_size);
            priceTxt = (TextView) rootView.findViewById(R.id.tv_price);
            discountTxt = (TextView) rootView.findViewById(R.id.tv_discount_price);
            numTxt = (TextView) rootView.findViewById(R.id.tv_buy_num);
            llnoEdit = (RelativeLayout) rootView.findViewById(R.id.rl_no_edtor);
            llEdit = (LinearLayout) rootView.findViewById(R.id.ll_edtor);
            tvAdd = (TextView) rootView.findViewById(R.id.tv_add);
            tvReduce = (TextView) rootView.findViewById(R.id.tv_reduce);
            tvDelete = (TextView) rootView.findViewById(R.id.tv_goods_delete);
            tvNum = (TextView) rootView.findViewById(R.id.tv_num);
        }
    }

}
