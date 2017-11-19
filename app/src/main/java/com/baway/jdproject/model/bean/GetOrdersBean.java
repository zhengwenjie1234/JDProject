package com.baway.jdproject.model.bean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class GetOrdersBean extends BaseBean {

    /**
     * msg : 请求成功
     * data : [{"createtime":"2017-11-13T16:06:40","orderid":1482,"price":111,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T12:52:15","orderid":1618,"price":111,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T14:11:22","orderid":1637,"price":0,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T16:12:45","orderid":1672,"price":0,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T16:17:06","orderid":1676,"price":0,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T16:19:12","orderid":1678,"price":0,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T16:19:37","orderid":1679,"price":0,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T16:41:55","orderid":1694,"price":799,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T16:54:14","orderid":1695,"price":1687,"status":0,"title":null,"uid":505},{"createtime":"2017-11-15T17:54:53","orderid":1696,"price":1687,"status":0,"title":null,"uid":505}]
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
