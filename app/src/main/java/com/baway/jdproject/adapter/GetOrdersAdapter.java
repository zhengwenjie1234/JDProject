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
import com.baway.jdproject.activity.PayMethodActivity;
import com.baway.jdproject.model.bean.GetOrdersBean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class GetOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<GetOrdersBean.DataBean> list;

    public GetOrdersAdapter(Context context, List<GetOrdersBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        double price = list.get(position).price;
        viewHolder.tvPrice.setText(price+"");
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int orderid = list.get(position).orderid;
                double price1 = list.get(position).price;
                Intent intent = new Intent(context, PayMethodActivity.class);
                intent.putExtra("orderid",orderid);
                intent.putExtra("price",price1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvPrice;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
