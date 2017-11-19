package com.baway.jdproject.adapter;

import android.content.Context;
import android.graphics.Paint;
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
 * Created by 郑文杰 on 2017/11/3.
 */

public class HomeMSAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomeBean.MiaoshaBean.ListBeanX> list;
    private String uri;

    public HomeMSAdapter(Context context, List<HomeBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_xrecyclems, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvBargainPrice.setText("¥" + list.get(position).bargainPrice + "");
        myViewHolder.tvPrice.setText("¥" + list.get(position).price);
        String images = list.get(position).images;
        if (images.contains("|")) {
            for (int i = 0; i < images.length(); i++) {
                String[] split = images.split("\\|");
                for (int j = 0; j < split.length; j++) {

                    uri = split[j % 1];
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

        private final TextView tvBargainPrice;
        private final SimpleDraweeView sdv;
        private final TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv);
            tvBargainPrice = itemView.findViewById(R.id.tvBargainPrice);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
