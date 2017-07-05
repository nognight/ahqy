package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Privilege;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public interface PrivilegeDao {

    List<Privilege> getPrivilegeList(Privilege pojo);

    Privilege getPrivilege(Privilege pojo);
}
