package com.njhh.ahqy.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.njhh.ahqy.entity.CheckIn;
import com.njhh.ahqy.dao.impl.mapper.CheckInMapper;

@Service
public class CheckInDaoImpl{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private CheckInMapper checkInMapper;

    public int insert(CheckIn pojo){
        return checkInMapper.insert(pojo);
    }

    public int insertSelective(CheckIn pojo){
        return checkInMapper.insertSelective(pojo);
    }

    public int insertList(List<CheckIn> pojos){
        return checkInMapper.insertList(pojos);
    }

    public int update(CheckIn pojo){
        return checkInMapper.update(pojo);
    }
}
