package com.baway.jdproject.net;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.baway.jdproject.model.bean.BaseBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 郑文杰 on 2017/10/14.
 */

public class HttpUtil {

    private static volatile HttpUtil httpUtil;
    private Context context;
    private Handler handler=new Handler(Looper.getMainLooper());
    private final OkHttpClient client;


    public HttpUtil(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        this.context=context;

    }

    public static HttpUtil getInstance(Context context){

        if (httpUtil==null){
            synchronized (HttpUtil.class){
                if (httpUtil==null){
                    httpUtil = new HttpUtil(context);
                }
            }
        }
        return httpUtil;
    }

    public void doPost(String url, Map<String, String> params, final Class clazz, final OnNetListener onNetListener) {
        if (params != null && params.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            FormBody formBody = builder.build();

            Request request = new Request.Builder().url(url).post(formBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final BaseBean baseBean = (BaseBean) new Gson().fromJson(response.body().string(), clazz);
                    String code = baseBean.getCode();
                    if (code.equals("0")){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    onNetListener.onSuccess(baseBean);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else{


                    }
                }
            });
        }


    }

    public void doGet(String url, final Class clazz, final OnNetListener onNetListener){

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                /*final BaseBean baseBean = (BaseBean) new Gson().fromJson(response.body().string(), clazz);
                String code = baseBean.getCode();
                if (code.equals("0")){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                onNetListener.onSuccess(baseBean);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }else{

                }*/

            }
        });

    }

}
