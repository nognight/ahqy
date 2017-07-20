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
        try {
            return privilegeAdMapper.insert(pojo);
        }catch (Exception e){
            logger.warn("err insert");
            return -1;
        }

    }

    public int insertSelective(PrivilegeAd pojo) {

        try {
            return privilegeAdMapper.insertSelective(pojo);
        }catch (Exception e){
            logger.warn("err insertSelective");
            return -1;
        }
    }

    public int insertList(List<PrivilegeAd> pojos) {

        try{
            return privilegeAdMapper.insertList(pojos);
        }catch (Exception e){
            logger.warn("err insertList");
            return -1;
        }
    }

    public int update(PrivilegeAd pojo) {

        try{
            return privilegeAdMapper.update(pojo);
        }catch (Exception e){
            logger.warn("err update");
            return -1;
        }
    }

    @Override
    public  List<PrivilegeAd>  getPrivilegeAdList(PrivilegeAd privilegeAd){
        try{
            return  privilegeAdMapper.getPrivilegeAdList(privilegeAd);
        }catch (Exception e){
            logger.warn("err getPrivilegeAdList" + e.getMessage());
            return null;
        }

    }


}
