package com.kingwag.tinyworld.view.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexMainFragment extends Fragment {
    private Context context=getContext();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list=new ArrayList<>();
    private String[]TABS;
    private LinearLayout linearLayout;
    private JinrituijianFragment jinrituijianFragment;
    private FuzhuangFragment fuzhuangFragment;
    private MeizhuangFragment meizhuangFragment;
    private BaihuoFragment baihuoFragment;
    private ShoujiFragment shoujiFragment;
    private MuyingFragment muyingFragment;
    private QitaFragment qitaFragment;
    private MyViewPagerAdapter myViewPagerAdapter;

    public IndexMainFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_main, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout= (TabLayout) view.findViewById(R.id.tablayoyt_index);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager_index);
        linearLayout= (LinearLayout) view.findViewById(R.id.linearlayout_fragment_index);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TABS=context.getResources().getStringArray(R.array.tab_list);
        initData();
        initView();
    }

    private void initView() {
        myViewPagerAdapter=new MyViewPagerAdapter(getChildFragmentManager(),TABS,list);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setOffscreenPageLimit(7);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initData() {
        jinrituijianFragment=new JinrituijianFragment();
        fuzhuangFragment=new FuzhuangFragment();
        meizhuangFragment=new MeizhuangFragment();
        baihuoFragment =new BaihuoFragment();
        shoujiFragment=new ShoujiFragment();
        muyingFragment=new MuyingFragment();
        qitaFragment=new QitaFragment();

        list.add(jinrituijianFragment);
        list.add(fuzhuangFragment);
        list.add(meizhuangFragment);
        list.add(baihuoFragment);
        list.add(shoujiFragment);
        list.add(muyingFragment);
        list.add(qitaFragment);

    }

}
