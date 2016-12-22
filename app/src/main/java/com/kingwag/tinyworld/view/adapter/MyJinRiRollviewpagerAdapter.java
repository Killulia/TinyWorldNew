package com.kingwag.tinyworld.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.JinRiBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */

public class MyJinRiRollviewpagerAdapter extends LoopPagerAdapter{
    private Context context;
    private List<JinRiBean.ResultBean.SlideBannersBean>slideBannersBeen;

    public MyJinRiRollviewpagerAdapter(RollPagerView viewPager, Context context, List<JinRiBean.ResultBean.SlideBannersBean> slideBannersBeen) {
        super(viewPager);
        this.context = context;
        this.slideBannersBeen = slideBannersBeen;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(context).load(slideBannersBeen.get(position).getImageSrc()).placeholder(R.drawable.jiazai).into(imageView);
        return imageView;
    }

    @Override
    public int getRealCount() {
        return slideBannersBeen.size();
    }
}
