package com.baway.jdproject.presenter;

import android.util.Log;

import com.baway.jdproject.model.LoginModelImp;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.LoginView;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class LoginPresenterImp implements LoginPresenter,OnFinish{

    LoginView loginView;
    private final LoginModelImp loginModelImp;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView=loginView;
        loginModelImp = new LoginModelImp();
    }

    @Override
    public void relevance(String userName, String userPwd) {
        loginModelImp.getUser(this,userName,userPwd);
        Log.e("PPPP",userName+"         "+userPwd);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        loginView.getData(baseBean);
    }
}
