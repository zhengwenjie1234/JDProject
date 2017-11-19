package com.baway.jdproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.baway.jdproject.R;
import com.baway.jdproject.activity.SerarchActivity;
import com.baway.jdproject.adapter.HomeAdapter;
import com.baway.jdproject.adapter.HomeGridAdapter;
import com.baway.jdproject.adapter.HomeMSAdapter;
import com.baway.jdproject.app.FrescoImage;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ClassBean;
import com.baway.jdproject.model.bean.HomeBean;
import com.baway.jdproject.presenter.ClassPresenterImp;
import com.baway.jdproject.presenter.HomePresenterImp;
import com.baway.jdproject.view.ClassView;
import com.baway.jdproject.view.HomeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.baway.jdproject.R.id.xrv_ms;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class HomeFragment extends Fragment implements HomeView, ClassView, View.OnClickListener {

    private XRecyclerView xrv;
    private HomeBean homeBean;
    private Banner banner;
    private List<String> imageList = new ArrayList<>();
    private RecyclerView rv_ms;
    private TextView name;
    private LinearLayout search;
    private String keyWord;
    private GridView gridview;
    private LinearLayout sm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //初始化组件
        sm = view.findViewById(R.id.sm);
        sm.setOnClickListener(this);
        xrv = view.findViewById(R.id.xrv);
        banner = view.findViewById(R.id.banner);
        rv_ms = view.findViewById(xrv_ms);
        gridview = view.findViewById(R.id.gridview);
        name = view.findViewById(R.id.et_search);
        name.setOnClickListener(this);

        //获取SharedPreferences里的值
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        //设置线性布局
        xrv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_ms.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        //实例化HomePresenterImp，进行关联
        HomePresenterImp presenter = new HomePresenterImp(this);
        presenter.relevance();
        //实例化ClassPresenterImp
        ClassPresenterImp classPresenterImp = new ClassPresenterImp(this);
        classPresenterImp.relevance();
        return view;
    }

    @Override
    public void getData(BaseBean baseBean) {
        homeBean = (HomeBean) baseBean;

        //Banner数据
        List<HomeBean.DataBean> data = homeBean.data;
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).icon;
            imageList.add(icon);
        }
        banner.setImageLoader(new FrescoImage());
        banner.setImages(imageList);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.start();

        //设置适配器
        HomeAdapter homeAdapter = new HomeAdapter(homeBean.tuijian.list, getContext());
        xrv.setAdapter(homeAdapter);

        //设置秒杀适配器
        HomeMSAdapter homeMSAdapter = new HomeMSAdapter(getContext(), homeBean.miaosha.list);
        rv_ms.setAdapter(homeMSAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_search:
                Intent intent = new Intent(getContext(), SerarchActivity.class);
                startActivity(intent);
                break;

            case R.id.sm:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
                break;
        }
    }

    @Override
    public void getClassData(BaseBean baseBean) {
        ClassBean classBean = (ClassBean) baseBean;
        List<ClassBean.DataBean> data = classBean.data;
        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(getContext(), data);
        gridview.setAdapter(homeGridAdapter);

    }
}
