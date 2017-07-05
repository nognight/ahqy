package com.njhh.ahqy.service.thread.OrderCallback;

import com.njhh.ahqy.sms.SmsClient;

/**
 * Created by HiWin10 on 2017/7/2.
 */

public class PrivilegeCallback extends OrderCallback {
    private int privilegeId;
    private int userId;
    private String phoneNum;

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void checkOrder(){


        StringBuilder content = new StringBuilder("您本次订购的权益");
        content.append("成功，以收到的短信为准——安徽权益");
        SmsClient.getInstance().sendSms(phoneNum, content.toString());
    }
}
