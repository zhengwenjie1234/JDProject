package com.baway.jdproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.baway.jdproject.R;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.DetailBean;
import com.baway.jdproject.presenter.DetailPresenter;
import com.baway.jdproject.view.DetailView;

import static com.baway.jdproject.R.id.wv;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class DetailFragment extends Fragment implements DetailView {

    private WebView webview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        webview = view.findViewById(wv);
        WebSettings settings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptEnabled(true);
        //设置支持缩放
        settings.setBuiltInZoomControls(true);

        Intent intent = getActivity().getIntent();
        String pid = intent.getStringExtra("pid");
        DetailPresenter detailPresenter=new DetailPresenter(this);
        detailPresenter.relevance(pid);
        return view;
    }

    @Override
    public void getData(BaseBean baseBean) {
        DetailBean detailBean = (DetailBean) baseBean;
        String detailUrl = detailBean.data.detailUrl;
        webview.loadUrl(detailUrl);
    }
}
