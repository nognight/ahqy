package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.UserPrivilege;

@Mapper
public interface UserPrivilegeMapper {
    int insert(@Param("pojo") UserPrivilege pojo);

    int insertSelective(@Param("pojo") UserPrivilege pojo);

    int insertList(@Param("pojos") List<UserPrivilege> pojo);

    int update(@Param("pojo") UserPrivilege pojo);

    List<UserPrivilege> getUserPrivilegeList(@Param("pojo") UserPrivilege pojo);
}
