package com.njhh.ahqy.service.thread.OrderCallback;

import com.njhh.ahqy.dao.UserPrivilegeDao;
import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.UserPrivilege;
import com.njhh.ahqy.sms.SmsClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/7/2.
 */

public class PrivilegeCallback extends OrderCallback {
    private UserPrivilege userPrivilege;
    private Privilege privilege;
    private int userId;
    private String phoneNum;

    @Autowired
    private UserPrivilegeDao userPrivilegeDao;


    public UserPrivilege getUserPrivilege() {
        return userPrivilege;
    }

    public void setUserPrivilege(UserPrivilege userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
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

        // TODO: 2017/7/5 根据订购结果进行回掉
        userPrivilege.setStatus(1);
        userPrivilege.setStartTime(new Date());
        userPrivilege.setUsedTime(new Date());
        userPrivilegeDao.updatePrivilege(userPrivilege);




    }
}
