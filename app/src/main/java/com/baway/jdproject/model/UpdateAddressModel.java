package com.baway.jdproject.model;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.UpdateAddressBean;
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

public class UpdateAddressModel {

    public void getData(final OnFinish onFinish, String uid, String addrid, String token){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<UpdateAddressBean> observable = apiService.updateAddress(uid, addrid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateAddressBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateAddressBean updateAddressBean) {
                        BaseBean baseBean=updateAddressBean;
                        onFinish.onSuccess(baseBean);
                    }
                });

    }
}
