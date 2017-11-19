package com.baway.jdproject.event;

/**
 * Created by 郑文杰 on 2017/11/8.
 */

public class ChildCheckEvent {

    private boolean flag;

    public ChildCheckEvent(boolean flag) {
        this.flag = flag;
    }

    public ChildCheckEvent() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
