package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.UserDao;
import com.njhh.ahqy.dao.impl.mapper.RegionMapper;
import com.njhh.ahqy.entity.Region;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.dao.impl.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by HiWin10 on 2017/6/14.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private RegionMapper regionMapper;




    @Override
    public int addUser(User user) {
        try{
        return   userMapper.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delUser(User user) {
        try
        {
            return 0;
        } catch (Exception e)
        {
            logger.warn("err delUser");
            return -1;
        }
        
    }

    @Override
    public User getUser(User user) {
        try
        {
            if(null!= user.getPhoneNum()&&!"".equals(user.getPhoneNum())){
                return   userMapper.getUserByPhoneNum(user.getPhoneNum());
              }
              if(null!= user.getId()&&!"".equals(user.getId())){
                  return   userMapper.getUserById(user.getId());
              }
              if(null!= user.getName()&&!"".equals(user.getName())){
                  return   userMapper.getUserByName(user.getName());
              }


              return null;
        } catch (Exception e)
        {
            logger.warn("err getUser");
            return null;
        }
        
    }
    @Override
    public List<User> getUserList(){
        try
        {
            return null;
        } catch (Exception e)
        {
            logger.warn("err getUserList");
            return null;
        }
       
    }


    @Override
    public  String getPhoneNumFromWeCode(String weCode){
        try
        {
            String phoneNum = "";
            return phoneNum;
        } catch (Exception e)
        {
            logger.warn("err getPhoneNumFromWeCode");
            return null;
        }
       
    }

    @Override
    public int updateUser(User user){
        try
        {
            return userMapper.update(user);
        } catch (Exception e)
        {
            logger.warn("err updateUser");
            return -1;
        }
      

    }

    @Override
    public Region getUserRegion(User user){
        try
        {
            String num = user.getPhoneNum().substring(0,7);
            Region region = new Region();
            region.setNumSeg(num);
            return regionMapper.getRegion(region);
        } catch (Exception e)
        {
            logger.warn("err getUserRegion");
            return null;
        }
       
    }










    public int insert(User pojo){
        try
        {
            return userMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
        
    }

    public int insertSelective(User pojo){
        try
        {
            return userMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insertSelective");
            return -1;
        }
        
    }

    public int insertList(List<User> pojos){
        try
        {
            return userMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
       
    }

    public int update(User pojo)
    {
        try
        {
            return userMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }

    }
}
