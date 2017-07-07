package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.UserPrivilege;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserPrivilegeDao {
    List<Privilege> getUserPrivilegeList(UserPrivilege userPrivilege);

    int addUserPrivilege(UserPrivilege userPrivilege);

    int updatePrivilege(UserPrivilege userPrivilege);
}
