package com.baway.jdproject.presenter;

import com.baway.jdproject.model.GetMrAddressModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.GetMrAddressView;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class GetMrAddressPresenter implements OnFinish{

    private GetMrAddressView getMrAddressView;
    private final GetMrAddressModel getMrAddressModel;

    public GetMrAddressPresenter(GetMrAddressView getMrAddressView) {
        this.getMrAddressView=getMrAddressView;
        getMrAddressModel = new GetMrAddressModel();
    }

    public void relevance(String uid,String token){
        getMrAddressModel.getData(this,uid,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        getMrAddressView.getMrAddress(baseBean);
    }
}
