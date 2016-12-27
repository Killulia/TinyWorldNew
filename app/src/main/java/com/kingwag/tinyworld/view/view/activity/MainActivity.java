package com.kingwag.tinyworld.view.view.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.view.fragment.IndexMainFragment;
import com.kingwag.tinyworld.view.view.fragment.MineMainFragment;
import com.kingwag.tinyworld.view.view.fragment.ShoppingLoginedFragment;
import com.kingwag.tinyworld.view.view.fragment.ShoppingNoneFragment;

import cn.bmob.v3.Bmob;

/**
 * 主画面
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private FrameLayout mFrameLayout_main;
    private RadioGroup mRadioGroup_main;
    private RadioButton radioButton_index_main,radioButton_shopping_main,radioButton_mine_main;
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    private IndexMainFragment indexMainFragment;
    private ShoppingNoneFragment shoppingNoneFragment;
    private ShoppingLoginedFragment shoppingLoginedFragment;
    private MineMainFragment mineMainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "c9622a92f204094d529a3e0f62899cce");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
        initFrameLayout();
        initView();
    }

    private void initView() {
        mContext = this;
        mFrameLayout_main = (FrameLayout) findViewById(R.id.frameLayout_main);
        mRadioGroup_main = (RadioGroup) findViewById(R.id.radioGroup_main);
        radioButton_index_main = (RadioButton) findViewById(R.id.indexFragment_main);
        radioButton_shopping_main = (RadioButton) findViewById(R.id.shoppingFragment_main);
        radioButton_mine_main = (RadioButton) findViewById(R.id.mineFragment_main);
        mRadioGroup_main.check(R.id.indexFragment_main);
        mFragmentTransaction = mFragmentManager.beginTransaction();

        indexMainFragment = new IndexMainFragment();
        mFragmentTransaction.add(R.id.frameLayout_main, indexMainFragment, "indexMainFragment");
        mFragmentTransaction.show(indexMainFragment);

        shoppingNoneFragment = new ShoppingNoneFragment();
        mFragmentTransaction.add(R.id.frameLayout_main, shoppingNoneFragment, "shoppingNoneFragment");
        mFragmentTransaction.hide(shoppingNoneFragment);

        //测试用的已登录购物车界面
        shoppingLoginedFragment = new ShoppingLoginedFragment();
        mFragmentTransaction.add(R.id.frameLayout_main, shoppingLoginedFragment, "shoppingLoginedFragment");
        mFragmentTransaction.hide(shoppingLoginedFragment);

        mineMainFragment = new MineMainFragment();
        mFragmentTransaction.add(R.id.frameLayout_main, mineMainFragment, "  mineMainFragment");
        mFragmentTransaction.hide(mineMainFragment);
        mFragmentTransaction.commit();

        mRadioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.indexFragment_main:
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.show(indexMainFragment);
                        //测试暂时换成已登录
                        mFragmentTransaction.hide(shoppingLoginedFragment);
                        mFragmentTransaction.hide(mineMainFragment);
                        mFragmentTransaction.commit();

                        radioButton_index_main.setTextColor(getResources().getColor(R.color.red));
                        radioButton_mine_main.setTextColor(getResources().getColor(R.color.gray));
                        radioButton_shopping_main.setTextColor(getResources().getColor(R.color.gray));
                        break;
                    case R.id.shoppingFragment_main:
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.hide(indexMainFragment);
                        //测试暂时换成已登录
                        mFragmentTransaction.show(shoppingLoginedFragment);
                        mFragmentTransaction.hide(mineMainFragment);
                        mFragmentTransaction.commit();

                        radioButton_index_main.setTextColor(getResources().getColor(R.color.gray));
                        radioButton_mine_main.setTextColor(getResources().getColor(R.color.gray));
                        radioButton_shopping_main.setTextColor(getResources().getColor(R.color.red));
                        break;
                    case R.id.mineFragment_main:
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.hide(indexMainFragment);
                        mFragmentTransaction.hide(shoppingNoneFragment);
                        mFragmentTransaction.show(mineMainFragment);
                        mFragmentTransaction.commit();

                        radioButton_index_main.setTextColor(getResources().getColor(R.color.gray));
                        radioButton_mine_main.setTextColor(getResources().getColor(R.color.red));
                        radioButton_shopping_main.setTextColor(getResources().getColor(R.color.gray));
                        break;
                }
            }
        });
    }

    private void initFrameLayout() {
        mFragmentManager=getSupportFragmentManager();
    }
}
