package com.baway.jdproject.presenter;

import com.baway.jdproject.model.CreateOrderModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.CreateOrderView;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class CreateOrderPresenter implements OnFinish{

    private CreateOrderView createOrderView;
    private final CreateOrderModel createOrderModel;

    public CreateOrderPresenter(CreateOrderView createOrderView) {
        this.createOrderView=createOrderView;
        createOrderModel = new CreateOrderModel();
    }
    public void relevance(String uid,String price,String token){
        createOrderModel.getData(this,uid,price,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        createOrderView.getOrderInfo(baseBean);
    }
}
