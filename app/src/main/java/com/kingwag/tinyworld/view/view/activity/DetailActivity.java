package com.kingwag.tinyworld.view.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.Goods;
import com.kingwag.tinyworld.view.helper.GoodsManager;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context=this;
    private ImageView imageView,bigimageView;
    private TextView textViewPrice,textViewOprice,textViewTitle,youhuijiaa;
    private String title,price,oprice,image,bigimage,youhuijia;
    //点击的控件
    private ImageView image_back,image_fenxiang;
    private TextView textview_jiarugouwuche,textview_lijigoumai;
    private GoodsManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //holder.textView3.getPaint().setAntiAlias(true);
       // holder.textView3.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        manager = new GoodsManager(context);
        initData();
        initView();
    }

    private void initData() {
        Intent inient=getIntent();
        title=inient.getStringExtra("title");
        image=inient.getStringExtra("image");
        bigimage=inient.getStringExtra("bigimage");
        oprice=inient.getStringExtra("oprice");
        price=inient.getStringExtra("price");
        youhuijia=inient.getStringExtra("youhuijia");

    }

    private void initView() {
        //点击的控件
        image_back= (ImageView) findViewById(R.id.iv_back_detail);
        image_back.setOnClickListener(this);
        //分享
        image_fenxiang= (ImageView) findViewById(R.id.fenxiang);
        image_fenxiang.setOnClickListener(this);
        //加入购物车
        textview_jiarugouwuche= (TextView) findViewById(R.id.jiarugouwuche);
        textview_jiarugouwuche.setOnClickListener(this);









        //实例化数据的控件
        imageView= (ImageView) findViewById(R.id.iv_detail);
        bigimageView= (ImageView) findViewById(R.id.image_xiangqing);
        textViewTitle= (TextView) findViewById(R.id.tv_title);
        textViewPrice= (TextView) findViewById(R.id.tv_price);
        textViewOprice= (TextView) findViewById(R.id.tv_oprice);
        youhuijiaa= (TextView) findViewById(R.id.tv_youhuijia);


        //铺数据
        Glide.with(context).load(image).placeholder(getResources().getDrawable(R.drawable.jiazai)).into(imageView);
        Glide.with(context).load(bigimage).placeholder(getResources().getDrawable(R.drawable.jiazai)).into(bigimageView);
        textViewTitle.setText(title);
        textViewPrice.setText(price);
        textViewOprice.setText(oprice);
        textViewOprice.getPaint().setAntiAlias(true);
        textViewOprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        youhuijiaa.setText(youhuijia);

    }

    @Override
    public void onClick(View v) {
        oprice=oprice.substring(1);
        price=price.substring(1);
        switch (v.getId()){
            case R.id.iv_back_detail:
                finish();
                break;
            case R.id.fenxiang:
                break;
            case R.id.jiarugouwuche:
                Goods goods = new Goods(title, Double.valueOf(price),Double.valueOf(oprice),image);
                try {
                    Log.d("tag","gone");
                    manager.insert(goods);

                    Toast.makeText(context, "加入购物车成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


        }
    }
}
