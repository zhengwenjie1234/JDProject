package com.baway.jdproject.model;

import android.content.Context;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.HomeBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/11/1.
 */

public class HomeModelImp implements HomeModel{

    private Context context;
    @Override
    public void getData(final OnFinish onFinish) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<HomeBean> observable = apiService.getHomeData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        BaseBean baseBean=homeBean;
                        onFinish.onSuccess(baseBean);
                    }
                });

    }
}
