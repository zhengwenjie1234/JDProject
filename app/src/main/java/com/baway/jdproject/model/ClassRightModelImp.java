package com.baway.jdproject.model;

import android.content.Context;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ClassRightBean;
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

public class ClassRightModelImp implements ClassRightModel {
    private Context context;
    @Override
    public void getData(final OnFinish onFinish, String cid) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<ClassRightBean> observable = apiService.getClassRight(cid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassRightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassRightBean classRightBean) {
                        BaseBean baseBean=classRightBean;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
