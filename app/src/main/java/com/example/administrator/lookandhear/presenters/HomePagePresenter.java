package com.example.administrator.lookandhear.presenters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.lookandhear.base.BasePresenter;
import com.example.administrator.lookandhear.view.HomePageView;

/**
 * Created by XuYanping on 2017/9/27.
 */

public class HomePagePresenter extends BasePresenter<HomePageView> {


    private Context mContext;

    public HomePagePresenter(Context mContext) {
        this.mContext = mContext;
    }

    private HomePageView mHomePageView;
    private LinearLayoutManager mLinearLayoutManager;
}
