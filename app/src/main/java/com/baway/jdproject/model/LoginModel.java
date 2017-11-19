package com.baway.jdproject.model;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public interface LoginModel {

    void getUser(OnFinish onFinish,String UserName,String UserPwd);

}
