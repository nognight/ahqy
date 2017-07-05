package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.Coupon;

@Mapper
public interface CouponMapper {
    int insert(@Param("pojo") Coupon pojo);

    int insertSelective(@Param("pojo") Coupon pojo);

    int insertList(@Param("pojos") List<Coupon> pojo);

    int update(@Param("pojo") Coupon pojo);

    List<Coupon> getCouponList(@Param("pojo") Coupon pojo);
}
