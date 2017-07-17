package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.PrivilegeAdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.njhh.ahqy.entity.PrivilegeAd;
import com.njhh.ahqy.dao.impl.mapper.PrivilegeAdMapper;

@Service
public class PrivilegeAdDaoImpl implements PrivilegeAdDao{
        private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private PrivilegeAdMapper privilegeAdMapper;

    public int insert(PrivilegeAd pojo) {
        return privilegeAdMapper.insert(pojo);
    }

    public int insertSelective(PrivilegeAd pojo) {
        return privilegeAdMapper.insertSelective(pojo);
    }

    public int insertList(List<PrivilegeAd> pojos) {
        return privilegeAdMapper.insertList(pojos);
    }

    public int update(PrivilegeAd pojo) {
        return privilegeAdMapper.update(pojo);
    }

    @Override
    public  List<PrivilegeAd>  getPrivilegeAdList(PrivilegeAd privilegeAd){
        return  privilegeAdMapper.getPrivilegeAdList(privilegeAd);
    }


}
