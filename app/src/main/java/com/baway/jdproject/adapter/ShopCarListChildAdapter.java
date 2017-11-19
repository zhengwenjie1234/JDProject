package com.baway.jdproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.event.MCEvent;
import com.baway.jdproject.model.bean.ShopCarListBean;
import com.baway.jdproject.widget.MyAddDelView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/7.
 */

public class ShopCarListChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShopCarListBean.DataBean.ListBean> list;

    /**
     * 长按接口
     */
    private OnlongItemClick onLongItemClick;
    public interface OnlongItemClick{
        void onLongClick(ShopCarListBean.DataBean.ListBean listBean);
    }
    public void setOnLongItemClick(OnlongItemClick onLongItemClick){
        this.onLongItemClick=onLongItemClick;
    }

    /**
     * 接口回调
     */
    private OnItemLayoutClickListener onItemLayoutClickListener;
    public interface OnItemLayoutClickListener {
        void onItemLayoutClick(boolean flag);
    }
    public void setOnItemLayoutClickListener(OnItemLayoutClickListener onItemLayoutClickListener) {
        this.onItemLayoutClickListener = onItemLayoutClickListener;
    }

    public ShopCarListChildAdapter(Context context, List<ShopCarListBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopcarlist_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ShopCarListBean.DataBean.ListBean listBean = list.get(position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(listBean.title);
        myViewHolder.tvPrice.setText("¥：" + listBean.price);
        myViewHolder.cb_child.setChecked(listBean.isCb_child());
        String images = listBean.images;
        if (images.contains("|")) {
            for (int i = 0; i < images.length(); i++) {
                String[] split = images.split("\\|");
                for (int j = 0; j < split.length; j++) {

                    String uri = split[j % 2];
                    myViewHolder.sdv.setImageURI(uri);

                }
            }
        } else {
            myViewHolder.sdv.setImageURI(images);
        }

        myViewHolder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onLongItemClick.onLongClick(listBean);
                return true;
            }
        });

        /**
         * 设置子类复选框，进行接口回调
         */
        myViewHolder.cb_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.cb_child.isChecked()){
                    list.get(position).setCb_child(true);
                    if (onItemLayoutClickListener!=null){
                        onItemLayoutClickListener.onItemLayoutClick(list.get(position).isCb_child());
                    }
                    for (int i = 0; i <list.size() ; i++) {
                        MCEvent mcEvent = new MCEvent();
                        mcEvent.setMoney(list.get(i).price+"");
                        mcEvent.setNum(list.size());
                        EventBus.getDefault().post(mcEvent);
                    }
                }else{
                    list.get(position).setCb_child(false);
                    if (onItemLayoutClickListener!=null){
                        onItemLayoutClickListener.onItemLayoutClick(list.get(position).isCb_child());
                    }
                    for (int i = 0; i <list.size() ; i++) {
                        MCEvent mcEvent = new MCEvent();
                        mcEvent.setMoney(-list.get(i).price+"");
                        mcEvent.setNum(-list.size());
                        EventBus.getDefault().post(mcEvent);
                    }

                }
            }
        });
        /**
         * 加减器操作
         */
        myViewHolder.adv.setOnItemClick(new MyAddDelView.OnItemClick() {
            @Override
            public void onItemAddClick(int count) {
                if (list.get(position).isCb_child()){
                    double price = list.get(position).price;
                    MCEvent mcEvent = new MCEvent();
                    mcEvent.setMoney(price+"");
                    mcEvent.setNum(1);
                    EventBus.getDefault().post(mcEvent);

                }else{
                    Toast.makeText(context,"请勾选",Toast.LENGTH_SHORT).show();
                    myViewHolder.adv.setCount();
                }
            }

            @Override
            public void onItemDelClick(int count) {
                if (list.get(position).isCb_child()){
                    MCEvent mcEvent = new MCEvent();
                    mcEvent.setMoney(-list.get(position).price+"");
                    mcEvent.setNum(-1);
                    EventBus.getDefault().post(mcEvent);

                }else{
                    Toast.makeText(context,"请勾选",Toast.LENGTH_SHORT).show();
                    myViewHolder.adv.setCount();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 全选操作判断
     * @param flag
     */
    public void childselectAll(boolean flag) {

        for (int i = 0; i <list.size() ; i++) {
            if (flag){
                list.get(i).setCb_child(true);
                //B41ABDD0E6E1CE17CAA49DF7D17A4CFF
                MCEvent mcEvent = new MCEvent();
                mcEvent.setMoney(list.get(i).price+"");
                mcEvent.setNum(list.size());
                EventBus.getDefault().post(mcEvent);
            }else{
                list.get(i).setCb_child(false);
                MCEvent mcEvent = new MCEvent();
                mcEvent.setMoney(-list.get(i).price+"");
                mcEvent.setNum(-list.size());
                EventBus.getDefault().post(mcEvent);

            }
            notifyDataSetChanged();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb_child;
        private final TextView tvName;
        private final TextView tvPrice;
        private final MyAddDelView adv;
        private final SimpleDraweeView sdv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb_child = itemView.findViewById(R.id.cb_child);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            sdv = itemView.findViewById(R.id.sdv);
            adv = itemView.findViewById(R.id.adv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
