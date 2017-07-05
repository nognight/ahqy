package com.njhh.ahqy.service.thread.OrderCallback;

import org.springframework.stereotype.Service;

/**
 * Created by HiWin10 on 2017/7/2.
 */

public class OrderCallback {
    private int resultCode;
    private String msg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
