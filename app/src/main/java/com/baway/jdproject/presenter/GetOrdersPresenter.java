package com.baway.jdproject.presenter;

import com.baway.jdproject.model.GetOrdersModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.GetOrdersView;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class GetOrdersPresenter implements OnFinish {

    private GetOrdersView getOrdersView;
    private final GetOrdersModel getOrdersModel;

    public GetOrdersPresenter(GetOrdersView getOrdersView) {
        this.getOrdersView = getOrdersView;
        getOrdersModel = new GetOrdersModel();
    }

    public void relevance(String uid, String token) {
        getOrdersModel.getOrdersData(this, uid, token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        getOrdersView.getOrderData(baseBean);
    }
}
