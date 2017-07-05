package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.PrivilegeDao;
import com.njhh.ahqy.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.njhh.ahqy.dao.impl.mapper.PrivilegeMapper;

@Repository
public class PrivilegeDaoImpl implements PrivilegeDao{

    @Autowired(required = false)
    private PrivilegeMapper privilegeMapper;

    public int insert(Privilege pojo){
        return privilegeMapper.insert(pojo);
    }

    public int insertSelective(Privilege pojo){
        return privilegeMapper.insertSelective(pojo);
    }

    public int insertList(List<Privilege> pojos){
        return privilegeMapper.insertList(pojos);
    }

    public int update(Privilege pojo){
        return privilegeMapper.update(pojo);
    }

    @Override
    public List<Privilege> getPrivilegeList(Privilege pojo){
        return privilegeMapper.getPrivilegeList(pojo);
    }

    @Override
    public    Privilege getPrivilege(Privilege pojo){
        return  privilegeMapper.getPrivilege(pojo);
    }
}
