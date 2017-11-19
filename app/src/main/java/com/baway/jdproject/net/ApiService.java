package com.baway.jdproject.net;

import com.baway.jdproject.model.bean.AddAddressBean;
import com.baway.jdproject.model.bean.AddShopCarBean;
import com.baway.jdproject.model.bean.BaseBean;
import com.baway.jdproject.model.bean.ClassBean;
import com.baway.jdproject.model.bean.ClassRightBean;
import com.baway.jdproject.model.bean.CreateOrderBean;
import com.baway.jdproject.model.bean.DeleteShopBean;
import com.baway.jdproject.model.bean.DetailBean;
import com.baway.jdproject.model.bean.GetAddressBean;
import com.baway.jdproject.model.bean.GetMrAddressBean;
import com.baway.jdproject.model.bean.GetOrdersBean;
import com.baway.jdproject.model.bean.HomeBean;
import com.baway.jdproject.model.bean.LoginBean;
import com.baway.jdproject.model.bean.RegistBean;
import com.baway.jdproject.model.bean.SearchBean;
import com.baway.jdproject.model.bean.SetMrAddressBean;
import com.baway.jdproject.model.bean.ShopCarListBean;
import com.baway.jdproject.model.bean.ShopListBean;
import com.baway.jdproject.model.bean.UpdateAddressBean;
import com.baway.jdproject.model.bean.UserBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 郑文杰 on 2017/11/2.
 */

public interface ApiService {
    /**
     * 上传图片 参数有2个 uid file
     */
    @Multipart
    @POST("file/upload")
    Call<BaseBean>  uploadPic(@Part("uid")RequestBody uid,@Part("token")RequestBody token,  @Part MultipartBody.Part file);

    /**
     * 根据关键词搜索商品
     * https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1&source=android
     */
    @GET("product/searchProducts")
    Observable<SearchBean> getSerarchData(@Query("keywords")String keywords, @Query("page")String page, @Query("source")String source);


    /**
     * 注册
     * https://www.zhaoapi.cn/user/reg
     * @param mobile
     * @param password
     * @return
     */
    @POST("user/reg")
    @FormUrlEncoded
    Observable<RegistBean> userRegist(@Field("mobile")String mobile, @Field("password")String password);

    /**
     * 登录
     * https://www.zhaoapi.cn/user/login
     * @param mobile
     * @param password
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> userLogin(@Field("mobile")String mobile, @Field("password")String password);


    /**
     * 获取用户信息
     * @param uid
     * @param token
     * @return
     */
    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<UserBean> getUserInfo(@Field("uid")String uid, @Field("token")String token);

    /**
     * 首页广告
     * https://www.zhaoapi.cn/ad/getAd
     * @return
     */
    @GET("ad/getAd")
    Observable<HomeBean> getHomeData();

    /**
     * 商品分类接口
     * https://www.zhaoapi.cn/product/getCatagory
     */
    @GET("product/getCatagory")
    Observable<ClassBean> getHomeClass();

    /**
     * 商品子分类接口 右侧二三级
     * https://www.zhaoapi.cn/product/getProductCatagory
     * @param cid
     * @return
     */
    @GET("product/getProductCatagory")
    Observable<ClassRightBean> getClassRight(@Query("cid")String cid);

    /**
     * https://www.zhaoapi.cn/product/getProducts
     * 当前子分类下的商品列表
     * @param pscid
     */
    @POST("product/getProducts")
    @FormUrlEncoded
    Observable<ShopListBean> getShopList(@Field("pscid")String pscid);

    /**
     * 详情页面
     * https://www.zhaoapi.cn/product/getProductDetail
     * @param pid
     */
    @GET("product/getProductDetail")
    Observable<DetailBean> getDetailData(@Query("pid") String pid);

    /**
     * 添加购物车
     * https://www.zhaoapi.cn/product/addCart
     * @param uid
     * @param pid
     * @param token
     * @return
     */
    @GET("product/addCart")
    Observable<AddShopCarBean> getShopCar(@Query("uid")String uid, @Query("pid")String pid, @Query("token")String token);

    /**
     *
     * 购物车展示页面
     * @param uid
     * @return
     */
    @GET("product/getCarts")
    Observable<ShopCarListBean> getShopCarList(@Query("uid")String uid,@Query("token")String token);


    /**
     * 删除
     * https://www.zhaoapi.cn/product/deleteCart?uid=72&pid=1&token=
     * @param uid
     * @param pid
     * @param token
     * @return
     */
    @GET("product/deleteCart")
    Observable<DeleteShopBean> deleteShop(@Query("uid")String uid, @Query("pid")String pid, @Query("token")String token);



    /**
     * 创建订单
     * https://www.zhaoapi.cn/product/createOrder?uid=71&price=99.99
     * @param uid
     * @param price
     */
    @POST("product/createOrder")
    @FormUrlEncoded
    Observable<CreateOrderBean> createOrder(@Field("uid")String uid, @Field("price")String price, @Field("token")String token);

    /**
     * 订单列表
     * https://www.zhaoapi.cn/product/getOrders?uid=71
     * @param uid
     * @param token
     * @return
     */
    @GET("product/getOrders")
    Observable<GetOrdersBean> getOrders(@Query("uid")String uid, @Query("token")String token);

    /**
     * 添加收货地址
     * https://www.zhaoapi.cn/user/addAddr?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
     * @param uid
     * @param addr
     * @param mobile
     * @param name
     * @param token
     * @return
     */
    @POST("user/addAddr")
    @FormUrlEncoded
    Observable<AddAddressBean> setDetailAddress(@Field("uid")String uid, @Field("addr")String addr, @Field("mobile")String mobile, @Field("name")String name, @Field("token")String token);


    /**
     * 常用收货地址列表
     * https://www.zhaoapi.cn/user/getAddrs?uid=71
     * https://www.zhaoapi.cn/user/getAddrs?uid=71
     * getAddrs
     * @param uid
     * @param token
     * @return
     */
    @GET("user/getAddrs")
    Observable<GetAddressBean> addresslist(@Query("uid")String uid, @Query("token")String token);

    /**
     * 修改常用收货地址
     * https://www.zhaoapi.cn/user/updateAddr?uid=71&addrid=2
     * @param uid
     * @param addrid
     * @param token
     * @return
     */
    @GET("user/updateAddr")
    Observable<UpdateAddressBean> updateAddress(@Query("udi")String uid, @Query("addrid")String addrid, @Query("token")String token);

    /**
     * user/setAddr?uid=71&addrid=3&status=1
     * 设置默认地址
     * @param uid
     * @param status
     * @param token
     * @return
     */
    @GET("user/setAddr")
    Observable<SetMrAddressBean> setMrAddress(@Query("uid")String uid,@Query("addrid")String addrid, @Query("status")String status, @Query("token")String token);


    /**
     * https://www.zhaoapi.cn/user/getDefaultAddr?uid=71&token=
     * 获取默认地址
     * @param uid
     * @param token
     * @return
     */
    @GET("user/getDefaultAddr")
    Observable<GetMrAddressBean> getMrAddress(@Query("uid")String uid, @Query("token")String token);



}
