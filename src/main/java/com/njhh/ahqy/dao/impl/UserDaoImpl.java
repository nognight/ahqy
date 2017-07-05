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
        userMapper.insert(user);
        return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delUser(User user) {
        return 0;
    }

    @Override
    public User getUser(User user) {
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
    }
    @Override
    public List<User> getUserList(){
        return null;
    }


    @Override
    public  String getPhoneNumFromWeCode(String weCode){
        String phoneNum = "";
        return phoneNum;
    }

    @Override
    public int updateUser(User user){
        return userMapper.update(user);

    }

    @Override
    public Region getUserRegion(User user){
        String num = user.getPhoneNum().substring(0,7);
        Region region = new Region();
        region.setNumSeg(num);
        return regionMapper.getRegion(region);
    }










    public int insert(User pojo){
        return userMapper.insert(pojo);
    }

    public int insertSelective(User pojo){
        return userMapper.insertSelective(pojo);
    }

    public int insertList(List<User> pojos){
        return userMapper.insertList(pojos);
    }

    public int update(User pojo){
        return userMapper.update(pojo);
    }
}
