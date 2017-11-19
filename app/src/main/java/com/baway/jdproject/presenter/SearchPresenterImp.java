package com.baway.jdproject.presenter;

import com.baway.jdproject.model.OnFinish;
import com.baway.jdproject.model.SearchModelImp;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.view.SearchView;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class SearchPresenterImp implements SearchPresenter,OnFinish {

    private SearchView searchView;
    private final SearchModelImp searchModelImp;


    public SearchPresenterImp(SearchView searchView) {
        this.searchView=searchView;
        searchModelImp = new SearchModelImp();

    }

    @Override
    public void relevance(String keyWord,String page,String source) {
        searchModelImp.getData(keyWord,page,source,this);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        searchView.getSearchData(baseBean);
    }
}
