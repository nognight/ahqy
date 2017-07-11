package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.UserPrivilegeDao;
import com.njhh.ahqy.entity.Privilege;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.njhh.ahqy.entity.UserPrivilege;
import com.njhh.ahqy.dao.impl.mapper.UserPrivilegeMapper;

@Service
public class UserPrivilegeDaoImpl implements UserPrivilegeDao{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private UserPrivilegeMapper userPrivilegeMapper;

    public int insert(UserPrivilege pojo){
        return userPrivilegeMapper.insert(pojo);
    }

    public int insertSelective(UserPrivilege pojo){
        return userPrivilegeMapper.insertSelective(pojo);
    }

    public int insertList(List<UserPrivilege> pojos){
        return userPrivilegeMapper.insertList(pojos);
    }

    public int update(UserPrivilege pojo){
        return userPrivilegeMapper.update(pojo);
    }


    @Override
    public int addUserPrivilege(UserPrivilege pojo){
        return userPrivilegeMapper.insert(pojo);
    }

    @Override
    public  List<UserPrivilege> getUserPrivilegeList(UserPrivilege userPrivilege){
        return  userPrivilegeMapper.getUserPrivilegeList(userPrivilege);
    }

    @Override
    public  int updatePrivilege(UserPrivilege userPrivilege){

        return userPrivilegeMapper.update(userPrivilege);
    }
}
