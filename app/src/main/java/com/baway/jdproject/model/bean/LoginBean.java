package com.baway.jdproject.model.bean;

/**
 * Created by 郑文杰 on 2017/10/31.
 */

public class LoginBean extends BaseBean {
    /**
     * msg : 登录成功
     * data : {"age":null,"appkey":"5cbf2352cf4dec30","appsecret":"8C96EA51776723122E0ADEEA0F173D6A","createtime":"2017-11-08T20:43:38","email":null,"gender":null,"icon":null,"mobile":"18018018018","money":null,"nickname":null,"password":"30D0BA0744A36CFD7EFF8869E8B09A0E","token":"2136795F4AE1C550757761D2EAD83A0A","uid":1906,"username":"18018018018"}
     */

    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : 5cbf2352cf4dec30
         * appsecret : 8C96EA51776723122E0ADEEA0F173D6A
         * createtime : 2017-11-08T20:43:38
         * email : null
         * gender : null
         * icon : null
         * mobile : 18018018018
         * money : null
         * nickname : null
         * password : 30D0BA0744A36CFD7EFF8869E8B09A0E
         * token : 2136795F4AE1C550757761D2EAD83A0A
         * uid : 1906
         * username : 18018018018
         */

        public Object age;
        public String appkey;
        public String appsecret;
        public String createtime;
        public Object email;
        public Object gender;
        public Object icon;
        public String mobile;
        public Object money;
        public Object nickname;
        public String password;
        public String token;
        public int uid;
        public String username;
    }

}
