package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.CouponDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.dao.impl.mapper.CouponMapper;

@Repository
public class CouponDaoImpl implements CouponDao{

    @Autowired(required = false)
    private CouponMapper couponMapper;

    public int insert(Coupon pojo){
        return couponMapper.insert(pojo);
    }

    public int insertSelective(Coupon pojo){
        return couponMapper.insertSelective(pojo);
    }

    public int insertList(List<Coupon> pojos){
        return couponMapper.insertList(pojos);
    }

    public int update(Coupon pojo){
        return couponMapper.update(pojo);
    }

    @Override
    public List<Coupon> getCouponList(Coupon pojo){
        return   couponMapper.getCouponList(pojo);
    }
}
