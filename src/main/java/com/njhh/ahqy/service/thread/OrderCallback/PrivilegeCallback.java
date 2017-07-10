package com.njhh.ahqy.service.thread.OrderCallback;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.dao.PrivilegeDao;
import com.njhh.ahqy.dao.ProductDao;
import com.njhh.ahqy.dao.UserPrivilegeDao;
import com.njhh.ahqy.dao.impl.UserPrivilegeDaoImpl;
import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.Product;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.entity.UserPrivilege;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.impl.UserServiceImpl;
import com.njhh.ahqy.service.thread.OrderThread;
import com.njhh.ahqy.sms.SmsClient;
import com.njhh.ahqy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/7/2.
 */

public class PrivilegeCallback extends OrderCallback {
    private UserPrivilege userPrivilege;
    private Privilege privilege;
    private User user;
    private String phoneNum;




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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
