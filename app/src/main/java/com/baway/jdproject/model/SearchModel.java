package com.baway.jdproject.model;

/**
 * Created by 郑文杰 on 2017/11/3.
 */

public interface SearchModel  {
    void getData(String keyWord,String page,String source,OnFinish onFinish);
}
