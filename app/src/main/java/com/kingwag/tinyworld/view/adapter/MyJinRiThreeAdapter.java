package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.JinRiBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyJinRiThreeAdapter extends RecyclerView.Adapter<MyJinRiThreeAdapter.MyViewHolder> {
    private Context context;
    private List<JinRiBean.ResultBean.CouponBean>couponBeen;

    public MyJinRiThreeAdapter(Context context, List<JinRiBean.ResultBean.CouponBean> couponBeen) {
        this.context = context;
        this.couponBeen = couponBeen;
    }
    @Override
    public int getItemCount() {
        return couponBeen.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_three_jinri, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        JinRiBean.ResultBean.CouponBean Bean=couponBeen.get(position);
        final String imgUrl = Bean.getCoverImage();
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.jiazai)
                .into(holder.imageView_viewholder3_zuo);
         holder.textView1.setText(couponBeen.get(position).getTitle());
         holder.textView2.setText(couponBeen.get(position).getPrice());
         holder.textView3.setText(couponBeen.get(position).getOPrice());
         holder.textView3.getPaint().setAntiAlias(true);
         holder.textView3.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        if (Bean.getSurplus().equals(0)){
            holder.textView4.setText("已抢光");
        }else {
         holder.textView4.setText("剩余"+couponBeen.get(position).getSurplus()+"张");
        }
         holder.textView5.setText(couponBeen.get(position).getAmount());

    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_viewholder3_zuo;
        private TextView textView1,textView2,textView3,textView4,textView5;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView_viewholder3_zuo= (ImageView) itemView.findViewById(R.id.imageview_three_jinri);
            textView1= (TextView) itemView.findViewById(R.id.text_three1_jinri);
            textView2= (TextView) itemView.findViewById(R.id.textview_three2_jinri);
            textView3= (TextView) itemView.findViewById(R.id.textview_three3_jinri);
            textView4= (TextView) itemView.findViewById(R.id.textview_three4_liji);
            textView5= (TextView) itemView.findViewById(R.id.textview_three5_liji);
        }
    }
}
