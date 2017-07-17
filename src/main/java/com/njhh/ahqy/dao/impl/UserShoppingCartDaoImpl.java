package com.njhh.ahqy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.njhh.ahqy.entity.UserShoppingCart;
import com.njhh.ahqy.dao.impl.mapper.UserShoppingCartMapper;

@Service
public class UserShoppingCartDaoImpl{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private UserShoppingCartMapper userShoppingCartMapper;

    public int insert(UserShoppingCart pojo){
        try
        {
            return userShoppingCartMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
       
    }

    public int insertSelective(UserShoppingCart pojo){
        try
        {
            return userShoppingCartMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insertSelective");
            return -1;
        }
        
    }

    public int insertList(List<UserShoppingCart> pojos){
        try
        {
            return userShoppingCartMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
        
    }

    public int update(UserShoppingCart pojo){
        try
        {
            return userShoppingCartMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
       
    }
}
