package com.baway.jdproject.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.presenter.RegistPresenterImp;
import com.baway.jdproject.view.RegistView;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener,RegistView {

    private EditText etName;
    private EditText etPwd;
    private Button btRegist;

    @Override
    protected void getPresenter() {

    }

    @Override
    protected void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btRegist = (Button) findViewById(R.id.btRegist);
        btRegist.setOnClickListener(this);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btRegist:
                String userName = etName.getText().toString().trim();
                String userPwd = etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPwd)){
                    Toast.makeText(RegistActivity.this,"用户名和密码不为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("vvvv",userName+"         "+userPwd);
                RegistPresenterImp registPresenterImp = new RegistPresenterImp(this);
                registPresenterImp.relevance(userName,userPwd);
                break;
        }
    }

    @Override
    public void getData(BaseBean baseBean) {
        String code = baseBean.getCode();
        if (code.equals("0")){
            Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        }
    }
}
