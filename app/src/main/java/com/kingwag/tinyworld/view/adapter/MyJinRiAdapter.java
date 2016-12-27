package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.view.activity.ItemTwoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */

public class MyJinRiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<JinRiBean.ResultBean.SlideBannersBean>listslideBannersBeen;
    private List<JinRiBean.ResultBean.CouponBannersBean>couponBannersBeen;
    private List<JinRiBean.ResultBean.CouponBean>couponBeen;
    private final int TYPE1 = 0, TYPE2 = 1, TYPE3 = 2;


    public MyJinRiAdapter(Context context, List<JinRiBean.ResultBean.SlideBannersBean> listslideBannersBeen, List<JinRiBean.ResultBean.CouponBannersBean>couponBannersBeen,List<JinRiBean.ResultBean.CouponBean>couponBeen) {
        this.context = context;
        this.listslideBannersBeen = listslideBannersBeen;
        this.couponBannersBeen=couponBannersBeen;
        this.couponBeen=couponBeen;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE1;
            case 1:
                return TYPE2;
            case 2:
                return TYPE3;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
               View view1 = LayoutInflater.from(context).inflate(R.layout.fragment_rollviewpager_jinri, parent, false);
               return new MyViewHolder1(view1);
            case 1:
                View view2 = LayoutInflater.from(context).inflate(R.layout.fragment_jinri_two_xrecyclerview, parent, false);
                return new MyViewHolder2(view2);
            case 2:
                View view3 = LayoutInflater.from(context).inflate(R.layout.fragment_jinri_three_recyclerview, parent, false);
                return new MyViewHolder3(view3);

       }       return null;
       /* View view1 = LayoutInflater.from(context).inflate(R.layout.fragment_rollviewpager_jinri, parent, false);
        return new MyViewHolder1(view1);*/

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1){
            ((MyViewHolder1) holder).setData(listslideBannersBeen);
        }
        else if (holder instanceof MyViewHolder2){
            ((MyViewHolder2) holder).setData(couponBannersBeen);

        }
        else if (holder instanceof MyViewHolder3){
            ((MyViewHolder3) holder).setData(couponBeen);


        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        private RollPagerView rollPagerView;
        private List<JinRiBean.ResultBean.SlideBannersBean>listslideBannersBeen=new ArrayList<>();
        public MyViewHolder1(View itemView) {
            super(itemView);
            rollPagerView= (RollPagerView) itemView.findViewById(R.id.rollviewpage_jinrifragment);
            rollPagerView.setAdapter(new MyJinRiRollviewpagerAdapter(rollPagerView,context,listslideBannersBeen));

        }
        public void setData(List<JinRiBean.ResultBean.SlideBannersBean> newList) {
            listslideBannersBeen.clear();
            listslideBannersBeen.addAll(newList);
            rollPagerView.getViewPager().getAdapter().notifyDataSetChanged();
        }

    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private List<JinRiBean.ResultBean.CouponBannersBean>couponBannersBeanList=new ArrayList<>();
        public MyViewHolder2(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.recycler_jinri_two);
            recyclerView.setLayoutManager(new GridLayoutManager(context,3,OrientationHelper.VERTICAL,false));
            recyclerView.setAdapter(new MyJinRiTwoAdapter(couponBannersBeanList,context));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
        public void setData(List<JinRiBean.ResultBean.CouponBannersBean>newlist2){
            couponBannersBeanList.clear();
            couponBannersBeanList.addAll(newlist2);
            recyclerView.getAdapter().notifyDataSetChanged();
        }


    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView3;
        private List<JinRiBean.ResultBean.CouponBean> mList = new ArrayList<>();

        public MyViewHolder3(View itemView) {
            super(itemView);
            recyclerView3= (RecyclerView) itemView.findViewById(R.id.recyclerview_jinri_three);
            recyclerView3.setLayoutManager(new LinearLayoutManager(context,OrientationHelper.VERTICAL,false));
            recyclerView3.setAdapter(new MyJinRiThreeAdapter(context,mList));

        }
        public void setData(List<JinRiBean.ResultBean.CouponBean>newlist3){
            mList.clear();
            mList.addAll(newlist3);
            recyclerView3.getAdapter().notifyDataSetChanged();
        }
    }
}