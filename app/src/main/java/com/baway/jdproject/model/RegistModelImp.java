package com.baway.jdproject.model;

import android.content.Context;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.RegistBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class RegistModelImp implements RegistModel{

    private Context context;
    @Override
    public void setUser(final OnFinish onFinish, String userName, String userPwd) {

        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<RegistBean> observable = apiService.userRegist(userName, userPwd);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegistBean registBean) {
                        BaseBean baseBean=registBean;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }


}
