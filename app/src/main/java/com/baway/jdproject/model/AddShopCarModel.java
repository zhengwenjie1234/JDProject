package com.baway.jdproject.model;

import com.baway.jdproject.model.bean.AddShopCarBean;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/11/7.
 */

public class AddShopCarModel {

    public void getData(final OnFinish onFinish, String uid, String pid, String token){
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<AddShopCarBean> observable = apiService.getShopCar(uid, pid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddShopCarBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddShopCarBean addShopCarBean) {
                        BaseBean baseBean=addShopCarBean;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
