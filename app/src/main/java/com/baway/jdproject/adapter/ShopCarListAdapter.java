package com.baway.jdproject.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.event.AllCheckEvent;
import com.baway.jdproject.event.MCEvent;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.DeleteShopBean;
import com.baway.jdproject.model.bean.ShopCarListBean;
import com.baway.jdproject.presenter.DeleteShopPresenter;
import com.baway.jdproject.view.DeleteShopView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/7.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShopCarListBean.DataBean> data;
    private ShopCarListChildAdapter shopCarListChildAdapter;

    public ShopCarListAdapter(Context context, List<ShopCarListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopcar_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ShopCarListBean.DataBean dataBean = data.get(position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvDianPu.setText(dataBean.sellerName);
        myViewHolder.cb_group.setChecked(dataBean.isCb_group());
        final List<ShopCarListBean.DataBean.ListBean> cblist = dataBean.list;
        myViewHolder.rv.setLayoutManager(new LinearLayoutManager(context));
        shopCarListChildAdapter = new ShopCarListChildAdapter(context, cblist);
        myViewHolder.rv.setAdapter(shopCarListChildAdapter);

        shopCarListChildAdapter.setOnLongItemClick(new ShopCarListChildAdapter.OnlongItemClick() {
            @Override
            public void onLongClick(final ShopCarListBean.DataBean.ListBean listBean) {

                final DeleteShopPresenter deleteShopPresenter = new DeleteShopPresenter(new DeleteShopView() {

                    @Override
                    public void getDeleteData(BaseBean baseBean) {
                        DeleteShopBean deleteShopBean= (DeleteShopBean) baseBean;
                        Toast.makeText(context,deleteShopBean.msg,Toast.LENGTH_SHORT).show();
                    }
                });
                int pids = listBean.pid;
                String pid = String.valueOf(pids);
                Toast.makeText(context,pid,Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", null);
                int uids = sharedPreferences.getInt("uid", 0);
                String uid = String.valueOf(uids);
                deleteShopPresenter.relevance(uid,pid+"",token);
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        myViewHolder.cb_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.cb_group.isChecked()) {
                    data.get(position).setCb_group(true);
                    shopCarListChildAdapter = new ShopCarListChildAdapter(context, cblist);
                    myViewHolder.rv.setAdapter(shopCarListChildAdapter);
                    shopCarListChildAdapter.childselectAll(true);
                    if (isselectAll()) {
                        AllCheckEvent allCheckEvent = new AllCheckEvent();
                        allCheckEvent.setCheckAll(true);
                        EventBus.getDefault().post(allCheckEvent);
                    } else {
                        AllCheckEvent allCheckEvent = new AllCheckEvent();
                        allCheckEvent.setCheckAll(false);
                        EventBus.getDefault().post(allCheckEvent);
                    }
                    notifyDataSetChanged();

                } else {
                    data.get(position).setCb_group(false);
                    AllCheckEvent allCheckEvent = new AllCheckEvent();
                    allCheckEvent.setCheckAll(false);
                    EventBus.getDefault().post(allCheckEvent);
                    shopCarListChildAdapter = new ShopCarListChildAdapter(context, cblist);
                    myViewHolder.rv.setAdapter(shopCarListChildAdapter);
                    shopCarListChildAdapter.childselectAll(false);
                    notifyDataSetChanged();
                }
            }
        });

        shopCarListChildAdapter.setOnItemLayoutClickListener(new ShopCarListChildAdapter.OnItemLayoutClickListener() {
            @Override
            public void onItemLayoutClick(boolean flag) {
                if (flag) {
                    data.get(position).setCb_group(true);
                    if (isselectAll()) {
                        AllCheckEvent allCheckEvent = new AllCheckEvent();
                        allCheckEvent.setCheckAll(true);
                        EventBus.getDefault().post(allCheckEvent);
                    } else {
                        AllCheckEvent allCheckEvent = new AllCheckEvent();
                        allCheckEvent.setCheckAll(false);
                        EventBus.getDefault().post(allCheckEvent);
                    }
                    notifyDataSetChanged();
                } else {
                    data.get(position).setCb_group(false);
                    AllCheckEvent allCheckEvent = new AllCheckEvent();
                    allCheckEvent.setCheckAll(false);
                    EventBus.getDefault().post(allCheckEvent);
                    notifyDataSetChanged();
                }
            }
        });

    }

    /**
     * 是否全部勾选
     *
     * @return
     */
    public boolean isselectAll() {
        for (int i = 0; i < data.size(); i++) {
            boolean cb_group = data.get(i).isCb_group();
            if (!cb_group) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }


    /**
     * 判断全选操作
     *
     * @param checked
     */
    public void selectAll(boolean checked) {
        MCEvent mcEvent = new MCEvent();
        mcEvent.setFlag(true);
        EventBus.getDefault().post(mcEvent);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setCb_group(checked);
            shopCarListChildAdapter = new ShopCarListChildAdapter(context, data.get(i).list);
            shopCarListChildAdapter.childselectAll(checked);
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb_group;
        private final TextView tvDianPu;
        private final RecyclerView rv;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb_group = itemView.findViewById(R.id.cb_group);
            tvDianPu = itemView.findViewById(R.id.tvDianPu);
            rv = itemView.findViewById(R.id.rv);
        }
    }
}
