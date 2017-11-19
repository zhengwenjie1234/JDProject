package com.baway.jdproject.presenter;

import com.baway.jdproject.model.DeleteShopModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.DeleteShopView;

/**
 * Created by 郑文杰 on 2017/11/16.
 */

public class DeleteShopPresenter implements OnFinish {

    private DeleteShopView deleteShopView;
    private final DeleteShopModel deleteShopModel;

    public DeleteShopPresenter(DeleteShopView deleteShopView) {
        this.deleteShopView=deleteShopView;
        deleteShopModel = new DeleteShopModel();
    }

    public void relevance(String uid,String pid,String token){
        deleteShopModel.getData(this,uid,pid,token);
    }


    @Override
    public void onSuccess(BaseBean baseBean) {
        deleteShopView.getDeleteData(baseBean);
    }
}
