package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.bean.ItemItemBean;
import com.kingwag.tinyworld.view.bean.JinRiBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyTwoTwoAdapter extends RecyclerView.Adapter<MyTwoTwoAdapter.MyViewHolder> {
    private Context context;
    private List<ItemItemBean.ResultBean.ListBean.CouponBean>objectList;

    public MyTwoTwoAdapter(Context context, List<ItemItemBean.ResultBean.ListBean.CouponBean> objectList) {
        this.context = context;
        this.objectList = objectList;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_itemtwo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ItemItemBean.ResultBean.ListBean.CouponBean Bean=objectList.get(position);
        final String imgUrl = Bean.getCoverImage();
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.jiazai)
                .into(holder.im);
         holder.textView2.setText(Bean.getTitle());
         holder.textView3.setText("剩余"+Bean.getSurplus()+"张");
         holder.textView4.setText(Bean.getPrice());
         holder.textView5.setText(Bean.getOPrice());
        holder.textView5.getPaint().setAntiAlias(true);
        holder.textView5.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        holder.textView6.setText(Bean.getAmount());
        if (position==1){

        }

    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView im;
        private TextView textView2,textView3,textView4,textView5,textView6;
        public MyViewHolder(View itemView) {
            super(itemView);
            im= (ImageView) itemView.findViewById(R.id.imageview_twotwo1_activity);
            textView2= (TextView) itemView.findViewById(R.id.text_twotwo2_jinri);
            textView3= (TextView) itemView.findViewById(R.id.textview_twotwo3_liji);
            textView4= (TextView) itemView.findViewById(R.id.textview_twotwo4_jinri);
            textView5= (TextView) itemView.findViewById(R.id.textview_three5_jinri);
            textView6= (TextView) itemView.findViewById(R.id.twptwp6);
        }
    }
}
