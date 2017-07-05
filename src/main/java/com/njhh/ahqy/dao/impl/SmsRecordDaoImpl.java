package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.SmsRecordDao;
import com.njhh.ahqy.dao.impl.mapper.SmsRecordMapper;
import com.njhh.ahqy.entity.SmsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SmsRecordDaoImpl implements SmsRecordDao {

    @Autowired(required = false)
    private SmsRecordMapper smsRecordMapper;

    public int insert(SmsRecord pojo) {
        return smsRecordMapper.insert(pojo);
    }

    public int insertSelective(SmsRecord pojo) {
        return smsRecordMapper.insertSelective(pojo);
    }

    public int insertList(List<SmsRecord> pojos) {
        return smsRecordMapper.insertList(pojos);
    }

    public int update(SmsRecord pojo) {
        return smsRecordMapper.update(pojo);
    }

    @Override
    public int addRecord(SmsRecord smsRecord) {
        return smsRecordMapper.insert(smsRecord);
    }
}
