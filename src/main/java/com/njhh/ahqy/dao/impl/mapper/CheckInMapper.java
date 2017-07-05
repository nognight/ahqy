package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.CheckIn;

@Mapper
public interface CheckInMapper {
    int insert(@Param("pojo") CheckIn pojo);

    int insertSelective(@Param("pojo") CheckIn pojo);

    int insertList(@Param("pojos") List<CheckIn> pojo);

    int update(@Param("pojo") CheckIn pojo);
}
