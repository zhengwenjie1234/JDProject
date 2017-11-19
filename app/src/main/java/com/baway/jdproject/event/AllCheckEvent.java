package com.baway.jdproject.event;

/**
 * Created by 郑文杰 on 2017/11/8.
 */

public class AllCheckEvent {
    private boolean checkAll;

    public AllCheckEvent() {
    }

    public AllCheckEvent(boolean checkAll) {
        this.checkAll = checkAll;
    }

    public boolean isCheckAll() {
        return checkAll;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }
}
