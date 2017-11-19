package com.baway.jdproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baway.jdproject.R;
import com.baway.jdproject.fragment.ClassFragment;
import com.baway.jdproject.fragment.FindFragment;
import com.baway.jdproject.fragment.HomeFragment;
import com.baway.jdproject.fragment.MySelfFragment;
import com.baway.jdproject.fragment.ShopCartFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton mRbHome;
    private RadioButton mRbClass;
    private RadioButton mRbFind;
    private RadioButton mRbShopCart;
    private RadioButton mRbMySelf;
    private RadioGroup mRg;
    private FrameLayout mFl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        //System.out.print(1/0);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl, new HomeFragment());
        fragmentTransaction.commit();
    }

    private void initView() {

        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbHome.setOnClickListener(this);
        mRbClass = (RadioButton) findViewById(R.id.rb_class);
        mRbClass.setOnClickListener(this);
        mRbFind = (RadioButton) findViewById(R.id.rb_find);
        mRbFind.setOnClickListener(this);
        mRbShopCart = (RadioButton) findViewById(R.id.rb_shopCart);
        mRbShopCart.setOnClickListener(this);
        mRbMySelf = (RadioButton) findViewById(R.id.rb_mySelf);
        mRbMySelf.setOnClickListener(this);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFl = (FrameLayout) findViewById(R.id.fl);


    }


    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()) {
            case R.id.rb_home:
                mRbHome.setBackgroundResource(R.mipmap.ac1);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fl, new HomeFragment());
                fragmentTransaction.commit();

                break;
            case R.id.rb_class:
                mRbClass.setBackgroundResource(R.mipmap.abx);
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.fl, new ClassFragment());
                fragmentTransaction2.commit();
                break;
            case R.id.rb_find:
                mRbFind.setBackgroundResource(R.mipmap.abz);
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.fl, new FindFragment());
                fragmentTransaction3.commit();
                break;
            case R.id.rb_shopCart:
                mRbShopCart.setBackgroundResource(R.mipmap.abv);
                FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.fl, new ShopCartFragment());
                fragmentTransaction4.commit();
                break;
            case R.id.rb_mySelf:
                mRbMySelf.setBackgroundResource(R.mipmap.ac3);
                FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.fl, new MySelfFragment());
                fragmentTransaction5.commit();
                break;
        }
    }

    public void reset() {
        mRbHome.setBackgroundResource(R.mipmap.ac0);
        mRbClass.setBackgroundResource(R.mipmap.abw);
        mRbFind.setBackgroundResource(R.mipmap.aby);
        mRbShopCart.setBackgroundResource(R.mipmap.abu);
        mRbMySelf.setBackgroundResource(R.mipmap.ac2);
    }
}
