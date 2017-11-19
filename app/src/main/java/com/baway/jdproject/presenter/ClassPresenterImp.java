package com.baway.jdproject.presenter;

import com.baway.jdproject.model.ClassModelImp;
import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.ClassView;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class ClassPresenterImp implements ClassPresenter,OnFinish {

    private ClassView classView;
    private final ClassModelImp classModelImp;

    public ClassPresenterImp(ClassView classView) {
        this.classView=classView;
        classModelImp = new ClassModelImp();
    }

    @Override
    public void relevance() {
        classModelImp.getData(this);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        classView.getClassData(baseBean);
    }
}
