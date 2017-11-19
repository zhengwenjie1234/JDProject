package com.baway.jdproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //加载布局
        setContentView(initLayout());
        //初始化控件
        initView();
        //创建p
        getPresenter();

    }

    protected abstract void getPresenter();

    protected abstract void initView();

    protected abstract int initLayout();


}
