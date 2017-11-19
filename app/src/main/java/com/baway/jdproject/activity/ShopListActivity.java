package com.baway.jdproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.adapter.ShopListAdapter;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ShopListBean;
import com.baway.jdproject.presenter.ShopListPresenterImp;
import com.baway.jdproject.view.ShopListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ShopListActivity extends AppCompatActivity implements ShopListView {

    private XRecyclerView mXrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        initView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mXrv.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String pscid = intent.getStringExtra("pscid");

        ShopListPresenterImp shopListPresenterImp = new ShopListPresenterImp(this);
        shopListPresenterImp.relevance(pscid);

    }

    @Override
    public void getData(BaseBean baseBean) {
        ShopListBean shopListBean = (ShopListBean) baseBean;
        List<ShopListBean.DataBean> data = shopListBean.data;
        ShopListAdapter shopListAdapter = new ShopListAdapter(this, data);
        mXrv.setAdapter(shopListAdapter);
        Toast.makeText(ShopListActivity.this, shopListBean.toString(), Toast.LENGTH_SHORT).show();
        Log.e("shop", shopListBean.toString());
    }

    private void initView() {
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
    }
}
