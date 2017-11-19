package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.UpdateAddressModel;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.UpdateAddressView;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class UpdateAddressPresenter implements OnFinish {

    private UpdateAddressView updateAddressView;
    private final UpdateAddressModel updateAddressModel;

    public UpdateAddressPresenter(UpdateAddressView updateAddressView) {
        this.updateAddressView=updateAddressView;
        updateAddressModel = new UpdateAddressModel();
    }

    public void relevance(String uid,String addrid,String token){
        updateAddressModel.getData(this,uid,addrid,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        updateAddressView.getUpdateDate(baseBean);
    }
}
