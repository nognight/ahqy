package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.UserOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.njhh.ahqy.entity.UserOrder;
import com.njhh.ahqy.dao.impl.mapper.UserOrderMapper;

@Service
public class UserOrderDaoImpl implements UserOrderDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired(required = false)
    private UserOrderMapper userOrderMapper;

    public int insert(UserOrder pojo){
        try
        {
            return userOrderMapper.insert(pojo);
        } catch (Exception e)
        {
           logger.warn("err insert");
           return -1;
        }
       
    }

    public int insertSelective(UserOrder pojo){
        try
        {
            return userOrderMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insertSelective");
            return -1;
        }
       
    }

    public int insertList(List<UserOrder> pojos){
        try
        {
            return userOrderMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
       
    }

    public int update(UserOrder pojo){
        try
        {
            return userOrderMapper.update(pojo); 
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
       
    }

    @Override
    public int addUserOrder(UserOrder pojo){
        try
        {
            return   userOrderMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err addUserOrder");
            return -1;
        }
     
    }
    @Override
    public int  updateUserOrder(UserOrder pojo){
        try
        {
            return  userOrderMapper.update(pojo);

        } catch (Exception e)
        {
            logger.warn("err updateUserOrder");
            return -1;
        }
       
    }
    @Override
    public     UserOrder getUserOrder(UserOrder pojo){
        try
        {
            return userOrderMapper.getUserOrder(pojo);
        } catch (Exception e)
        {
            logger.warn("err getUserOrder");
            return null;
        }
        

    }


}
