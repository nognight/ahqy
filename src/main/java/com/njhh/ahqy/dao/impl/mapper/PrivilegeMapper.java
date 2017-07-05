package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.Privilege;

@Mapper
public interface PrivilegeMapper {
    int insert(@Param("pojo") Privilege pojo);

    int insertSelective(@Param("pojo") Privilege pojo);

    int insertList(@Param("pojos") List<Privilege> pojo);

    int update(@Param("pojo") Privilege pojo);


    List<Privilege> getPrivilegeList(@Param("pojo") Privilege pojo);
    Privilege getPrivilege(@Param("pojo") Privilege pojo);
}
