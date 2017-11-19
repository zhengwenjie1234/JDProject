package com.baway.jdproject.model.bean;

/**
 * Created by 郑文杰 on 2017/11/2.
 */

public class UserBean extends BaseBean {


    /**
     * msg : 获取用户信息成功
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-09T16:52:52","email":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/505.jpg","mobile":"13717766447","money":0,"nickname":null,"password":"888888","token":"B41ABDD0E6E1CE17CAA49DF7D17A4CFF","uid":505,"username":"13717766447"}
     */

    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-09T16:52:52
         * email : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/505.jpg
         * mobile : 13717766447
         * money : 0
         * nickname : null
         * password : 888888
         * token : B41ABDD0E6E1CE17CAA49DF7D17A4CFF
         * uid : 505
         * username : 13717766447
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public int gender;
        public String icon;
        public String mobile;
        public int money;
        public Object nickname;
        public String password;
        public String token;
        public int uid;
        public String username;
    }
}
