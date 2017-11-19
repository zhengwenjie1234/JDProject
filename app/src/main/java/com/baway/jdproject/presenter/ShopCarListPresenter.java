package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.ShopCarListModel;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.ShopCarListView;

/**
 * Created by 郑文杰 on 2017/11/7.
 */

public class ShopCarListPresenter implements OnFinish {

    private ShopCarListView shopCarListView;
    private final ShopCarListModel shopCarListModel;
    public ShopCarListPresenter(ShopCarListView shopCarListView) {
        this.shopCarListView=shopCarListView;
        shopCarListModel = new ShopCarListModel();
    }

    public void relevance(String uid,String token){
        shopCarListModel.getData(this,uid,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        shopCarListView.getData(baseBean);
    }
}
