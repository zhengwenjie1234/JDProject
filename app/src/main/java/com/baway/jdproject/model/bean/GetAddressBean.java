package com.baway.jdproject.model.bean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/13.
 */

public class GetAddressBean extends BaseBean {

    /**
     * msg : 地址列表请求成功
     * data : [{"addr":"北京市海淀区上地软件园南站","addrid":65,"mobile":13717766447,"name":"雨季","status":0,"uid":505},{"addr":"激动啥","addrid":66,"mobile":2222,"name":"jj","status":0,"uid":505},{"addr":"666","addrid":67,"mobile":333,"name":"22","status":0,"uid":505},{"addr":"北京会死啊咖啡等级","addrid":68,"mobile":15810205555,"name":"逐字逐句","status":0,"uid":505},{"addr":"爱的色放第三方","addrid":69,"mobile":12345678954,"name":"123","status":0,"uid":505},{"addr":"北京市","addrid":70,"mobile":15695959595,"name":"丽丽","status":0,"uid":505},{"addr":"上海","addrid":71,"mobile":15715771577,"name":"哈哈","status":0,"uid":505},{"addr":"山西","addrid":72,"mobile":16916916999,"name":"杜岩","status":0,"uid":505}]
     */

    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * addr : 北京市海淀区上地软件园南站
         * addrid : 65
         * mobile : 13717766447
         * name : 雨季
         * status : 0
         * uid : 505
         */

        public String addr;
        public int addrid;
        public long mobile;
        public String name;
        public int status;
        public int uid;
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
