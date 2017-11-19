package com.baway.jdproject.model.bean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public class ClassRightBean extends BaseBean {


    /**
     * msg : 获取子分类成功
     * data : [{"cid":"1","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"月饼","pcid":1,"pscid":1},{"icon":"http://120.27.23.105/images/icon.png","name":"坚果炒货","pcid":1,"pscid":2},{"icon":"http://120.27.23.105/images/icon.png","name":"糖巧","pcid":1,"pscid":3},{"icon":"http://120.27.23.105/images/icon.png","name":"休闲零食","pcid":1,"pscid":4},{"icon":"http://120.27.23.105/images/icon.png","name":"肉干肉脯","pcid":1,"pscid":5},{"icon":"http://120.27.23.105/images/icon.png","name":"饼干蛋糕","pcid":1,"pscid":6},{"icon":"http://120.27.23.105/images/icon.png","name":"蜜饯果干","pcid":1,"pscid":7},{"icon":"http://120.27.23.105/images/icon.png","name":"无糖食品","pcid":1,"pscid":8}],"name":"休闲零食","pcid":"1"},{"cid":"1","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"新鲜水果","pcid":2,"pscid":9},{"icon":"http://120.27.23.105/images/icon.png","name":"海鲜水产","pcid":2,"pscid":10},{"icon":"http://120.27.23.105/images/icon.png","name":"精选肉类","pcid":2,"pscid":11},{"icon":"http://120.27.23.105/images/icon.png","name":"蛋类","pcid":2,"pscid":12},{"icon":"http://120.27.23.105/images/icon.png","name":"新鲜蔬菜","pcid":2,"pscid":13},{"icon":"http://120.27.23.105/images/icon.png","name":"冷冻食品","pcid":2,"pscid":14},{"icon":"http://120.27.23.105/images/icon.png","name":"饮品甜品","pcid":2,"pscid":15},{"icon":"http://120.27.23.105/images/icon.png","name":"大闸蟹","pcid":2,"pscid":16}],"name":"京东生鲜","pcid":"2"},{"cid":"1","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"大米","pcid":3,"pscid":21},{"icon":"http://120.27.23.105/images/icon.png","name":"面粉","pcid":3,"pscid":22},{"icon":"http://120.27.23.105/images/icon.png","name":"杂粮","pcid":3,"pscid":23},{"icon":"http://120.27.23.105/images/icon.png","name":"食用油","pcid":3,"pscid":24},{"icon":"http://120.27.23.105/images/icon.png","name":"调味品","pcid":3,"pscid":25},{"icon":"http://120.27.23.105/images/icon.png","name":"方便速食","pcid":3,"pscid":26},{"icon":"http://120.27.23.105/images/icon.png","name":"有机食品","pcid":3,"pscid":27}],"name":"粮油调味","pcid":"3"},{"cid":"1","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"饮用水","pcid":4,"pscid":28},{"icon":"http://120.27.23.105/images/icon.png","name":"饮料","pcid":4,"pscid":29},{"icon":"http://120.27.23.105/images/icon.png","name":"牛奶乳品","pcid":4,"pscid":30},{"icon":"http://120.27.23.105/images/icon.png","name":"名茶","pcid":4,"pscid":31},{"icon":"http://120.27.23.105/images/icon.png","name":"蜂蜜","pcid":4,"pscid":32}],"name":"水饮茗茶","pcid":"4"},{"cid":"1","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"白酒","pcid":5,"pscid":33},{"icon":"http://120.27.23.105/images/icon.png","name":"葡萄酒","pcid":5,"pscid":34},{"icon":"http://120.27.23.105/images/icon.png","name":"洋酒","pcid":5,"pscid":35},{"icon":"http://120.27.23.105/images/icon.png","name":"啤酒","pcid":5,"pscid":36},{"icon":"http://120.27.23.105/images/icon.png","name":"黄酒","pcid":5,"pscid":37},{"icon":"http://120.27.23.105/images/icon.png","name":"陈年老酒","pcid":5,"pscid":38}],"name":"中外名酒","pcid":"5"}]
     */

    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * cid : 1
         * list : [{"icon":"http://120.27.23.105/images/icon.png","name":"月饼","pcid":1,"pscid":1},{"icon":"http://120.27.23.105/images/icon.png","name":"坚果炒货","pcid":1,"pscid":2},{"icon":"http://120.27.23.105/images/icon.png","name":"糖巧","pcid":1,"pscid":3},{"icon":"http://120.27.23.105/images/icon.png","name":"休闲零食","pcid":1,"pscid":4},{"icon":"http://120.27.23.105/images/icon.png","name":"肉干肉脯","pcid":1,"pscid":5},{"icon":"http://120.27.23.105/images/icon.png","name":"饼干蛋糕","pcid":1,"pscid":6},{"icon":"http://120.27.23.105/images/icon.png","name":"蜜饯果干","pcid":1,"pscid":7},{"icon":"http://120.27.23.105/images/icon.png","name":"无糖食品","pcid":1,"pscid":8}]
         * name : 休闲零食
         * pcid : 1
         */

        public String cid;
        public String name;
        public String pcid;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * icon : http://120.27.23.105/images/icon.png
             * name : 月饼
             * pcid : 1
             * pscid : 1
             */

            public String icon;
            public String name;
            public int pcid;
            public int pscid;
        }
    }
}
