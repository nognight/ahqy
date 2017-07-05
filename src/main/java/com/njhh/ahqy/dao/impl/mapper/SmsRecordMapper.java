package com.njhh.ahqy.dao.impl.mapper;

import com.njhh.ahqy.entity.SmsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SmsRecordMapper {
    int insert(@Param("pojo") SmsRecord pojo);

    int insertSelective(@Param("pojo") SmsRecord pojo);

    int insertList(@Param("pojos") List<SmsRecord> pojo);

    int update(@Param("pojo") SmsRecord pojo);
}
