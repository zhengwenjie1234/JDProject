package com.baway.jdproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.jdproject.R;
import com.baway.jdproject.activity.LoginActivity;
import com.baway.jdproject.activity.OrdersActivity;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.UserBean;
import com.baway.jdproject.net.Api;
import com.baway.jdproject.net.ApiService;
import com.baway.jdproject.presenter.UserPresenterImp;
import com.baway.jdproject.view.UserView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class MySelfFragment extends Fragment implements View.OnClickListener,UserView{

    private TextView tvLogin;
    private String uid;
    private SimpleDraweeView header;
    private String token;
    private LinearLayout myOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int uids = sharedPreferences.getInt("uid", 0);
        token = sharedPreferences.getString("token", "");
        uid = String.valueOf(uids);
        UserPresenterImp userPresenter = new UserPresenterImp(this);
        userPresenter.relevance(uid,token);
        myOrder = view.findViewById(R.id.myOrder);
        tvLogin = view.findViewById(R.id.tvLogin);
        header = view.findViewById(R.id.head);
        tvLogin.setOnClickListener(this);
        header.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        return view;
    }

    /**
     * 图片上传
     */
    private void uploadpic() {

        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //图片文件
        File file = new File(Environment.getExternalStorageDirectory() + "/shuma.png");
        //创建RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody uid1 = RequestBody.create(MediaType.parse("multipart/form-data"), uid);
        RequestBody token1 = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<BaseBean> call = apiService.uploadPic(uid1,token1, body);
        call.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                Log.e("xxxx", "成功");
                Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogin:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.head:
                uploadpic();
                break;

            case R.id.myOrder:
                Intent intent1 = new Intent(getContext(), OrdersActivity.class);
                startActivity(intent1);
                break;

        }
    }

    @Override
    public void getData(BaseBean baseBean) {
        UserBean userBean= (UserBean) baseBean;
        Log.e("TAG",userBean.toString());
        String mobile = userBean.data.mobile;
        tvLogin.setText(mobile);
        String icon = userBean.data.icon;
        Uri uri = Uri.parse(icon);
        header.setImageURI(uri);
        Toast.makeText(getContext(),userBean.toString(),Toast.LENGTH_SHORT).show();
    }
}
