package com.baway.jdproject.presenter;

import com.baway.jdproject.model.GetAddressModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.GetAddressListView;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class GetAddressPresenter implements OnFinish {

    private GetAddressListView getAddressListView;
    private final GetAddressModel getAddressModel;

    public GetAddressPresenter(GetAddressListView getAddressListView) {
        this.getAddressListView=getAddressListView;
        getAddressModel = new GetAddressModel();
    }

    public void relevance(String uid,String token){
        getAddressModel.getAddressData(this,uid,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        getAddressListView.getAddressList(baseBean);
    }
}
