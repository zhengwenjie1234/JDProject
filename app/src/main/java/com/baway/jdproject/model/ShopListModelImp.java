package com.baway.jdproject.model;

import android.content.Context;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ShopListBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.net.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 郑文杰 on 2017/11/6.
 */

public class ShopListModelImp implements ShopListModel {
    private Context context;
    @Override
    public void getData(final OnFinish onFinish, String pscid) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<ShopListBean> observable = apiService.getShopList(pscid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShopListBean shopListBean) {
                        BaseBean baseBean=shopListBean;
                        onFinish.onSuccess(baseBean);
                    }
                });

    }
}
