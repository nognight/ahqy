package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.PrivilegeAd;

@Mapper
public interface PrivilegeAdMapper {
    int insert(@Param("pojo") PrivilegeAd pojo);

    int insertSelective(@Param("pojo") PrivilegeAd pojo);

    int insertList(@Param("pojos") List<PrivilegeAd> pojo);

    int update(@Param("pojo") PrivilegeAd pojo);

    List<PrivilegeAd> getPrivilegeAdList(@Param("pojo") PrivilegeAd pojo);

}
