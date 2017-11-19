package com.baway.jdproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.activity.DetailAddressActivity;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.GetAddressBean;
import com.baway.jdproject.model.bean.SetMrAddressBean;
import com.baway.jdproject.presenter.SetMrAddressPresenter;
import com.baway.jdproject.view.SetMrAddressView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GetAddressBean.DataBean> list;

    public AddressAdapter(Context context, List<GetAddressBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final GetAddressBean.DataBean bean = list.get(position);
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.address.setText(bean.addr);
        myViewHolder.tel.setText(bean.mobile+"");
        myViewHolder.tvDetailAddress.setText(bean.addr);
        myViewHolder.cb.setChecked(false);
        myViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checked = list.get(position).isChecked();
                Log.i("ch",checked+"");
                int addrids = list.get(position).addrid;
                String addrid = String.valueOf(addrids);
                int statuss = list.get(position).status;
                String status = String.valueOf(statuss);
                SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", MODE_PRIVATE);
                int uids = sharedPreferences.getInt("uid", 0);
                String uid = String.valueOf(uids);
                String token = sharedPreferences.getString("token", null);

                for (int i = 0; i <list.size() ; i++) {
                    if (!checked){
                        SetMrAddressPresenter setMrAddressPresenter = new SetMrAddressPresenter(new SetMrAddressView() {
                            @Override
                            public void setMrAddress(BaseBean baseBean) {
                                SetMrAddressBean setMrAddressBean= (SetMrAddressBean) baseBean;
                                String msg = setMrAddressBean.msg;
                                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                            }
                        });
                        setMrAddressPresenter.relevance(uid,addrid,status,token);
                    }
                }


            }
        });
        myViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAddressActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        if (list!=null){
            return list.size();
        }
        return 0;

    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox cb;
        private final TextView address;
        private final TextView tel;
        private final TextView tvDetailAddress;
        private final ImageView update;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb);
            address = itemView.findViewById(R.id.address);
            tel = itemView.findViewById(R.id.tel);
            tvDetailAddress = itemView.findViewById(R.id.tvDetailAddress);
            update = itemView.findViewById(R.id.update);
        }
    }
}
