package com.kingwag.tinyworld.view.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.CollectAdapter;
import com.kingwag.tinyworld.view.bean.Goods;
import com.kingwag.tinyworld.view.helper.GoodsManager;
import com.kingwag.tinyworld.view.custom.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class MineCollect extends AppCompatActivity {
    private ListView listView;
    private List<Goods> goods;
    private GoodsManager manager;
    private CollectAdapter adapter ;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collect);
        mContext = this;
        manager = new GoodsManager(mContext);
        initData();
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.collect_list);
        adapter = new CollectAdapter(mContext, goods);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(mContext).
                        setMessage("是否取消收藏").
                        setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Goods good = goods.get(position);
                                try {
                                    goods.remove(position);
                                    manager.delete(Integer.valueOf(good.getId()),2);
                                    Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                                    adapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).
                        setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create().show();
                return true;
            }
        });
    }

    private void initData() {
        goods = new ArrayList<>();
        try {
            goods = manager.quearyAll(2);
            Log.d("MineCollect", "size:"+goods.size());


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
