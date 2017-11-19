package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.SetMrAddressModel;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.SetMrAddressView;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class SetMrAddressPresenter implements OnFinish {

    private SetMrAddressView setMrAddressView;
    private final SetMrAddressModel setMrAddressModel;

    public SetMrAddressPresenter(SetMrAddressView setMrAddressView) {
        this.setMrAddressView=setMrAddressView;
        setMrAddressModel = new SetMrAddressModel();
    }

    public void relevance(String uid, String addrid, String status, String token){
        setMrAddressModel.getData(this,uid,addrid,status,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        setMrAddressView.setMrAddress(baseBean);
    }
}
