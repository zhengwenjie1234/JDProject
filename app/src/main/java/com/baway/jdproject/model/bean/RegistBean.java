package com.baway.jdproject.model.bean;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class RegistBean extends BaseBean {
    /**
     * msg : 注册成功
     * data : {"age":null,"createtime":null,"gender":0,"icon":null,"mobile":"23","money":0,"nickname":null,"password":"2888","uid":0,"username":"23"}
     */

    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * createtime : null
         * gender : 0
         * icon : null
         * mobile : 23
         * money : 0
         * nickname : null
         * password : 2888
         * uid : 0
         * username : 23
         */

        public Object age;
        public Object createtime;
        public int gender;
        public Object icon;
        public String mobile;
        public int money;
        public Object nickname;
        public String password;
        public int uid;
        public String username;
    }
}
