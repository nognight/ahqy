package com.njhh.ahqy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.njhh.ahqy.entity.UserIndent;
import com.njhh.ahqy.dao.impl.mapper.UserIndentMapper;

@Service
public class UserIndentDaoImpl{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired(required = false)
    private UserIndentMapper userIndentMapper;

    public int insert(UserIndent pojo){
        try
        {
            return userIndentMapper.insert(pojo);
        } catch (Exception e)
        {
           logger.warn("err insert");
           return -1;
        }
      
    }

    public int insertSelective(UserIndent pojo){
        try
        {
            return userIndentMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insertSelective");
            return -1;
        }
       
    }

    public int insertList(List<UserIndent> pojos){
        try
        {
            return userIndentMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
        
    }

    public int update(UserIndent pojo){
        try
        {
            return userIndentMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
       
    }
}
