package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.UserCoupon;

@Mapper
public interface UserCouponMapper {
    int insert(@Param("pojo") UserCoupon pojo);

    int insertSelective(@Param("pojo") UserCoupon pojo);

    int insertList(@Param("pojos") List<UserCoupon> pojo);

    int update(@Param("pojo") UserCoupon pojo);

    List<UserCoupon> getUserCouponList(@Param("userId") String userId);
}
