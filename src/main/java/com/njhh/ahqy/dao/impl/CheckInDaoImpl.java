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
        try
        {
            return checkInMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;

        }
        
    }

    public int insertSelective(CheckIn pojo){
        try
        {
            return checkInMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insertSelective");
            return -1;

        }
    }

    public int insertList(List<CheckIn> pojos){
        try
        {
            return checkInMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
        
    }

    public int update(CheckIn pojo){
        try
        {
            return checkInMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
       
    }
}
