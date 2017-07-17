package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.SmsRecordDao;
import com.njhh.ahqy.dao.impl.mapper.SmsRecordMapper;
import com.njhh.ahqy.entity.SmsRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SmsRecordDaoImpl implements SmsRecordDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired(required = false)
    private SmsRecordMapper smsRecordMapper;

    public int insert(SmsRecord pojo) {
        try
        {
            return smsRecordMapper.insert(pojo); 
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
    }

    public int insertSelective(SmsRecord pojo) {
        try
        {
            return smsRecordMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
       
    }

    public int insertList(List<SmsRecord> pojos) {
        try
        {
            return smsRecordMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }
        
    }

    public int update(SmsRecord pojo) {
        try
        {
            return smsRecordMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
        
    }

    @Override
    public int addRecord(SmsRecord smsRecord) {
        try
        {
            return smsRecordMapper.insert(smsRecord);
        } catch (Exception e)
        {
            logger.warn("err addRecord");
            return -1;
        }   
    }
}
