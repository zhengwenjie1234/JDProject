package com.baway.jdproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/2.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeBean.TuijianBean.ListBean> list;
    private Context context;
    private String uri;

    public HomeAdapter(List<HomeBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_xrecycleitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(list.get(position).title);
        myViewHolder.tvPrice.setText("¥:" + list.get(position).price);
        String images = list.get(position).images;
        if (images.contains("|")) {
            for (int i = 0; i <images.length() ; i++) {
                String[] split = images.split("\\|");
                for (int j = 0; j < split.length; j++) {

                    uri = split[j%2];
                    myViewHolder.sdv.setImageURI(uri);

                }
            }


        } else {
            myViewHolder.sdv.setImageURI(images);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvPrice;
        private final SimpleDraweeView sdv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            sdv = itemView.findViewById(R.id.sdv);
        }
    }
}
