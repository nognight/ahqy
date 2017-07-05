package com.njhh.ahqy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.njhh.ahqy.entity.UserShoppingCart;
import com.njhh.ahqy.dao.impl.mapper.UserShoppingCartMapper;

@Service
public class UserShoppingCartDaoImpl{

    @Autowired(required = false)
    private UserShoppingCartMapper userShoppingCartMapper;

    public int insert(UserShoppingCart pojo){
        return userShoppingCartMapper.insert(pojo);
    }

    public int insertSelective(UserShoppingCart pojo){
        return userShoppingCartMapper.insertSelective(pojo);
    }

    public int insertList(List<UserShoppingCart> pojos){
        return userShoppingCartMapper.insertList(pojos);
    }

    public int update(UserShoppingCart pojo){
        return userShoppingCartMapper.update(pojo);
    }
}
