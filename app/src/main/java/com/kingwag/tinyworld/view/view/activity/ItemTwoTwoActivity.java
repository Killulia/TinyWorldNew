package com.kingwag.tinyworld.view.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.MyFuZhuangAdapter;
import com.kingwag.tinyworld.view.adapter.MyTwoTwoAdapter;
import com.kingwag.tinyworld.view.bean.FuzhuangBean;
import com.kingwag.tinyworld.view.bean.ItemItemBean;
import com.kingwag.tinyworld.view.bean.JinRiBean;
import com.kingwag.tinyworld.view.presenter.PresenterFuZhuang;
import com.kingwag.tinyworld.view.presenter.PresenterTwoTwo;
import com.kingwag.tinyworld.view.utils.URLConstant;
import com.kingwag.tinyworld.view.view.interfacese.IFuzhuangView;
import com.kingwag.tinyworld.view.view.interfacese.IItemItemView;

import java.util.ArrayList;
import java.util.List;

public class ItemTwoTwoActivity extends AppCompatActivity implements IItemItemView,View.OnClickListener{
    private Context context;
    private TextView textViewcc;
    private ImageView textViewfh;
    private ProgressBar progressBar2;
    private RecyclerView recyclerView;
    private MyTwoTwoAdapter myttadapter;
    private PresenterTwoTwo presentertwotwo;
    private List<ItemItemBean.ResultBean.ListBean.CouponBean>object=new ArrayList<>();
    private String title;
    private String stringUrl1;
    private String stringUrl2;
    private String stringUrl3;
    private String stringUrl4;
    private String stringUrl5;
    private ItemItemBean mBean;
    private int page=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_two_two);
        context=getApplicationContext();
        title = getIntent().getStringExtra("title");
        stringUrl2=String.format(URLConstant.ITEMITEM_URL);
        stringUrl3=String.format(URLConstant.ITEMTWO_BEAN,"coupon/index?_did=00000000-7bb3-da3d-ffff-ffff99d603a9&_app=xiaoshijie&_atype=android&_network=2&_networkType=0&_t=1482301908&_swidth=720&_imei=352284044635872&_manufacture=Meizu&_model=GT-S6358&_osver=4.4.2&_tn=0&_st=0&versionName=1.7.4.0&_channel=wandoujia&_av=174&id=3&tr=504723868706279424_0&fr=c010x3-01&pid=mm_109716036_12190578_67700348&_at=9a1c0bcd31f20b7f24a1691c425564a7&");
        initview();
    }

    private void initview() {
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.xrecycler_itemtwo);
        myttadapter=new MyTwoTwoAdapter(this,object);
        recyclerView.setAdapter(myttadapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2,OrientationHelper.VERTICAL,false));
        presentertwotwo = new PresenterTwoTwo(this);



        textViewcc = (TextView) findViewById(R.id.text_cc);
        textViewfh = (ImageView) findViewById(R.id.image_fanhui);
        textViewfh.setOnClickListener(this);
        textViewcc.setText(title);
       /* recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                page+=1;
                if (page==2){
                    String urther = String.format(stringUrl2,page);
                    presentertwotwo.LoadTwoTwoData(urther);
                }if (page==3){
                    String urther = String.format(stringUrl2,page);
                    presentertwotwo.LoadTwoTwoData(urther);
                }else {
                    String urther = String.format(stringUrl2,page);
                    presentertwotwo.LoadTwoTwoData(urther);
                }

            }
        });*/
       if (title.equals("Top热销")){
           presentertwotwo.LoadTwoTwoData(stringUrl2);
       }else {
           presentertwotwo.LoadTwoTwoData(stringUrl3);
       }
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
    public void loadData(ItemItemBean itemItemBean) {
        object.clear();
        List<ItemItemBean.ResultBean.ListBean.CouponBean>  mList=  itemItemBean.getResult().getList().getCoupon();
        object.addAll(mList);
        myttadapter.notifyDataSetChanged();

    }


}
