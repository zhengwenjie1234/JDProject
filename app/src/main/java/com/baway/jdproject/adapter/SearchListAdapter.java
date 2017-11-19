package com.baway.jdproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.activity.DetailActivity;
import com.baway.jdproject.model.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/6.
 */

public class SearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<SearchBean.DataBean> list;
    private String uri;
    public SearchListAdapter(Context context, List<SearchBean.DataBean> list ) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchlist_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        String images = list.get(position).images;
        myViewHolder.tvName.setText(list.get(position).title);
        myViewHolder.tvPrice.setText("¥："+list.get(position).price);
        if (images.contains("|")) {
            for (int i = 0; i <images.length() ; i++) {
                String[] split = images.split("\\|");
                for (int j = 0; j < split.length; j++) {

                    uri = split[j%2];
                    myViewHolder.sdv.setImageURI(uri);

                }
            }

            myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pid = list.get(position).pid;
                    String pids = String.valueOf(pid);
                    Log.e("lllll",pids);
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("pid",pids);
                    context.startActivity(intent);
                }
            });


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
        private final SimpleDraweeView sdv;
        private final TextView tvPrice;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
