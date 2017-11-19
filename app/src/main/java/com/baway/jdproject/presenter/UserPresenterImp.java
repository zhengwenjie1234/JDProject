package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.UserModelImp;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.UserView;

/**
 * Created by 郑文杰 on 2017/11/2.
 */

public class UserPresenterImp implements UserPresenter, OnFinish {

    private UserView userView;
    private final UserModelImp userModelImp;

    public UserPresenterImp(UserView userView) {
        this.userView = userView;
        userModelImp = new UserModelImp();
    }

    @Override
    public void relevance(String uid,String token) {
        //p关联m层
        userModelImp.getData(this,uid,token);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        //p关联v层
        userView.getData(baseBean);
    }
}
