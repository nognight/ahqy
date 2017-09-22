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
    List<PrivilegeAd> getPrivilegeAdList(int type,int source ,int id ,HttpSession httpSession);

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

    int usePrivilege(int id, String authCode, int source ,HttpSession httpSession);


    /**
     * 增加权益
     * @param id
     * @param source
     * @param httpSession
     * @return
     */
    int addUserPrivilege(int id, int source ,HttpSession httpSession);

    /**
     * 凭id使用权益
     * @param id
     * @param phoneNumber
     * @param timestamp
     * @param sign
     * @param httpSession
     * @return
     */
    int usePrivilegeById(String channel,int id, String phoneNumber,String timestamp, String sign ,HttpSession httpSession);

    /**
     *
     * @param id
     * @param httpSession
     * @return
     */

    int getAuthCode(int id,HttpSession httpSession);

}
