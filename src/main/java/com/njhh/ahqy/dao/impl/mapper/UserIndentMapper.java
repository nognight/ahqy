package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.UserIndent;

@Mapper
public interface UserIndentMapper {
    int insert(@Param("pojo") UserIndent pojo);

    int insertSelective(@Param("pojo") UserIndent pojo);

    int insertList(@Param("pojos") List<UserIndent> pojo);

    int update(@Param("pojo") UserIndent pojo);
}
