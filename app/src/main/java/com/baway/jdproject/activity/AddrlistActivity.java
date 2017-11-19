package com.baway.jdproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baway.jdproject.R;
import com.baway.jdproject.adapter.AddressAdapter;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.GetAddressBean;
import com.baway.jdproject.presenter.GetAddressPresenter;
import com.baway.jdproject.view.GetAddressListView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/15.
 */

public class AddrlistActivity extends AppCompatActivity implements View.OnClickListener,GetAddressListView {

    private ImageView mIvBack;
    private RelativeLayout mRl;
    /**
     * ＋新建地址
     */
    private Button mBtAdd;
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrlist);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        int uids = sharedPreferences.getInt("uid", 0);
        String uid = String.valueOf(uids);
        GetAddressPresenter getAddressPresenter = new GetAddressPresenter(this);
        getAddressPresenter.relevance(uid, token);
        Log.i("22222","88888888888");

    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mIvBack.setOnClickListener(this);
        mRl = (RelativeLayout) findViewById(R.id.rl);
        mBtAdd = (Button) findViewById(R.id.btAdd);
        mBtAdd.setOnClickListener(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
                Intent intent = new Intent(AddrlistActivity.this, DetailAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.ivBack:
                Intent intent1 = new Intent(AddrlistActivity.this, CreateOrderActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    @Override
    public void getAddressList(BaseBean baseBean) {
        GetAddressBean getAddressBean = (GetAddressBean) baseBean;
        List<GetAddressBean.DataBean> data = getAddressBean.data;
        AddressAdapter addressAdapter = new AddressAdapter(AddrlistActivity.this, data);
        mRv.setAdapter(addressAdapter);
    }
}
