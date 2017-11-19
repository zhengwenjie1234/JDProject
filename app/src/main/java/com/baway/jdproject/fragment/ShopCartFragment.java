package com.baway.jdproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.activity.CreateOrderActivity;
import com.baway.jdproject.adapter.ShopCarListAdapter;
import com.baway.jdproject.event.AllCheckEvent;
import com.baway.jdproject.event.MCEvent;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.DeleteShopBean;
import com.baway.jdproject.model.bean.ShopCarListBean;
import com.baway.jdproject.model.bean.XinBean;
import com.baway.jdproject.presenter.ShopCarListPresenter;
import com.baway.jdproject.view.DeleteShopView;
import com.baway.jdproject.view.ShopCarListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class ShopCartFragment extends Fragment implements ShopCarListView,View.OnClickListener,DeleteShopView {

    private RecyclerView rv;
    private String uid;
    private CheckBox cb_all;
    private ShopCarListAdapter shopCarListAdapter;
    private TextView tvTotalPrice;
    private TextView tvNum;
    private int totalMoney;
    private int totalNum;
    private String token;
    private LinearLayout ll_order;
    private ShopCarListBean shopCarListBean;
    private List<ShopCarListBean.DataBean> data;
    private ShopCarListBean.DataBean.ListBean listBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_shopcart, container, false);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        rv = view.findViewById(R.id.rv);
        cb_all = view.findViewById(R.id.cb_all);
        cb_all.setOnClickListener(this);
        tvNum = view.findViewById(R.id.tvNum);
        ll_order = view.findViewById(R.id.ll_order);
        ll_order.setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //获取uid
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        //Toast.makeText(getContext(),token,Toast.LENGTH_SHORT).show();
        int uids = sharedPreferences.getInt("uid", 0);
        uid = String.valueOf(uids);
        //关联ShopCarListPresenter
        ShopCarListPresenter shopCarListPresenter = new ShopCarListPresenter(this);
        shopCarListPresenter.relevance(uid, token);
        return view;
    }

    /**
     * 注册EventBus
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * 全选复选框判断状态
     *
     * @param allCheckEvent
     */
    @Subscribe
    public void allCheck(AllCheckEvent allCheckEvent) {
        cb_all.setChecked(allCheckEvent.isCheckAll());
    }

    /**
     * 总计数量和价格
     *
     * @param mcEvent
     */
    @Subscribe
    public void moneyAndNum(MCEvent mcEvent) {
        if (mcEvent.isFlag()) {
            totalNum = 0;
            totalMoney = 0;
        }
        String money = mcEvent.getMoney();
        int num = mcEvent.getNum();
        double m = Double.parseDouble(money);
        int mon = (int) m;
        totalMoney += mon;
        totalNum += num;
        if (totalMoney <= 0 || totalNum <= 0) {
            totalMoney = 0;
            totalNum = 0;
        }
        tvTotalPrice.setText(totalMoney + "");
        tvNum.setText(totalNum + "");

    }


    /**
     * 注销EventBus
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void getData(BaseBean baseBean) {
        if (uid.equals(null) || uid == "") {
            Toast.makeText(getContext(), "请登录", Toast.LENGTH_LONG).show();
            return;
        }
        shopCarListBean = (ShopCarListBean) baseBean;
        data = shopCarListBean.data;
        shopCarListAdapter = new ShopCarListAdapter(getContext(), data);
        rv.setAdapter(shopCarListAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_all:
                shopCarListAdapter.selectAll(cb_all.isChecked());
                break;
            case R.id.ll_order:
                if (totalMoney!=0){
                    Intent intent = new Intent(getContext(), CreateOrderActivity.class);
                    String totalmoney = String.valueOf(totalMoney);
                    intent.putExtra("totalMoney",totalmoney);
                    List<XinBean> list=new ArrayList<>();
                    for (int i = 0; i <data.size() ; i++) {
                        for (int j = 0; j <data.get(i).list.size() ; j++) {
                            if (data.get(i).list.get(j).isCb_child()){
                                ShopCarListBean.DataBean.ListBean listBean = data.get(i).list.get(j);
                                String title = listBean.title;
                                double price = listBean.price;
                                String images = listBean.images;
                                XinBean xinBean = new XinBean(title, price + "", images);
                                list.add(xinBean);
                            }
                        }
                    }
                    intent.putExtra("list", (Serializable) list);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"请勾选商品",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void getDeleteData(BaseBean baseBean) {
        DeleteShopBean deleteShopBean= (DeleteShopBean) baseBean;
        String msg = deleteShopBean.msg;
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();

    }
}
