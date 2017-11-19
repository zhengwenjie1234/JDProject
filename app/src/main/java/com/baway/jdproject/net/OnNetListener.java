package com.baway.jdproject.net;

import com.baway.jdproject.model.bean.BaseBean;

import java.io.IOException;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public interface OnNetListener {

    public void onSuccess(BaseBean baseBean) throws IOException;
    public void onError(IOException e);
}
