package com.baway.jdproject.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.adapter.ConfirmShopAdapter;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.CreateOrderBean;
import com.baway.jdproject.model.bean.GetMrAddressBean;
import com.baway.jdproject.model.bean.XinBean;
import com.baway.jdproject.presenter.CreateOrderPresenter;
import com.baway.jdproject.presenter.GetMrAddressPresenter;
import com.baway.jdproject.view.CreateOrderView;
import com.baway.jdproject.view.GetMrAddressView;

import java.util.ArrayList;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class CreateOrderActivity extends BaseActivity implements View.OnClickListener,CreateOrderView,GetMrAddressView {
    private ImageView mIvBack;
    /**
     * 收货地址
     */
    private TextView mAddress;
    /**
     * 收货地址
     */
    private TextView mTel;
    /**
     * 详细地址
     */
    private TextView mTvDetailAddress;
    private LinearLayout mToAddress;
    private LinearLayout xiadan;
    private TextView tvPrice;
    private String uid;
    private String token;
    private String totalMoney;
    private RecyclerView rv;
    @Override
    protected void getPresenter() {
        GetMrAddressPresenter getMrAddressPresenter=new GetMrAddressPresenter(this);
        getMrAddressPresenter.relevance(uid,token);
    }

    @Override
    protected void initView() {

        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mIvBack.setOnClickListener(this);
        mAddress = (TextView) findViewById(R.id.address);
        mTel = (TextView) findViewById(R.id.tel);
        mTvDetailAddress = (TextView) findViewById(R.id.tvDetailAddress);
        mToAddress = (LinearLayout) findViewById(R.id.to_address);
        mToAddress.setOnClickListener(this);
        xiadan = (LinearLayout) findViewById(R.id.xiadan);
        xiadan.setOnClickListener(this);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        totalMoney = intent.getStringExtra("totalMoney");
        tvPrice.setText(totalMoney);
        ArrayList<XinBean> list= (ArrayList<XinBean>) intent.getSerializableExtra("list");
        SharedPreferences sharedPreferences =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        Toast.makeText(CreateOrderActivity.this, token,Toast.LENGTH_SHORT).show();
        int uids = sharedPreferences.getInt("uid", 0);
        uid = String.valueOf(uids);

        for (int i = 0; i <list.size() ; i++) {
            String img = list.get(i).getImg();
            String price = list.get(i).getPrice();
            String title = list.get(i).getTitle();
            Log.e("img",img);
        }
        ConfirmShopAdapter confirmShopAdapter = new ConfirmShopAdapter(list, this);
        rv.setAdapter(confirmShopAdapter);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_address:
                Intent intent = new Intent(CreateOrderActivity.this, AddrlistActivity.class);
                startActivity(intent);
                break;
            case R.id.ivBack:
                CreateOrderActivity.this.finish();
                break;
            case R.id.xiadan:
                CreateOrderPresenter createOrderPresenter = new CreateOrderPresenter(this);
                createOrderPresenter.relevance(uid, totalMoney, token);
                Toast.makeText(CreateOrderActivity.this, "订单创建成功", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(CreateOrderActivity.this, PayMethodActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void getOrderInfo(BaseBean baseBean) {
        CreateOrderBean createOrderBean= (CreateOrderBean) baseBean;
    }

    @Override
    public void getMrAddress(BaseBean baseBean) {
        GetMrAddressBean getMrAddressBean= (GetMrAddressBean) baseBean;
        GetMrAddressBean.DataBean data = getMrAddressBean.data;
        String addr = data.addr;
        long mobiles = data.mobile;
        String mobile = String.valueOf(mobiles);
        mAddress.setText(addr);
        mTel.setText(mobile);
        mTvDetailAddress.setText(addr);
        String msg = getMrAddressBean.msg;
        Toast.makeText(CreateOrderActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
