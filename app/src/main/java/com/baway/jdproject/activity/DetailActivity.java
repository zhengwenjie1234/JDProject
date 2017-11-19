package com.baway.jdproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.baway.jdproject.R;
import com.baway.jdproject.fragment.DetailFragment;
import com.baway.jdproject.fragment.EvaluateFragment;
import com.baway.jdproject.fragment.ProductFragment;
import com.baway.jdproject.model.bean.AddShopCarBean;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.DetailBean;
import com.baway.jdproject.presenter.AddShopCarPresenter;
import com.baway.jdproject.presenter.DetailPresenter;
import com.baway.jdproject.util.ShareUtil;
import com.baway.jdproject.view.AddShopCarView;
import com.baway.jdproject.view.DetailView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, AddShopCarView, DetailView {

    private LinearLayout mLlGys;
    private LinearLayout mLlDp;
    private LinearLayout mLlGz;
    private LinearLayout mLlGwc;
    /**
     * 加入购物车
     */
    private TextView mAddShop;
    private List<String> list = new ArrayList<>();
    private PagerSlidingTabStrip pst;
    private ViewPager vp;
    private AddShopCarBean addBean;
    private String uid;
    private String pid;
    private ImageView mIvShare;
    private ShareUtil shareUtil;
    private DetailBean detailBean;
    private RelativeLayout rl;
    private String token;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();

        //获取pid
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        Log.e("xxxx  pid", pid);


        //详情页
        DetailPresenter detailPresenter = new DetailPresenter(this);
        detailPresenter.relevance(pid);

        //设置PagerSlidingTabStrip
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pst.setViewPager(vp);

        //第三方分享类ShareUtil
        shareUtil = new ShareUtil(this);


    }

    private void initData() {
        list.add("商品");
        list.add("详情");
        list.add("评价");
    }

    private void initView() {
        pst = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        vp = (ViewPager) findViewById(R.id.vp);
        mLlGys = (LinearLayout) findViewById(R.id.ll_gys);
        mLlGys.setOnClickListener(this);
        mLlDp = (LinearLayout) findViewById(R.id.ll_dp);
        mLlDp.setOnClickListener(this);
        mLlGz = (LinearLayout) findViewById(R.id.ll_gz);
        mLlGz.setOnClickListener(this);
        mLlGwc = (LinearLayout) findViewById(R.id.ll_gwc);
        mLlGwc.setOnClickListener(this);
        mAddShop = (TextView) findViewById(R.id.add_shop);
        mAddShop.setOnClickListener(this);
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvShare.setOnClickListener(this);
        rl = (RelativeLayout) findViewById(R.id.rl);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
    }

    /*private void ShareWeb(int thumb_img){
        UMImage thumb = new UMImage(this,thumb_img);
        UMWeb web = new UMWeb("https://www.jd.com/");        web.setThumb(thumb);
        web.setDescription("电商项目");        web.setTitle("京东分享");        new ShareAction(this).withMedia(web).setPlatform(SHARE_MEDIA.QZONE).setCallback(shareListener).share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        *//**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         *//*
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(DetailActivity.this,"分享开始",Toast.LENGTH_LONG).show();
        }

        *//**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         *//*
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(DetailActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        *//**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         *//*
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            Toast.makeText(DetailActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        *//**
         * @descrption 分享取消的回调
         * @param
         *//*
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_gys:
                break;
            case R.id.ll_dp:
                break;
            case R.id.ll_gz:
                break;
            case R.id.ll_gwc:
                Intent intent = new Intent(DetailActivity.this, EmptyActivity.class);
                startActivity(intent);
                break;
            case R.id.ivBack:
                Intent intent1 = new Intent(DetailActivity.this, ShopListActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.add_shop:
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                int uids = sharedPreferences.getInt("uid", 0);
                uid = String.valueOf(uids);
                token = sharedPreferences.getString("token", null);
                //添加购物车
                AddShopCarPresenter addShopCarPresenter = new AddShopCarPresenter(this);
                addShopCarPresenter.relevance(uid, pid, token);

                Log.e("xxxx  uid", uid);
                if (uid == "" || uid.equals(null)) {
                    Toast.makeText(DetailActivity.this, "请登录账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(DetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivShare:
                AlertDialog.Builder build=new AlertDialog.Builder(DetailActivity.this);
                build.setMessage("是否分享商品");
                build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        View view = View.inflate(DetailActivity.this, R.layout.pop_item, null);
                        Button bt_qqfriend=view.findViewById(R.id.bt_qqfriend);
                        Button bt_qqzone=view.findViewById(R.id.bt_qqzone);
                        bt_qqfriend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = detailBean.data.title;
                                String detailUrl = detailBean.data.detailUrl;
                                shareUtil.ShareWeb(R.mipmap.jd_buy_icon,"http://www.jd.com/",title,false);
                            }
                        });
                        bt_qqzone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String images = detailBean.data.images;
                                String title = detailBean.data.title;
                                String detailUrl = detailBean.data.detailUrl;
                                shareUtil.ShareWeb(R.mipmap.jd_buy_icon,"http://www.jd.com/",title,true);
                            }
                        });
                        PopupWindow pop=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        pop.setOutsideTouchable(true);
                        pop.setBackgroundDrawable(new ColorDrawable());
                        pop.showAtLocation(rl, Gravity.BOTTOM,0,0);

                    }
                });
                build.setNegativeButton("取消",null);
                build.show();
                break;
        }
    }


    @Override
    public void getAddShop(BaseBean baseBean) {
        addBean = (AddShopCarBean) baseBean;
        Log.e("aaaa", addBean.toString());
    }

    @Override
    public void getData(BaseBean baseBean) {
        detailBean = (DetailBean) baseBean;
        Log.e("detailBean", detailBean.toString());
    }

    class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new ProductFragment();
                    break;

                case 1:
                    fragment = new DetailFragment();
                    break;

                case 2:
                    fragment = new EvaluateFragment();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
