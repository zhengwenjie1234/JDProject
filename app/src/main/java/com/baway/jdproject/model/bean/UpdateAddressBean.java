package com.baway.jdproject.model.bean;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class UpdateAddressBean extends BaseBean {

    /**
     * msg : 更新成功
     * data : {"addr":"激动啥","addrid":66,"mobile":2222,"name":"jj","status":0,"uid":505}
     */

    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * addr : 激动啥
         * addrid : 66
         * mobile : 2222
         * name : jj
         * status : 0
         * uid : 505
         */

        public String addr;
        public int addrid;
        public int mobile;
        public String name;
        public int status;
        public int uid;
    }
}
