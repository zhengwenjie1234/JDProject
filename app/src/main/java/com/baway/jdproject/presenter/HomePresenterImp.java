package com.baway.jdproject.presenter;

import android.util.Log;

import com.baway.jdproject.model.HomeModelImp;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.HomeView;

/**
 * Created by 郑文杰 on 2017/11/1.
 */

public class HomePresenterImp implements HomePresenter,OnFinish {

    private HomeView homeView;
    private final HomeModelImp homeModelImp;

    public HomePresenterImp(HomeView homeView) {
        this.homeView=homeView;
        homeModelImp = new HomeModelImp();
    }

    @Override
    public void relevance() {
        //p关联m
        homeModelImp.getData(this);
    }


    @Override
    public void onSuccess(BaseBean baseBean) {
        //p关联v
        homeView.getData(baseBean);
        Log.e("pppppp------------",baseBean.toString());
    }
}
