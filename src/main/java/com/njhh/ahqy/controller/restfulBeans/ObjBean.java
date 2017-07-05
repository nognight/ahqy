package com.njhh.ahqy.controller.restfulBeans;

import com.njhh.ahqy.dao.CacheDao;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public class ObjBean extends Content{
    private Object object =null;

    public ObjBean(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
