package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.AddShopCarModel;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.AddShopCarView;

/**
 * Created by 郑文杰 on 2017/11/7.
 */

public class AddShopCarPresenter implements OnFinish {

    private AddShopCarView addShopCarView;
    private final AddShopCarModel shopCarModel;

    public AddShopCarPresenter(AddShopCarView addShopCarView) {
        this.addShopCarView=addShopCarView;
        shopCarModel = new AddShopCarModel();
    }

    public void relevance(String uid, String pid, String token){
        shopCarModel.getData(this,uid,pid,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        addShopCarView.getAddShop(baseBean);
    }
}
