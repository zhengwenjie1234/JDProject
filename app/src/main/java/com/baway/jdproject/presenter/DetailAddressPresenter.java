package com.baway.jdproject.presenter;

import com.baway.jdproject.model.DetailAddressModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.DetailAddressView;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class DetailAddressPresenter implements OnFinish {

    private DetailAddressView detailAddressView;
    private final DetailAddressModel detailAddressModel;

    public DetailAddressPresenter(DetailAddressView detailAddressView) {
        this.detailAddressView=detailAddressView;
        detailAddressModel = new DetailAddressModel();
    }

    public void relevance(String uid,String addr,String mobile,String name,String token){
        detailAddressModel.getData(this,uid,addr,mobile,name,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        detailAddressView.getAddressData(baseBean);
    }
}
