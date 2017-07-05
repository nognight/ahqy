package com.njhh.ahqy.httpclient.bean;

import java.util.Map;

/**
 * Created by HiWin10 on 2017/6/29.
 */
public class GateWayApi {
    private int ret;
    private Map<String, Object> data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
