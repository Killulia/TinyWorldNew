package com.kingwag.tinyworld.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private String[]TABS;
    private List<Fragment>list;
    public MyViewPagerAdapter(FragmentManager fm, String[]TABS, List<Fragment>list) {
        super(fm);
        this.TABS=TABS;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABS[position];
    }
}
