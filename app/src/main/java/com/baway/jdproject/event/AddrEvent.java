package com.baway.jdproject.event;

/**
 * Created by 郑文杰 on 2017/11/16.
 */

public class AddrEvent {

    private String address;
    private String tel;
    private String detailAddress;

    public AddrEvent() {
    }

    public AddrEvent(String address, String tel, String detailAddress) {
        this.address = address;
        this.tel = tel;
        this.detailAddress = detailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
