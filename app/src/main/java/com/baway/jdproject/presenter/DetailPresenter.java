package com.baway.jdproject.presenter;

import com.baway.jdproject.model.DetailModel;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.DetailView;

/**
 * Created by 郑文杰 on 2017/11/7.
 */

public class DetailPresenter implements OnFinish {

    private DetailView detailView;
    private final DetailModel detailModel;

    public DetailPresenter(DetailView detailView) {
        this.detailView=detailView;
        detailModel = new DetailModel();

    }

    public void relevance(String pid){
        detailModel.getData(this,pid);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        detailView.getData(baseBean);
    }
}
