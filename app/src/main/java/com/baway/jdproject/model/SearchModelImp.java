package com.baway.jdproject.model;

import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.SearchBean;
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

public class SearchModelImp implements SearchModel {


    @Override
    public void getData(String keyWord, String page, String source, final OnFinish onFinish) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.BASEURL, ApiService.class);
        Observable<SearchBean> observable = apiService.getSerarchData(keyWord, page, source);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        BaseBean baseBean=searchBean;
                        onFinish.onSuccess(baseBean);
                    }
                });
    }
}
