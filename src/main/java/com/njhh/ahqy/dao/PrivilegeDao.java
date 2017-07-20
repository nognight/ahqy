package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Privilege;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public interface PrivilegeDao {

    /**
     * 获得权益列表
     * @param pojo
     * @return
     */
    List<Privilege> getPrivilegeList(Privilege pojo);

    /**
     * 获得单个权益信息
     * @param pojo
     * @return
     */

    Privilege getPrivilege(Privilege pojo);
}
