package com.kingwag.tinyworld.view.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.fragment.IndexMainFragment;
import com.kingwag.tinyworld.view.fragment.MineMainFragment;
import com.kingwag.tinyworld.view.fragment.ShoppingMainFragment;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private FrameLayout mFrameLayout_main;
    private RadioGroup mRadioGroup_main;
    private RadioButton radioButton_index_main,radioButton_shopping_main,radioButton_mine_main;
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    private IndexMainFragment indexMainFragment;
    private ShoppingMainFragment shoppingMainFragment;
    private MineMainFragment mineMainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        shoppingMainFragment = new ShoppingMainFragment();
        mFragmentTransaction.add(R.id.frameLayout_main, shoppingMainFragment, "shoppingMainFragment");
        mFragmentTransaction.hide(shoppingMainFragment);

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
                        mFragmentTransaction.hide(shoppingMainFragment);
                        mFragmentTransaction.hide(mineMainFragment);
                        mFragmentTransaction.commit();
                        break;
                    case R.id.shoppingFragment_main:
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.hide(indexMainFragment);
                        mFragmentTransaction.show(shoppingMainFragment);
                        mFragmentTransaction.hide(mineMainFragment);
                        mFragmentTransaction.commit();
                        break;
                    case R.id.mineFragment_main:
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.hide(indexMainFragment);
                        mFragmentTransaction.hide(shoppingMainFragment);
                        mFragmentTransaction.show(mineMainFragment);
                        mFragmentTransaction.commit();
                        break;
                }
            }
        });
    }

    private void initFrameLayout() {
        mFragmentManager=getSupportFragmentManager();
    }
}
