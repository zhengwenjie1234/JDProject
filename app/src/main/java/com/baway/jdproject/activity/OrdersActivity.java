package com.baway.jdproject.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.baway.jdproject.R;
import com.baway.jdproject.adapter.GetOrdersAdapter;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.GetOrdersBean;
import com.baway.jdproject.presenter.GetOrdersPresenter;
import com.baway.jdproject.view.GetOrdersView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class OrdersActivity extends BaseActivity implements GetOrdersView, View.OnClickListener {
    private RecyclerView mRv;
    private ImageView ivBack;

    @Override
    protected void getPresenter() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        int uids = sharedPreferences.getInt("uid", 0);
        String uid = String.valueOf(uids);
        String token = sharedPreferences.getString("token", null);
        GetOrdersPresenter getOrdersPresenter = new GetOrdersPresenter(this);
        getOrdersPresenter.relevance(uid,token);
    }

    @Override
    protected void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_orders;
    }


    @Override
    public void getOrderData(BaseBean baseBean) {
        GetOrdersBean getOrdersBean= (GetOrdersBean) baseBean;
        List<GetOrdersBean.DataBean> list = getOrdersBean.data;
        GetOrdersAdapter adapter = new GetOrdersAdapter(OrdersActivity.this, list);
        mRv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                OrdersActivity.this.finish();
                break;
        }
    }
}
