package com.baway.jdproject.presenter;

import com.baway.jdproject.model.ClassRightModelImp;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.ClassRightView;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class ClassRightPresenterImp implements ClassRightPresenter,OnFinish {

    private ClassRightView classRightView;
    private final ClassRightModelImp classRightModelImp;

    public ClassRightPresenterImp(ClassRightView classRightView) {
        this.classRightView=classRightView;
        classRightModelImp = new ClassRightModelImp();
    }

    @Override
    public void relevance(String cid) {
        classRightModelImp.getData(this,cid);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        classRightView.getData(baseBean);
    }
}
