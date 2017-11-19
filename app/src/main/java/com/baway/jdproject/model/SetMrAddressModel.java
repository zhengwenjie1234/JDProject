package com.baway.jdproject.model;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.SetMrAddressBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class SetMrAddressModel {

    public void getData(final OnFinish onFinish, String uid, String addrid, String status, String token){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<SetMrAddressBean> observable = apiService.setMrAddress(uid, addrid, status, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SetMrAddressBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SetMrAddressBean setMrAddressBean) {
                        BaseBean baseBean=setMrAddressBean;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
