package com.baway.jdproject.model.bean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/11/17.
 */

public class PidBean {
    private List<String> list;

    public PidBean(List<String> list) {
        this.list = list;
    }

    public PidBean() {
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
