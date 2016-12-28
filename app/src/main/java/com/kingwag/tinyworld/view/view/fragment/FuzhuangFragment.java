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
/**
 * A simple {@link Fragment} subclass.
 */


    public class FuzhuangFragment extends Fragment implements View.OnClickListener, IFuzhuangView{
        private Context context;
        private XRecyclerView xRecyclerView;
        private RadioGroup radioGroup;
        private RadioButton radioButton_zonghe, radioButton_zhekou, radioButton_jiage;
        private boolean state1 = false;
        private boolean state2 = false;
        private List<Object> objectList = new ArrayList<>();
        private MyFuZhuangAdapter fuZhuangAdapter;
        private ProgressBar progressBar;
        private List<FuzhuangBean.ResultBean.CouponBean> couponBeen = new ArrayList<>();
        private PresenterFuZhuang presenterFuZhuang;
        private String stringUrl1;
        private String stringUrl2;
        private String stringUrl3;
        private String stringUrl4;
        private String stringUrl5;
        private String stringUrl11;
        private String stringurl12;
        private String stringurl13;


        private FuzhuangBean mBean;
        private int page = 1;

        public FuzhuangFragment() {
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = getContext();
            //综合
            stringUrl1 = String.format(URLConstant.FUZHUANG_URL, "1481115046&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=2&_at=d1976d749d022538c98461fee89c708f&");
            stringUrl11 = String.format(URLConstant.BASE_URLl, "_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482299096&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&cat=2&wp=eyJzb3J0IjoiNSIsImNhdCI6IjIiLCJwYWdlIjozLCJoYXNUb3BpYyI6ZmFsc2UsImhpc0lkcyI6ImVKd2xqY3NWQUNFTUFodmFRNzZZOU5cL1lLcHptUlJqeEFOSVwvRDh6MFJlXC9pWGZDOHFKa0hMSXpvMXp5T0ZPSWhzcGlWS3J2VXQ1U0ZtcnlzVzU5eFlibDNLMW80cmtmcWc1VW5vVWVaRmthZjJaSE96R0xrNFFjUXl5bW0ifQ%3D%3D&_at=887c58620a7f773ae4f3dc8f923d8d88&");
            stringurl12 = String.format(URLConstant.ITEMTWO_BEAN, "itemactivity/index?_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482299152&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&cat=2&wp=eyJzb3J0IjoiNSIsImNhdCI6IjIiLCJwYWdlIjo0LCJoYXNUb3BpYyI6ZmFsc2UsImhpc0lkcyI6ImVKd3RrTWNWeENBTUJSdmFnd0pLXC9UZTJvUEZwSHZ4a281YnArbFBMN3JpSW1YeW5WTDg0M1E4NUtZdDR6dEowWUFcL21aN1dEWldiamM5QU01NTRrZ3JKZG1OMjdGaFpLdWR4NDU1QWpFSTNHUWxNbVJYdzFzU2FYcXlrdEZYeExNZHY3ZjVcL0ZuSmJHNGp3QjFTWlVENWFkalcwcGx3XC96QjNmblBuWT0ifQ%3D%3D&_at=1939b96ab49b82569f085564675e7f40&");
            stringurl13 = String.format(URLConstant.ITEMTWO_BEAN, "itemactivity/index?_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482299154&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&cat=2&wp=eyJzb3J0IjoiNSIsImNhdCI6IjIiLCJwYWdlIjo1LCJoYXNUb3BpYyI6ZmFsc2UsImhpc0lkcyI6ImVKd3RrTUVSeERBSUF4dTZCMkNEb2ZcL0dMdGI2cFVtUVZrbzhxcGJcL1BLbzdQOG1adWtcL2w2NVBkZmFXbVRKTFhlYndXRWxkaWJkMDJsaG5GWjNNTG5IcXlUR0JxR1BWOUZocU84MUx4cmlGSElKc2JEUTNNRG5IZExKcGM2ZVpRVHJMbFVOdjZ2bWVKQmFXeExINEI2RERRZzBXMUtjcFo5dVExUUdtTmoyQnV5T0tXV041T1pEdE1paWJKUVVsMk1qZERUTnZuRDJET1V5ND0ifQ%3D%3D&_at=97023eb3d21bf936cc5bae38bdf9c945&");

            //下
            stringUrl2 = String.format(URLConstant.FUZHUANG_URL, "1481115135&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=2&sort=1&_at=7ee9a1c8093c3c70b45cdaa60a1ee2b5&");
            //上
            stringUrl3 = String.format(URLConstant.FUZHUANG_URL, "1481117302&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=2&sort=2&_at=e1546fd450b63309b8e01f7884251a72&");
            //上
            stringUrl4 = String.format(URLConstant.FUZHUANG_URL, "1481115189&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=2&sort=4&_at=84a004c659916c3489a324de92a8d3f2&");
            //下
            stringUrl5 = String.format(URLConstant.FUZHUANG_URL, "1481117444&_swidth=720&_imei=864394010947127&_manufacture=samsung&_model=SM-T211&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.3.0&_channel=anzhi&_av=173&cat=2&sort=3&_at=44e9e248be86d2a8aeb147358ba49a2c&");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_fuzhuang, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_fuzhuang);
            radioButton_zonghe = (RadioButton) view.findViewById(R.id.zonghe_fuzhuang);
            radioButton_zhekou = (RadioButton) view.findViewById(R.id.zhekou_fuzhuang);
            radioButton_jiage = (RadioButton) view.findViewById(R.id.jiage_fuzhuang);
            radioButton_zonghe.setOnClickListener(this);
            radioButton_zonghe.setChecked(true);
            radioButton_zhekou.setOnClickListener(this);
            radioButton_jiage.setOnClickListener(this);

            progressBar = (ProgressBar) view.findViewById(R.id.progressbar_fuzhuang);
            fuZhuangAdapter = new MyFuZhuangAdapter(context, objectList);

            xRecyclerView = (XRecyclerView) view.findViewById(R.id.xrecycler_fuzhuang);
            xRecyclerView.setAdapter(fuZhuangAdapter);
            xRecyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL, false));
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
                    state1 = !state1;
                    radioButton_zonghe.setTextColor(getResources().getColor(R.color.gray));
                    radioButton_zhekou.setTextColor(getResources().getColor(R.color.red));
                    radioButton_jiage.setTextColor(getResources().getColor(R.color.gray));

                    drawable = getResources().getDrawable(R.mipmap.shop_sort_def);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    radioButton_jiage.setCompoundDrawables(null, null, drawable, null);

                    if (state1) {
                        drawable = getResources().getDrawable(R.mipmap.shop_sort_asc);
                        objectList.clear();
                        couponBeen = mBean.getResult().getCoupon();
                        objectList.addAll(couponBeen);
                        fuZhuangAdapter.notifyDataSetChanged();
                        presenterFuZhuang.LoadFuZhuangData(stringUrl3);

                    } else {
                        drawable = getResources().getDrawable(R.mipmap.shop_sort_desc);
                        objectList.clear();
                        couponBeen = mBean.getResult().getCoupon();
                        objectList.addAll(couponBeen);
                        fuZhuangAdapter.notifyDataSetChanged();
                        presenterFuZhuang.LoadFuZhuangData(stringUrl2);
                    }
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    radioButton_zhekou.setCompoundDrawables(null, null, drawable, null);

                    break;
                case R.id.jiage_fuzhuang:
                    state2 = !state2;
                    radioButton_zonghe.setTextColor(getResources().getColor(R.color.gray));
                    radioButton_zhekou.setTextColor(getResources().getColor(R.color.gray));
                    radioButton_jiage.setTextColor(getResources().getColor(R.color.red));

                    drawable = getResources().getDrawable(R.mipmap.shop_sort_def);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    radioButton_zhekou.setCompoundDrawables(null, null, drawable, null);

                    if (state2) {
                        drawable = getResources().getDrawable(R.mipmap.shop_sort_asc);
                        objectList.clear();
                        couponBeen = mBean.getResult().getCoupon();
                        objectList.addAll(couponBeen);
                        fuZhuangAdapter.notifyDataSetChanged();
                        presenterFuZhuang.LoadFuZhuangData(stringUrl4);


                    } else {
                        drawable = getResources().getDrawable(R.mipmap.shop_sort_desc);
                        objectList.clear();
                        couponBeen = mBean.getResult().getCoupon();
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
                    objectList.clear();
                    page = 1;
                    presenterFuZhuang.LoadFuZhuangData(stringUrl1);
                }

                @Override
                public void onLoadMore() {
                    page += 1;
                    if (page == 2) {
                        String s = String.format(stringUrl11, page);
                        presenterFuZhuang.LoadFuZhuangData(s);
                    }
                    if (page == 3) {
                        String s = String.format(stringurl12, page);
                        presenterFuZhuang.LoadFuZhuangData(s);
                    }
                    if (page == 4) {
                        String s = String.format(stringurl13, page);
                        presenterFuZhuang.LoadFuZhuangData(s);
                    }
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
            mBean = fuzhuangBean;
            couponBeen = mBean.getResult().getCoupon();
            objectList.addAll(couponBeen);
            fuZhuangAdapter.notifyDataSetChanged();
            //xRecyclerView.refreshComplete();

        }

}