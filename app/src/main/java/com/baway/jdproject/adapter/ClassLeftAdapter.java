package com.baway.jdproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.ClassBean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class ClassLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ClassBean.DataBean> list;
    /**
     * 定义接口
     */
    private OnItemClick onItemClick;

    public interface OnItemClick {
        void clickItem(int position);
    }
    public void setOnClickItem(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }

    public ClassLeftAdapter(Context context, List<ClassBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_leftitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvTitle.setText(list.get(position).name);
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cid = list.get(position).cid;
                onItemClick.clickItem(cid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
