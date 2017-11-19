package com.baway.jdproject.model.bean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/15.
 */

public class OrderListBean extends BaseBean {

    /**
     * msg : 请求成功
     * data : [{"createtime":"2017-11-13T16:06:40","orderid":1482,"price":111,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T12:52:15","orderid":1618,"price":111,"status":0,"title":null,"uid":505}]
     * page : 1
     */

    public String msg;
    public String page;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * createtime : 2017-11-13T16:06:40
         * orderid : 1482
         * price : 111.0
         * status : 0
         * title : null
         * uid : 505
         */

        public String createtime;
        public int orderid;
        public double price;
        public int status;
        public Object title;
        public int uid;
    }
}
