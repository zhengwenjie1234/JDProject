package com.baway.jdproject.model;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.GetMrAddressBean;
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

public class GetMrAddressModel  {

    public void getData(final OnFinish onFinish, String uid, String token){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<GetMrAddressBean> observable = apiService.getMrAddress(uid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMrAddressBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetMrAddressBean getMrAddress) {
                        BaseBean baseBean=getMrAddress;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
