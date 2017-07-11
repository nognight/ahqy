package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.UserPrivilege;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/27.
 */
public interface PrivilegeService {
    List<Privilege> getPrivilegeList(int type ,int category ,HttpSession httpSession);
    Privilege getPrivilegeInfo(int id , HttpSession httpSession);
    List<UserPrivilege> getUserPrivilegeList(HttpSession httpSession);

    int usePrivilege(int id, String authCode ,HttpSession httpSession);

    int getAuthCode(int id,HttpSession httpSession);

}
