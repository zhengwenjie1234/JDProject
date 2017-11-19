package com.baway.jdproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.AddAddressBean;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.presenter.DetailAddressPresenter;
import com.baway.jdproject.view.DetailAddressView;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class DetailAddressActivity extends BaseActivity implements View.OnClickListener,DetailAddressView {
    private ImageView mIvBack;
    /**
     * 收货人
     */
    private EditText mEtName;
    /**
     * 手机号码
     */
    private EditText mEtTel;
    /**
     * 详细地址
     */
    private EditText mEtDadress;
    /**
     * 保存并使用
     */
    private Button mBtKeep;
    private AddAddressBean addressBean;

    @Override
    protected void getPresenter() {

    }

    @Override
    protected void initView() {

        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mIvBack.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.etName);
        mEtTel = (EditText) findViewById(R.id.etTel);
        mEtDadress = (EditText) findViewById(R.id.etDadress);
        mBtKeep = (Button) findViewById(R.id.bt_keep);
        mBtKeep.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_detailadress;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                Intent intent = new Intent(DetailAddressActivity.this, AddrlistActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_keep:
                String name = mEtName.getText().toString().trim();
                String address = mEtDadress.getText().toString().trim();
                String tel = mEtTel.getText().toString().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)||TextUtils.isEmpty(tel)){
                    Toast.makeText(DetailAddressActivity.this,"地址不为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                int uids = sharedPreferences.getInt("uid", 0);
                String uid = String.valueOf(uids);
                String token = sharedPreferences.getString("token", null);
                DetailAddressPresenter detailAddressPresenter = new DetailAddressPresenter(this);
                detailAddressPresenter.relevance(uid,address,tel,name,token);
                Toast.makeText(DetailAddressActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public void getAddressData(BaseBean baseBean) {
        addressBean = (AddAddressBean) baseBean;
    }

}
