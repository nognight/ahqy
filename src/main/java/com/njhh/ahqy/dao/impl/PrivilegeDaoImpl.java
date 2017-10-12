package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.PrivilegeDao;
import com.njhh.ahqy.entity.Privilege;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.njhh.ahqy.dao.impl.mapper.PrivilegeMapper;

@Repository
public class PrivilegeDaoImpl implements PrivilegeDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private PrivilegeMapper privilegeMapper;

    public int insert(Privilege pojo) {
        try {
            return privilegeMapper.insert(pojo);
        } catch (Exception e) {
            logger.warn("err insert");
            return -1;
        }

    }

    public int insertSelective(Privilege pojo) {
        try {
            return privilegeMapper.insertSelective(pojo);
        } catch (Exception e) {
            logger.warn("err insertSelective");
            return -1;
        }

    }

    public int insertList(List<Privilege> pojos) {
        try {
            return privilegeMapper.insertList(pojos);
        } catch (Exception e) {
            logger.warn("err insertList");
            return -1;
        }
    }

    public int update(Privilege pojo) {
        try {
            return privilegeMapper.update(pojo);
        } catch (Exception e) {
            logger.warn("err update");
            return -1;
        }
    }

    @Override
    public List<Privilege> getPrivilegeList(Privilege pojo) {
        try {
            return privilegeMapper.getPrivilegeList(pojo);
        } catch (Exception e) {
            logger.warn("err getPrivilegeList");
            return null;
        }

    }

    @Override
    public Privilege getPrivilege(Privilege pojo) {
        try {
            return privilegeMapper.getPrivilege(pojo);
        } catch (Exception e) {
            logger.warn("err getPrivilege");
            return null;
        }

    }

    @Override
    public Privilege getPrivilegeName(int id) {
        try {
            return privilegeMapper.getPrivilegeName(id);
        } catch (Exception e) {
            logger.warn("err getPrivilegeName");
            return null;
        }
    }
}
