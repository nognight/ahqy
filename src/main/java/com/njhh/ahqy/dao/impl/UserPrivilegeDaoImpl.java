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
        try
        {
            return userPrivilegeMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
        
    }

    public int insertSelective(UserPrivilege pojo) {
        try {
            return userPrivilegeMapper.insertSelective(pojo);
        } catch (Exception e) {
            logger.warn("err insertSelective");
            return -1;

        }
    }

    public int insertList(List<UserPrivilege> pojos){
        
        try
        {
            return userPrivilegeMapper.insertList(pojos);  
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
       
    }

    public int update(UserPrivilege pojo){
        
        try
        {
            return userPrivilegeMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
        
    }


    @Override
    public int addUserPrivilege(UserPrivilege pojo){
        
        try
        {
            return userPrivilegeMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err addUserPrivilege");
            return -1;
        }
       
    }

    @Override
    public  List<UserPrivilege> getUserPrivilegeList(UserPrivilege userPrivilege,int type){
        
        try
        {
            return  userPrivilegeMapper.getUserPrivilegeList(userPrivilege,type);
        } catch (Exception e)
        {
            logger.warn("err getUserPrivilegeList");
            return null;
        }
        
    }

    @Override
    public  int updatePrivilege(UserPrivilege userPrivilege){
        try
        {

            logger.info(userPrivilege.toString());
            return userPrivilegeMapper.update(userPrivilege);
        } catch (Exception e)
        {
            logger.warn("err updatePrivilege");
            return -1;
        }
        
    }
}
