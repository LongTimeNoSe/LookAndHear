package com.example.administrator.lookandhear;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.administrator.lookandhear.adapter.ViewPagerAdapter;
import com.example.administrator.lookandhear.base.BaseActivity;
import com.example.administrator.lookandhear.base.BasePresenter;
import com.example.administrator.lookandhear.ui.fragments.ClassificPageFragment;
import com.example.administrator.lookandhear.ui.fragments.HomePageFragment;
import com.example.administrator.lookandhear.ui.fragments.MinePageFragment;
import com.example.administrator.lookandhear.ui.fragments.RankingPageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.content_viewPager)
    ViewPager contentViewPager;

    private List<Fragment> mFragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mFragmentList = new ArrayList<Fragment>();

        mFragmentList.add(new HomePageFragment());
        mFragmentList.add(new RankingPageFragment());
        mFragmentList.add(new ClassificPageFragment());
        mFragmentList.add(new MinePageFragment());

        contentViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList));
        tabLayout.setupWithViewPager(contentViewPager);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected String setTitle() {
        return "Sound Of Music";
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    @Override
    public void requestData() {
        super.requestData();
    }
}
