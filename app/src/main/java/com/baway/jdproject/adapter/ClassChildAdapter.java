package com.baway.jdproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.activity.ShopListActivity;
import com.baway.jdproject.model.bean.ClassRightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/4.
 */

public class ClassChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ClassRightBean.DataBean.ListBean> list;

    public ClassChildAdapter(Context context, List<ClassRightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_child, parent, false);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHoder myViewHoder= (MyViewHoder) holder;
        myViewHoder.tvName.setText(list.get(position).name);
        myViewHoder.sdv.setImageURI(list.get(position).icon);
        myViewHoder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pscid = list.get(position).pscid;
                Intent intent = new Intent(context, ShopListActivity.class);
                String pscids = String.valueOf(pscid);
                intent.putExtra("pscid",pscids);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final SimpleDraweeView sdv;
        private final LinearLayout ll;

        public MyViewHoder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            sdv = itemView.findViewById(R.id.sdv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
