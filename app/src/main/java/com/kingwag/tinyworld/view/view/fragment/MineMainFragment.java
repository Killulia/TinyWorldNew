package com.kingwag.tinyworld.view.view.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
//    public static final boolean SWITCH_DATA =false;
    private DataCleanManagerUtils dataCleanManagerUtils;//清除缓存工具类
    private String cacheStr;//获取缓存大小
    private TextView tvCache;//缓存大小显示的TextView
    private ImageView ivSwitchData;
    private int count_data,count_push;//省流量模式的计数器
    private ImageView ivSwitchPush;//推送通知的开关
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
        //缓存显示的TextView实例化
        tvCache = (TextView) view.findViewById(R.id.tv_cache);
        //清除缓存的方法
        initCache();
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
                try {
                    cacheStr = dataCleanManagerUtils.getTotalCacheSize(getContext());
                    tvCache.setText(cacheStr);
                    Toast.makeText(getContext(),"缓存清除成功",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                dataCleanManagerUtils.cleanInternalCache(getContext());
            }
        });
        //省流量模式
        ivSwitchData = (ImageView) view.findViewById(R.id.iv_switch_data);
        ivSwitchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_data++;
                if(count_data%2==1)
                {
                    ivSwitchData.setImageResource(R.mipmap.switch_on);
                    //

                }
                else
                {
                    ivSwitchData.setImageResource(R.mipmap.switch_off);
                }








            }
        });

        //推送通知开关
        ivSwitchPush= (ImageView) view.findViewById(R.id.iv_switch_push);

        ivSwitchPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_push++;
                if(count_push%2==1)
                {
                    ivSwitchPush.setImageResource(R.mipmap.switch_off);
                    //弹Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    AlertDialog dialog = builder.create();
                    builder.setMessage("取消后不再接收每日最热门时尚潮流资讯，确认取消？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ivSwitchPush.setImageResource(R.mipmap.switch_on);
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();




                }
                else
                {
                    ivSwitchPush.setImageResource(R.mipmap.switch_on);
                }


            }
        });


    }

    private void initCache() {
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