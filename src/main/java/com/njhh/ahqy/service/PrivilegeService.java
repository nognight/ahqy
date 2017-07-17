package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.PrivilegeAd;
import com.njhh.ahqy.entity.UserPrivilege;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/27.
 */
public interface PrivilegeService {
    /**
     *取权益
     * @param type
     * @param category
     * @param httpSession
     * @return
     */
    List<Privilege> getPrivilegeList(int type ,int category ,HttpSession httpSession);

    /**
     *取权益广告
     * @param source  为0时取全部
     * @param id 为0是取全部
     * @param httpSession
     * @return
     */
    List<PrivilegeAd> getPrivilegeAdList(int source ,int id ,HttpSession httpSession);

    /**
     *
     * @param id
     * @param httpSession
     * @return
     */
    Privilege getPrivilegeInfo(int id , HttpSession httpSession);

    /**
     *
     * @param httpSession
     * @return
     */
    List<UserPrivilege> getUserPrivilegeList(HttpSession httpSession);

    /**
     *
     * @param id
     * @param authCode
     * @param httpSession
     * @return
     */
    int usePrivilege(int id, String authCode ,HttpSession httpSession);

    /**
     *
     * @param id
     * @param httpSession
     * @return
     */
    int getAuthCode(int id,HttpSession httpSession);

}
