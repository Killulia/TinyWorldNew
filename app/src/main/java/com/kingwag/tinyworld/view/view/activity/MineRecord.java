package com.kingwag.tinyworld.view.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.CollectAdapter;
import com.kingwag.tinyworld.view.bean.Goods;
import com.kingwag.tinyworld.view.helper.GoodsManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwag on 2016/12/30.
 * 浏览记录
 */

public class MineRecord extends AppCompatActivity{
    private ListView listView;
    private List<Goods> goods;
    private GoodsManager manager;
    private CollectAdapter adapter ;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_record);
        mContext = this;
        manager = new GoodsManager(mContext);
        initData();
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.record_list);
        adapter = new CollectAdapter(mContext, goods);
        listView.setAdapter(adapter);

    }

    private void initData() {
        goods = new ArrayList<>();
        try {
            goods = manager.quearyAll(3);
            Log.d("MineRecord", "size:"+goods.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
