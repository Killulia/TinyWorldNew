package com.kingwag.tinyworld.view.view.activity;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.MyFuZhuangAdapter;
import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.presenter.PresenterFuZhuang;
import com.kingwag.tinyworld.view.utils.URLConstant;
import com.kingwag.tinyworld.view.view.interfacese.IFuzhuangView;

import java.util.ArrayList;
import java.util.List;

public class ItemTwoActivity extends AppCompatActivity implements View.OnClickListener ,IFuzhuangView{
    private Context context;
    private TextView textView;
    private ImageView textViewfh;
    private ProgressBar progressBar2;
    private XRecyclerView xRecyclerView;
    private MyFuZhuangAdapter fuzhuangadapter;
    private PresenterFuZhuang presenterFuzhuang;
    private List<FuzhuangBean.ResultBean.CouponBean>bean=new ArrayList<>();
    private List<Object>object=new ArrayList<>();
    private String title;
    private String stringUrl1;
    private String stringUrl2;
    private String stringUrl3;
    private String stringUrl4;
    private String stringUrl5;
    private FuzhuangBean mBean;
    private int page=1;
    private String[]s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_two);
        context=getApplicationContext();
        stringUrl1 = String.format(URLConstant.ITEMTWO_BEAN,"coupon/zdm?_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482301761&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&tr=504723868702085120_0&fr=c010x1-01&pid=mm_109716036_12190578_67702230&_at=3d0a2ef65fa07ad5843958e369013e7e&");
        stringUrl2=String.format(URLConstant.ITEMTWO_BEAN,"coupon/zdm?_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482301770&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&wp=eyJjYXRlZ29yeSI6IiIsInRhZ3MiOiJcdTUwM2NcdTVmOTdcdTRlNzAiLCJzb3J0IjoiNiIsImZJZCI6IiIsInBhZ2UiOjJ9&tr=504723868702085120_0&fr=c010x1-01&pid=mm_109716036_12190578_67702230&_at=d2b1c1ee2af351b055be0bf7f97ca226&");
        stringUrl3=String.format(URLConstant.ITEMTWO_BEAN,"");
        stringUrl4=String.format(URLConstant.ITEMTWO_BEAN,"");
        stringUrl5=String.format(URLConstant.ITEMTWO_BEAN,"");

        title = getIntent().getStringExtra("title");
        initview();
    }

    private void initview() {
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar);
        xRecyclerView = (XRecyclerView) findViewById(R.id.xrecycler_itemtwo);
        fuzhuangadapter=new MyFuZhuangAdapter(context,object);
        xRecyclerView.setAdapter(fuzhuangadapter);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL, false));
        presenterFuzhuang = new PresenterFuZhuang(this);
        presenterFuzhuang.LoadFuZhuangData(stringUrl1);


        textView = (TextView) findViewById(R.id.text_bb);
        textViewfh = (ImageView) findViewById(R.id.image_fanhui);
        textViewfh.setOnClickListener(this);
        textView.setText(title);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                object.clear();
                page = 1;
                presenterFuzhuang.LoadFuZhuangData(stringUrl1);
            }

            @Override
            public void onLoadMore() {
                page+=1;
                if (page==2){
                    String urther = String.format(stringUrl2,page);
                    presenterFuzhuang.LoadFuZhuangData(urther);
                }if (page==3){
                    String urther = String.format(stringUrl1,page);
                    presenterFuzhuang.LoadFuZhuangData(urther);
                }else {
                    String urther = String.format(stringUrl2,page);
                    presenterFuzhuang.LoadFuZhuangData(urther);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_fanhui:
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar2.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void loadData(FuzhuangBean fuzhuangBean) {

        mBean=fuzhuangBean;
        bean=mBean.getResult().getCoupon();
        object.addAll(bean);
        fuzhuangadapter.notifyDataSetChanged();

        xRecyclerView.refreshComplete();
        xRecyclerView.loadMoreComplete();

    }
}