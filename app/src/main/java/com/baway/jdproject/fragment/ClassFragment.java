package com.baway.jdproject.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.jdproject.R;
import com.baway.jdproject.adapter.ClassGroupAdapter;
import com.baway.jdproject.adapter.ClassLeftAdapter;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ClassBean;
import com.baway.jdproject.model.bean.ClassRightBean;
import com.baway.jdproject.presenter.ClassPresenterImp;
import com.baway.jdproject.presenter.ClassRightPresenterImp;
import com.baway.jdproject.view.ClassRightView;
import com.baway.jdproject.view.ClassView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class ClassFragment extends Fragment implements ClassView, ClassRightView {


    private RecyclerView xrv;
    private ClassLeftAdapter classLeftAdapter;
    private int cid;
    private List<String> grouplist = new ArrayList<>();
    private List<List<ClassRightBean.DataBean.ListBean>> childlist = new ArrayList<>();
    private RecyclerView rv;
    private List<ClassRightBean.DataBean.ListBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        xrv = view.findViewById(R.id.xrv);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        xrv.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        /**
         * 关联左侧的xRecycleView
         */
        ClassPresenterImp classPresenterImp = new ClassPresenterImp(this);
        classPresenterImp.relevance();

        ClassRightPresenterImp classRightPresenterImp = new ClassRightPresenterImp(this);
        classRightPresenterImp.relevance("1");

        return view;
    }

    public void relevance() {
        /**
         * 关联右侧的
         */
        ClassRightPresenterImp classRightPresenterImp = new ClassRightPresenterImp(this);
        String cids = String.valueOf(cid);
        classRightPresenterImp.relevance(cids);
        Log.e("6666",cids);

    }

    @Override
    public void getClassData(BaseBean baseBean) {
        ClassBean classBean = (ClassBean) baseBean;
        List<ClassBean.DataBean> data = classBean.data;
        classLeftAdapter = new ClassLeftAdapter(getContext(), data);
        xrv.setAdapter(classLeftAdapter);
        //右侧一级导航的点击事件传入cid
        classLeftAdapter.setOnClickItem(new ClassLeftAdapter.OnItemClick() {
            @Override
            public void clickItem(int position) {
                cid = position;
                //为了防止添加重复的数据
                if (grouplist.size() > 0) {
                    grouplist.clear();
                }
                //为了防止重复数据
                if (childlist.size() > 0) {
                    childlist.clear();
                }
                relevance();
            }
        });

    }

    @Override
    public void getData(BaseBean baseBean) {
        ClassRightBean classRightBean = (ClassRightBean) baseBean;
        Log.e("kkkkkk",classRightBean.toString());
        List<ClassRightBean.DataBean> data = classRightBean.data;
        ClassGroupAdapter classGroupAdapter = new ClassGroupAdapter(getContext(), data);
        rv.setAdapter(classGroupAdapter);

    }


}
