package com.baway.jdproject.model.bean;

import java.io.Serializable;

/**
 * Created by 郑文杰 on 2017/11/18.
 */

public class XinBean implements Serializable{
    private String title;
    private String price;
    private String img;

    public XinBean(String title, String price, String img) {
        this.title = title;
        this.price = price;
        this.img = img;
    }

    public XinBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
