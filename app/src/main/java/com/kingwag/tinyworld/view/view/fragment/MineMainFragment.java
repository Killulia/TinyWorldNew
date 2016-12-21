package com.kingwag.tinyworld.view.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.utils.DataCleanManagerUtils;
import com.kingwag.tinyworld.view.view.activity.MainActivity;
import com.kingwag.tinyworld.view.view.activity.MineFragment_OpinionActivity;
import com.kingwag.tinyworld.view.view.activity.MineFragment_RegisterActivity;
import com.kingwag.tinyworld.view.view.activity.MyFragment_LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineMainFragment extends Fragment implements View.OnClickListener{

    private DataCleanManagerUtils dataCleanManagerUtils;//清除缓存工具类
    private String cacheStr;//获取缓存大小
    private TextView tvCache;//缓存大小显示的TextView


    public MineMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine_main, container, false);

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //登录按钮
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        //注册按钮
        view.findViewById(R.id.btn_register).setOnClickListener(this);
        //意见反馈
        view.findViewById(R.id.relativelayout_suggestion).setOnClickListener(this);
        //清除缓存
        view.findViewById(R.id.relativelayout_clear_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataCleanManagerUtils.clearAllCache(getContext());
                dataCleanManagerUtils.cleanInternalCache(getContext());
                try {
                    cacheStr = dataCleanManagerUtils.getTotalCacheSize(getContext());
                    tvCache = (TextView) view.findViewById(R.id.tv_cache);
                    tvCache.setText(cacheStr);
                    Toast.makeText(getContext(),"缓存清楚成功",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //显示缓存的方法
        initCache(view);
    }

    private void initCache(View view) {

        try {
            cacheStr =   dataCleanManagerUtils.getTotalCacheSize(getContext());
            tvCache.setText(cacheStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //登录按钮
            case R.id.btn_login:
                intent.setClass(getContext(), MyFragment_LoginActivity.class);
                break;
            //注册按钮跳转
            case R.id.btn_register:
                intent.setClass(getContext(), MineFragment_RegisterActivity.class);
                break;
            //意见反馈
            case R.id.relativelayout_suggestion:
                intent.setClass(getContext(), MineFragment_OpinionActivity.class);
                break;

        }
        startActivity(intent);
    }
}
