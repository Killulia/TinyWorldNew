package com.kingwag.tinyworld.view.view.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.MyFuZhuangAdapter;
import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.presenter.PresenterFuZhuang;
import com.kingwag.tinyworld.view.utils.URLConstant;
import com.kingwag.tinyworld.view.view.interfacese.IFuzhuangView;

import java.util.ArrayList;
import java.util.List;


public class BaihuoFragment extends Fragment implements View.OnClickListener,IFuzhuangView{
    private Context context;
    private XRecyclerView xRecyclerView;
    private RadioGroup radioGroup;
    private RadioButton radioButton_zonghe,radioButton_zhekou,radioButton_jiage;
    private boolean state1=false;
    private boolean state2=false;
    private List<Object>objectList=new ArrayList<>();
    private MyFuZhuangAdapter fuZhuangAdapter;
    private ProgressBar progressBar;
    private List<FuzhuangBean.ResultBean.CouponBean>couponBeen=new ArrayList<>();
    private PresenterFuZhuang presenterFuZhuang;
    private String stringUrl1;
    private String stringUrl2;
    private String stringUrl3;
    private String stringUrl4;
    private String stringUrl5;

    private  FuzhuangBean mBean;
    private int page = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        stringUrl1 = String.format(URLConstant.BAIHUO_URL,"00000000-1e5c-3f76-ffff-ffffeb116fa8&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1481116153&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=8&_at=a23e630d87084c778503a1bc98c093be&");
        //下
        stringUrl2=String.format(URLConstant.BAIHUO_URL,"00000000-1e5c-3f76-ffff-ffffeb116fa8&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1481116198&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=8&sort=1&_at=002a819340cb603791a9466a432b5cfd&");
        //上
        stringUrl3=String.format(URLConstant.BAIHUO_URL,"00000000-0931-3212-08c3-a64e2ad2a501&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1481117504&_swidth=720&_imei=864394010789032&_manufacture=Samsung&_model=Note7&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=xiaoshijie&_av=173&cat=8&sort=2&_at=72bf2246bc3bd984891b17d6a9c6e34a&");
        //上
        stringUrl4=String.format(URLConstant.BAIHUO_URL,"00000000-1e5c-3f76-ffff-ffffeb116fa8&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1481116217&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=8&sort=4&_at=aea9b09a7f60be19f82f3e568bed79e9&");
        //下
        stringUrl5=String.format(URLConstant.BAIHUO_URL,"00000000-0931-3212-08c3-a64e2ad2a501&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1481117542&_swidth=720&_imei=864394010789032&_manufacture=Samsung&_model=Note7&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=xiaoshijie&_av=173&cat=8&sort=3&_at=c9dc8a94b2636726f78cf617ac5e474d&");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_meizhuang, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup= (RadioGroup) view.findViewById(R.id.radioGroup_fuzhuang);
        radioButton_zonghe= (RadioButton) view.findViewById(R.id.zonghe_fuzhuang);
        radioButton_zhekou= (RadioButton) view.findViewById(R.id.zhekou_fuzhuang);
        radioButton_jiage= (RadioButton) view.findViewById(R.id.jiage_fuzhuang);
        radioButton_zonghe.setOnClickListener(this);
        radioButton_zonghe.setChecked(true);
        radioButton_zhekou.setOnClickListener(this);
        radioButton_jiage.setOnClickListener(this);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar_fuzhuang);
        fuZhuangAdapter=new MyFuZhuangAdapter(context,objectList);

        xRecyclerView= (XRecyclerView) view.findViewById(R.id.xrecycler_fuzhuang);
        xRecyclerView.setAdapter(fuZhuangAdapter);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(context,OrientationHelper.VERTICAL, false));
        presenterFuZhuang = new PresenterFuZhuang(this);
        presenterFuZhuang.LoadFuZhuangData(stringUrl1);
    }

    @Override
    public void onClick(View v) {
        Drawable drawable;
        switch (v.getId()) {
            case R.id.zonghe_fuzhuang:
                radioButton_zonghe.setTextColor(getResources().getColor(R.color.red));
                radioButton_zhekou.setTextColor(getResources().getColor(R.color.gray));
                radioButton_jiage.setTextColor(getResources().getColor(R.color.gray));
                drawable = getResources().getDrawable(R.mipmap.shop_sort_def);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                radioButton_zhekou.setCompoundDrawables(null, null, drawable, null);
                radioButton_jiage.setCompoundDrawables(null, null, drawable, null);
                objectList.clear();
                objectList.addAll(couponBeen);
                fuZhuangAdapter.notifyDataSetChanged();
                presenterFuZhuang.LoadFuZhuangData(stringUrl1);
                break;
            case R.id.zhekou_fuzhuang:
                state1=!state1;
                radioButton_zonghe.setTextColor(getResources().getColor(R.color.gray));
                radioButton_zhekou.setTextColor(getResources().getColor(R.color.red));
                radioButton_jiage.setTextColor(getResources().getColor(R.color.gray));

                drawable = getResources().getDrawable(R.mipmap.shop_sort_def);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                radioButton_jiage.setCompoundDrawables(null, null, drawable, null);

                if (state1){
                    drawable = getResources().getDrawable(R.mipmap.shop_sort_asc);
                    objectList.clear();
                    couponBeen=mBean.getResult().getCoupon();
                    objectList.addAll(couponBeen);
                    fuZhuangAdapter.notifyDataSetChanged();
                    presenterFuZhuang.LoadFuZhuangData(stringUrl3);

                }else {
                    drawable = getResources().getDrawable(R.mipmap.shop_sort_desc);
                    objectList.clear();
                    couponBeen=mBean.getResult().getCoupon();
                    objectList.addAll(couponBeen);
                    fuZhuangAdapter.notifyDataSetChanged();
                    presenterFuZhuang.LoadFuZhuangData(stringUrl2);
                }
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                radioButton_zhekou.setCompoundDrawables(null, null, drawable, null);

                break;
            case R.id.jiage_fuzhuang:
                state2=!state2;
                radioButton_zonghe.setTextColor(getResources().getColor(R.color.gray));
                radioButton_zhekou.setTextColor(getResources().getColor(R.color.gray));
                radioButton_jiage.setTextColor(getResources().getColor(R.color.red));

                drawable = getResources().getDrawable(R.mipmap.shop_sort_def);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                radioButton_zhekou.setCompoundDrawables(null, null, drawable, null);
                if (state2){
                    drawable = getResources().getDrawable(R.mipmap.shop_sort_asc);
                    objectList.clear();
                    couponBeen=mBean.getResult().getCoupon();
                    objectList.addAll(couponBeen);
                    fuZhuangAdapter.notifyDataSetChanged();
                    presenterFuZhuang.LoadFuZhuangData(stringUrl4);

                }else {
                    drawable = getResources().getDrawable(R.mipmap.shop_sort_desc);
                    objectList.clear();
                    couponBeen=mBean.getResult().getCoupon();
                    objectList.addAll(couponBeen);
                    fuZhuangAdapter.notifyDataSetChanged();
                    presenterFuZhuang.LoadFuZhuangData(stringUrl5);
                }
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                radioButton_jiage.setCompoundDrawables(null, null, drawable, null);

                break;

        }
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                page+=1;
                String s = String.format(stringUrl1,page);
                presenterFuZhuang.LoadFuZhuangData(s);
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void loadData(FuzhuangBean fuzhuangBean) {
        objectList.clear();
        mBean=fuzhuangBean;
        couponBeen=mBean.getResult().getCoupon();
        objectList.addAll(couponBeen);
        fuZhuangAdapter.notifyDataSetChanged();
        xRecyclerView.loadMoreComplete();

    }

}
