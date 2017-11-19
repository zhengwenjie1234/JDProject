package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.ShopListModelImp;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.ShopListView;

/**
 * Created by 郑文杰 on 2017/11/6.
 */

public class ShopListPresenterImp implements ShopListPresenter,OnFinish {

    private ShopListView shopListView;
    private final ShopListModelImp shopListModelImp;

    public ShopListPresenterImp(ShopListView shopListView) {
        this.shopListView=shopListView;
        shopListModelImp = new ShopListModelImp();
    }

    @Override
    public void relevance(String pscid) {
        shopListModelImp.getData(this,pscid);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        shopListView.getData(baseBean);
    }
}
