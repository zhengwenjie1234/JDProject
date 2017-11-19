package com.baway.jdproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.LoginBean;
import com.baway.jdproject.presenter.LoginPresenterImp;
import com.baway.jdproject.view.LoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {

    private EditText etName;
    private EditText etPwd;
    private Button btLogin;
    private TextView tvRegist;
    private String userName;
    private String userPwd;
    private LoginBean loginBean;
    private ImageView login_qq;
    private ImageView login_wx;
    private Button btBackLogin;
    private ImageView ivBack;

    @Override
    protected void getPresenter() {

    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvRegist = (TextView) findViewById(R.id.regist);
        tvRegist.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        login_qq = (ImageView) findViewById(R.id.login_QQ);
        login_qq.setOnClickListener(this);
        login_wx = (ImageView) findViewById(R.id.login_WX);
        login_wx.setOnClickListener(this);
        btBackLogin = (Button) findViewById(R.id.btBackLogin);
        btBackLogin.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;

            case R.id.btLogin:
                userName = etName.getText().toString().trim();
                userPwd = etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                    Toast.makeText(LoginActivity.this, "用户名和密码不为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginPresenterImp loginPresenterImp = new LoginPresenterImp(this);
                loginPresenterImp.relevance(userName, userPwd);

                break;
            case R.id.login_QQ:
                auth();
                break;
            case R.id.login_WX:
                break;
            case R.id.btBackLogin:
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                break;
        }
    }

    /**
     * 授权（第三方登录）
     */
    private void auth() {
        //伪授权，只是为了得到用户信息(一般用这个)
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, uMAuthListener);
        //取消授权
       // UMShareAPI.get(this).deleteOauth(this,SHARE_MEDIA.QQ,uMAuthListener);

        //也是进行用户授权
        //UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, uMAuthListener);

    }

    private UMAuthListener uMAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(getApplicationContext(), "授权开始", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data != null && data.size() > 0) {
                String temp = "";
                for (String key : data.keySet()) {
                    temp = temp + key + " : " + data.get(key) + "\n";
                }
                Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize onError", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize onCancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void getData(BaseBean baseBean) {
        String code = baseBean.getCode();
        if (code.equals("0")) {
            loginBean = (LoginBean) baseBean;
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            int uid = loginBean.data.uid;
            String token = loginBean.data.token;
            Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("uid", uid);
            editor.putString("token", token);
            editor.commit();
            finish();
        }

    }


}
