package com.baway.jdproject.presenter;

import android.util.Log;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.RegistModelImp;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.RegistView;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class RegistPresenterImp implements RegistPresenter,OnFinish {

    private RegistView registView;
    private final RegistModelImp registModelImp;

    public RegistPresenterImp(RegistView registView) {
        this.registView=registView;
        registModelImp = new RegistModelImp();
    }


    @Override
    public void relevance(String userName, String userPwd) {
        registModelImp.setUser(this,userName,userPwd);
        Log.e("ppp",userName+"    "+userPwd);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        registView.getData(baseBean);
    }
}
