package com.baway.jdproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.XinBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/18.
 */

public class ConfirmShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<XinBean> list;
    private Context context;

    public ConfirmShopAdapter(List<XinBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.confirmshop_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        XinBean xinBean = list.get(position);
        myViewHolder.tvName.setText(xinBean.getTitle());
        myViewHolder.tvPrice.setText(xinBean.getPrice());
        String images = xinBean.getImg();
        if (images.contains("|")) {
            for (int i = 0; i < images.length(); i++) {
                String[] split = images.split("\\|");
                for (int j = 0; j < split.length; j++) {
                    String uri = split[j % 1];
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
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView tvName;
        private final TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
