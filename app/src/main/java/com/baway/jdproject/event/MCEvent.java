package com.baway.jdproject.event;

/**
 * Created by 郑文杰 on 2017/11/8.
 */

public class MCEvent {
    private String money;
    private int num;
    private boolean flag;

    public MCEvent(String money, int num, boolean flag) {
        this.money = money;
        this.num = num;
        this.flag = flag;
    }

    public MCEvent() {
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
