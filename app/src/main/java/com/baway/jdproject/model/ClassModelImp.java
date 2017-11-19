package com.baway.jdproject.model;

import android.content.Context;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ClassBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class ClassModelImp implements ClassModel {

    private Context context;
    @Override
    public void getData(final OnFinish onFinish) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<ClassBean> observable = apiService.getHomeClass();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassBean classBean) {
                        BaseBean baseBean=classBean;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
