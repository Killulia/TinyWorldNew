package com.kingwag.tinyworld.view.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.MyJinRiAdapter;
import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.bean.JinRiShuaXinBean;
import com.kingwag.tinyworld.view.presenter.PresenterJinRi;
import com.kingwag.tinyworld.view.presenter.PresenterJinRiShuaXin;
import com.kingwag.tinyworld.view.utils.URLConstant;
import com.kingwag.tinyworld.view.view.interfacese.IShuaXinView;
import com.kingwag.tinyworld.view.view.interfacese.IView;

import java.util.ArrayList;
import java.util.List;

public class JinrituijianFragment extends Fragment implements IView,IShuaXinView{
    private Context context;
    private ProgressBar progressBar;
    private XRecyclerView xRecyclerView;
    private PresenterJinRi presenterJinRi;
    private MyJinRiAdapter myJinRiAdapter;
    private LinearLayoutManager layoutManager;
    private List<JinRiBean.ResultBean.SlideBannersBean>slideBannersBeen=new ArrayList<>();
    private List<JinRiBean.ResultBean.CouponBannersBean>couponBannersBeen=new ArrayList<>();
    private List<JinRiBean.ResultBean.CouponBean>couponBeen=new ArrayList<>();
    private int page = -1;

    private PresenterJinRiShuaXin presenterJinRiShuaXin;


    public JinrituijianFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context=getContext();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_jinrituijian, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar_jinrifragment);
        xRecyclerView= (XRecyclerView) view.findViewById(R.id.xrecyclerview_jinriFragment);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenterJinRi=new PresenterJinRi(this);
        presenterJinRi.LoadAJinRiDate(URLConstant.JINRI_URL);

        presenterJinRiShuaXin = new PresenterJinRiShuaXin(this);

        jinriAdapter();


    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadFailMsg() {

    }


    //上拉刷新数据
    @Override
    public void loadData(int page) {
        presenterJinRiShuaXin.LoadAJinRiDate(URLConstant.PAS_UR[page]);
    }

    @Override
    public void loadData(JinRiBean ajinRiBean) {
        slideBannersBeen.addAll(ajinRiBean.getResult().getSlideBanners());
        Log.e("tag",ajinRiBean.getResult().getSlideBanners()+"");
        couponBannersBeen.addAll(ajinRiBean.getResult().getCouponBanners());
        Log.e("tag",ajinRiBean.getResult().getCouponBanners()+"");
        couponBeen.addAll(ajinRiBean.getResult().getCoupon());
        Log.e("tag",ajinRiBean.getResult().getCoupon()+"");
        myJinRiAdapter.notifyDataSetChanged();
        xRecyclerView.loadMoreComplete();

    }

    private void jinriAdapter() {
        myJinRiAdapter=new MyJinRiAdapter(context,slideBannersBeen,couponBannersBeen,couponBeen);
        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setAdapter(myJinRiAdapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
               /* page+=1;
                String string=String.format(URLConstant.JINRIJIAZAI_URL,page);
                presenterJinRi.LoadAJinRiDate(string);*/

                page++;
                loadData(page);
            }
        });
    }

}
