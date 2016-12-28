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
import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.view.activity.DetailActivity;

import java.util.List;

public class MyFuZhuangAdapter extends RecyclerView.Adapter<MyFuZhuangAdapter.MyViewHolder> {
    private Context context;
    private List<Object> list;
    private List<FuzhuangBean.ResultBean.CouponBean>couponBeen;

    public MyFuZhuangAdapter(Context context,List<Object> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_three_jinri, parent, false);
        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //if (list.get(position) instanceof  FuzhuangBean.ResultBean.CouponBean) {}
            final FuzhuangBean.ResultBean.CouponBean Bean = (FuzhuangBean.ResultBean.CouponBean) list.get(position);
            final String imgUrl = Bean.getCoverImage();
            Glide.with(context)
                    .load(imgUrl)
                    .placeholder(R.drawable.jiazai)
                    .into(holder.imageView_viewholder3_zuo);
            holder.textView1.setText(Bean.getTitle());
            holder.textView2.setText(Bean.getPrice());
            holder.textView3.setText(Bean.getOPrice());
            holder.textView3.getPaint().setAntiAlias(true);
            holder.textView3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            if (Bean.getSurplus().equals(0)) {
                holder.textView4.setText("已抢光");
            } else {
                holder.textView4.setText("剩余" + Bean.getSurplus() + "张");
            }
            holder.textView5.setText(Bean.getAmount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("image",imgUrl);
                intent.putExtra("title",Bean.getTitle());
                intent.putExtra("price",Bean.getPrice());
                intent.putExtra("oprice",Bean.getOPrice());
                intent.putExtra("bigimage",imgUrl);
                intent.putExtra("youhuijia",Bean.getCouponPrice());

                context.startActivity(intent);
            }
        });

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
