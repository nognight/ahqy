package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.UserOrder;

@Mapper
public interface UserOrderMapper {
    int insert(@Param("pojo") UserOrder pojo);

    int insertSelective(@Param("pojo") UserOrder pojo);

    int insertList(@Param("pojos") List<UserOrder> pojo);

    int update(@Param("pojo") UserOrder pojo);
    UserOrder getUserOrder(@Param("pojo") UserOrder pojo);

}
