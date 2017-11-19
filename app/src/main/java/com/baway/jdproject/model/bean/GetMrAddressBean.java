package com.baway.jdproject.model.bean;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class GetMrAddressBean extends BaseBean {

    /**
     * msg : 请求成功
     * data : {"addr":"北京市海淀区上地软件园南站","addrid":65,"mobile":13717766447,"name":"雨季","status":1,"uid":505}
     */

    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * addr : 北京市海淀区上地软件园南站
         * addrid : 65
         * mobile : 13717766447
         * name : 雨季
         * status : 1
         * uid : 505
         */

        public String addr;
        public int addrid;
        public long mobile;
        public String name;
        public int status;
        public int uid;
    }
}
