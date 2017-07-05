package com.njhh.ahqy.controller.restfulBeans;

/**
 * Created by HiWin10 on 2017/6/29.
 */
public class CodeBean extends Content{
    private int ret = -1;

    public CodeBean(int ret) {
        this.ret = ret;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }
}
