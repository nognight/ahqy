package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.UserPrivilege;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserPrivilegeDao {
    /**
     * 获得用户权益列表
     * @param userPrivilege
     * @return
     */
    List<UserPrivilege> getUserPrivilegeList(UserPrivilege userPrivilege,int type);

    /**
     * 增加用户权益
     * @param userPrivilege
     * @return
     */
    int addUserPrivilege(UserPrivilege userPrivilege);

    /**
     * 更新用户权益
     * @param userPrivilege
     * @return
     */
    int updatePrivilege(UserPrivilege userPrivilege);
}
