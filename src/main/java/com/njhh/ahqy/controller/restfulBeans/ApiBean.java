package com.njhh.ahqy.controller.restfulBeans;

import com.njhh.ahqy.common.ResultCode;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public class ApiBean {
    private int ret = ResultCode.ERROR;
    private Content content = null;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
