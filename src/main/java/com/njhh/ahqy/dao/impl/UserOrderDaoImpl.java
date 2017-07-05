package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.UserOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.njhh.ahqy.entity.UserOrder;
import com.njhh.ahqy.dao.impl.mapper.UserOrderMapper;

@Service
public class UserOrderDaoImpl implements UserOrderDao{

    @Autowired(required = false)
    private UserOrderMapper userOrderMapper;

    public int insert(UserOrder pojo){
        return userOrderMapper.insert(pojo);
    }

    public int insertSelective(UserOrder pojo){
        return userOrderMapper.insertSelective(pojo);
    }

    public int insertList(List<UserOrder> pojos){
        return userOrderMapper.insertList(pojos);
    }

    public int update(UserOrder pojo){
        return userOrderMapper.update(pojo);
    }

    @Override
    public int addUserOrder(UserOrder pojo){
      return   userOrderMapper.insert(pojo);
    }
    @Override
    public int  updateUserOrder(UserOrder pojo){
        return  userOrderMapper.update(pojo);

    }
    @Override
    public     UserOrder getUserOrder(UserOrder pojo){
        return userOrderMapper.getUserOrder(pojo);

    }


}
