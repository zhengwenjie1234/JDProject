package com.baway.jdproject.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.ClassRightBean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/6.
 */

public class ClassGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ClassRightBean.DataBean> list;
    public ClassGroupAdapter(Context context, List<ClassRightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_group_child, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.tvGroup.setText(list.get(position).name);
        myViewHolder.rv.setLayoutManager(new GridLayoutManager(context,3));
        List<ClassRightBean.DataBean.ListBean> childlist = this.list.get(position).list;
        ClassChildAdapter classChildAdapter = new ClassChildAdapter(context, childlist);
        myViewHolder.rv.setAdapter(classChildAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvGroup;
        private final RecyclerView rv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvGroup = itemView.findViewById(R.id.tvGroup);
            rv = itemView.findViewById(R.id.rv);
        }
    }
}
