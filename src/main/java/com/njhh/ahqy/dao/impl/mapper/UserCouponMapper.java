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

    List<UserCoupon> getUserCouponList(@Param("userId") int userId, @Param("source") int source, @Param("status") int status, @Param("type") int type);

    UserCoupon getUserCouponByCid(@Param("cid") int cid, @Param("userId") int userId, @Param("status") int status);

    UserCoupon getUserCouponById(@Param("id") int id, @Param("userId") int userId);
}
