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
import com.kingwag.tinyworld.view.presenter.PresenterFuZhuang;
import com.kingwag.tinyworld.view.presenter.PresenterJinRi;
import com.kingwag.tinyworld.view.presenter.PresenterJinRiShuaXin;
import com.kingwag.tinyworld.view.utils.URLConstant;
import com.kingwag.tinyworld.view.view.activity.MainActivity;
import com.kingwag.tinyworld.view.view.interfacese.IShuaXinView;
import com.kingwag.tinyworld.view.view.interfacese.IView;

import java.util.ArrayList;
import java.util.List;

public class JinrituijianFragment extends Fragment implements IView{
    private Context context;
    private ProgressBar progressBar;
    private XRecyclerView xRecyclerView;
    private PresenterJinRi presenterJinRi;
    private MyJinRiAdapter myJinRiAdapter;
    private LinearLayoutManager layoutManager;
    private List<JinRiBean.ResultBean.SlideBannersBean>slideBannersBeen=new ArrayList<>();
    private List<JinRiBean.ResultBean.CouponBannersBean>couponBannersBeen=new ArrayList<>();
    private List<JinRiBean.ResultBean.CouponBean>couponBeen=new ArrayList<>();
    private int page = 1;
    private String stringUrl;
    private String stringUr2;

    private PresenterJinRiShuaXin presenterJinRiShuaXin;


    public JinrituijianFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context=getContext();
        super.onCreate(savedInstanceState);
        stringUrl = String.format(URLConstant.FUZHUANG_URL,"1481115046&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=2&_at=d1976d749d022538c98461fee89c708f&");
        stringUr2=String.format(URLConstant.ITEMTWO_BEAN,"itemactivity/index?_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482298543&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&cat=jr&wp=eyJzb3J0IjoiNSIsImNhdCI6ImpyIiwicGFnZSI6MiwiaGFzVG9waWMiOnRydWUsImhpc0lkcyI6ImVKd2RqTUVOQURBSUFoZnFvMnBRMkgreFZsNFhMa0RrM01vVDJXd2F4QWVrM3RSUmxySWtacE1ZeG5pSHU3djVQNWJsQ3FFSE12SVNCUT09In0%3D&_at=e232f44c3bbc607b4b04c9edc1ff562e&");
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
        //presenterFuZhuang=new PresenterFuZhuang();
        presenterJinRi.LoadAJinRiDate(URLConstant.JINRI_URL);

        jinriAdapter();


    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadFailMsg() {

    }



    @Override
    public void loadData(JinRiBean ajinRiBean) {
        slideBannersBeen.addAll(ajinRiBean.getResult().getSlideBanners());

        couponBannersBeen.addAll(ajinRiBean.getResult().getCouponBanners());

        couponBeen.addAll(ajinRiBean.getResult().getCoupon());

        myJinRiAdapter.notifyDataSetChanged();
        xRecyclerView.refreshComplete();

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
                slideBannersBeen.clear();
                couponBannersBeen.clear();
                couponBeen.clear();
                page = 1;
                presenterJinRi.LoadAJinRiDate(URLConstant.JINRI_URL);
            }

            @Override
            public void onLoadMore() {
                page+=1;
                if (page==2){
                    String urther = String.format(stringUrl,page);
                    presenterJinRi.LoadAJinRiDate(urther);
                }/*if (page==3){
                    String urther = String.format(stringUrl2,page);
                    presenterJinRi.LoadAJinRiDate(urther);
                }else {
                    String urther = String.format(stringUrl2,page);
                    presenterJinRi.LoadAJinRiDate(urther);
                }*/

            }
        });
    }

}
