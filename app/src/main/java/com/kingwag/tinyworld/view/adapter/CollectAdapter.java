package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.Goods;
import java.util.List;

/**
 * Created by kingwag on 2016/12/29.
 */

public class CollectAdapter extends BaseAdapter {
    private List<Goods> goodses;
    private LayoutInflater inflater;
    private Context context;

    public CollectAdapter(Context context,List<Goods> goodses ) {
        this.goodses = goodses;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodses.size();
    }

    @Override
    public Object getItem(int i) {
        return goodses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CollectHolder holder = null;
        if (view==null){
            view = inflater.inflate(R.layout.item_collect_list, viewGroup, false);
            holder = new CollectHolder(view);
            view.setTag(holder);
        }else {
            holder = (CollectHolder) view.getTag();
        }
        Goods goods = goodses.get(i);
        ImageView imageView = holder.Img;
        Glide.with(context).load(goods.getImageUrl()).into(imageView);
        holder.name.setText(goods.getName());
        holder.price.setText("￥"+goods.getPrice());
        //原价格加横线
        SpannableString spanString = new SpannableString("￥" + String.valueOf(goods.getDiscountPrice()));
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, 0, String.valueOf(goods.getDiscountPrice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (holder.discount.getText().toString().length() > 0) {
            holder.discount.setText("");
        }
        holder.discount.append(spanString);
        holder.style.setText(goods.getStyle());
        return view;
    }

    static class CollectHolder{
        ImageView Img;
        TextView name;
        TextView price;
        TextView discount;
        TextView style;
        public CollectHolder(View rootView) {
            Img = (ImageView) rootView.findViewById(R.id.item_collect_img);
            name = (TextView) rootView.findViewById(R.id.item_collect_name);
            price = (TextView) rootView.findViewById(R.id.item_collect_price);
            discount = (TextView) rootView.findViewById(R.id.item_collect_discount_price);
            style = (TextView) rootView.findViewById(R.id.tv_color_size);
        }
    }

}
