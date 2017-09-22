package com.example.administrator.lookandhear.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by XuYanping on 2017/9/22.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "首页";
            case 1:
                return "排行";
            case 2:
                return "分类";
            case 3:
                return "我的";
        }
        return null;
    }
}
