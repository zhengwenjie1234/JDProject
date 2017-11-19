package com.baway.jdproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.app.FrescoImage;
import com.baway.jdproject.model.bean.AddShopCarBean;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.DetailBean;
import com.baway.jdproject.model.bean.PidEvent;
import com.baway.jdproject.presenter.DetailPresenter;
import com.baway.jdproject.view.DetailView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class ProductFragment extends Fragment implements DetailView {

    private Banner banner;
    private TextView tvTitle;
    private TextView subhead;
    private TextView tvPrice;
    private String uri;
    private AddShopCarBean addBean;
    private String sellerid;
    private String uid;
    private List<String> imageList = new ArrayList<>();
    private String pid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        banner = view.findViewById(R.id.banner);
        tvTitle = view.findViewById(R.id.tvTitle);
        subhead = view.findViewById(R.id.subhead);
        tvPrice = view.findViewById(R.id.tvPrice);


        Intent intent = getActivity().getIntent();
        String pid = intent.getStringExtra("pid");
        //详情页DetailPresenter
        DetailPresenter detailPresenter = new DetailPresenter(this);
        detailPresenter.relevance(pid);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }
    
    @Subscribe
    public void onEventMainThread(PidEvent pidEvent){
        pid = pidEvent.getPid();
        Toast.makeText(getContext(), pid, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getData(BaseBean baseBean) {
        DetailBean detailBean = (DetailBean) baseBean;
        int sellerids = detailBean.data.sellerid;
        sellerid = String.valueOf(sellerids);
        tvTitle.setText(detailBean.data.title);
        tvPrice.setText("¥：" + detailBean.data.price);
        subhead.setText(detailBean.data.subhead);
        String images = detailBean.data.images;
        //路径进行分割
        String[] split = images.split("\\|");
        for (int j = 0; j < split.length; j++) {
            String imageurl = split[j];
            imageList.add(imageurl);
        }
        banner.setImageLoader(new FrescoImage());
        banner.setImages(imageList);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.start();

    }
}
