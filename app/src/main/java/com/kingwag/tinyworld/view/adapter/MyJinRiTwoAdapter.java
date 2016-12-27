package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.view.activity.ItemTwoActivity;
import com.kingwag.tinyworld.view.view.activity.ItemTwoTwoActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyJinRiTwoAdapter  extends  RecyclerView.Adapter<MyJinRiTwoAdapter.MyAmountItemRecyclerAdapter> {
    private  List<JinRiBean.ResultBean.CouponBannersBean>couponBannersBeen;
    private Context context;

    public MyJinRiTwoAdapter(List<JinRiBean.ResultBean.CouponBannersBean> couponBannersBeen, Context context) {
        this.couponBannersBeen = couponBannersBeen;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return couponBannersBeen.size();
    }

    @Override
    public MyAmountItemRecyclerAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragmeng_two_jinri, parent, false);
        return new MyAmountItemRecyclerAdapter(view);
    }

    @Override
    public void onBindViewHolder(MyAmountItemRecyclerAdapter holder, final int position) {
        final JinRiBean.ResultBean.CouponBannersBean bannersBean=couponBannersBeen.get(position);
        final String imgUrl = bannersBean.getImageSrc();
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.jiazai)
                .into(holder.imageView_viewholder2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0) {
                    Intent intent = new Intent(context, ItemTwoActivity.class);
                    String title = bannersBean.getTitle();
                    intent.putExtra("title", title);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, ItemTwoTwoActivity.class);
                    String title = bannersBean.getTitle();
                    intent.putExtra("title", title);
                    context.startActivity(intent);
                }
            }
        });

    }


    class MyAmountItemRecyclerAdapter extends RecyclerView.ViewHolder{
        private ImageView imageView_viewholder2;

        public MyAmountItemRecyclerAdapter(View itemView) {
            super(itemView);
            imageView_viewholder2= (ImageView) itemView.findViewById(R.id.imageview_two_jinrifragment);
        }
    }
}
