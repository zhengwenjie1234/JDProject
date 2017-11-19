package com.baway.jdproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.ClassBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class HomeGridAdapter extends BaseAdapter {

    private Context context;
    private List<ClassBean.DataBean> list;

    public HomeGridAdapter(Context context, List<ClassBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView=View.inflate(context, R.layout.home_gridclass,null);
            holder.sdv=convertView.findViewById(R.id.sdv);
            holder.tvName=convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position).name);
        holder.sdv.setImageURI(list.get(position).icon);
        return convertView;
    }

    class ViewHolder{
        SimpleDraweeView sdv;
        TextView tvName;
    }
}
