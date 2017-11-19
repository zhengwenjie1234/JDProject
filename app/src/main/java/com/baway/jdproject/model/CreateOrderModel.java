package com.baway.jdproject.model;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.CreateOrderBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class CreateOrderModel {

    public void getData(final OnFinish onFinish, String uid, String price, String token){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<CreateOrderBean> observable = apiService.createOrder(uid, price, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateOrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CreateOrderBean createOrder) {
                        BaseBean baseBean=createOrder;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
