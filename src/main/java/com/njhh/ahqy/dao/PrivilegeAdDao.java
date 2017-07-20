package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.PrivilegeAd;

import java.util.List;

/**
 * Created by HiWin10 on 2017/7/17.
 */
public interface PrivilegeAdDao {
    /**
     * 获得权益广告列表
     * @param privilegeAd
     * @return
     */
    List<PrivilegeAd>  getPrivilegeAdList(PrivilegeAd privilegeAd);

}
