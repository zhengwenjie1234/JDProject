package com.baway.jdproject.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.adapter.SearchListAdapter;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.SearchBean;
import com.baway.jdproject.presenter.SearchPresenterImp;
import com.baway.jdproject.view.SearchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/12.
 */

public class SerarchActivity extends BaseActivity implements SearchView, View.OnClickListener {

    private TextView tvSearch;
    private EditText etSearch;
    private XRecyclerView xrv;
    private List<SearchBean.DataBean> list;

    @Override
    protected void getPresenter() {

    }

    @Override
    protected void initView() {
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        tvSearch.setOnClickListener(this);
        xrv = (XRecyclerView) findViewById(R.id.xrv);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void getSearchData(BaseBean baseBean) {
        SearchBean searchBean = (SearchBean) baseBean;
        list = searchBean.data;
        xrv.setLayoutManager(new LinearLayoutManager(this));
        SearchListAdapter adapter = new SearchListAdapter(this, list);
        xrv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSearch:
                String keywords = etSearch.getText().toString().trim();
                //实例化SearchPresenterImp
                SearchPresenterImp searchPresenterImp = new SearchPresenterImp(this);
                searchPresenterImp.relevance(keywords,"1","android");

                break;
        }
    }
}
