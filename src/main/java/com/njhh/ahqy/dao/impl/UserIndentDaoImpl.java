package com.njhh.ahqy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.njhh.ahqy.entity.UserIndent;
import com.njhh.ahqy.dao.impl.mapper.UserIndentMapper;

@Service
public class UserIndentDaoImpl{

    @Autowired(required = false)
    private UserIndentMapper userIndentMapper;

    public int insert(UserIndent pojo){
        return userIndentMapper.insert(pojo);
    }

    public int insertSelective(UserIndent pojo){
        return userIndentMapper.insertSelective(pojo);
    }

    public int insertList(List<UserIndent> pojos){
        return userIndentMapper.insertList(pojos);
    }

    public int update(UserIndent pojo){
        return userIndentMapper.update(pojo);
    }
}
