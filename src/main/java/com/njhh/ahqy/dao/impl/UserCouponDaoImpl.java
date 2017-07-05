package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.UserCouponDao;
import com.njhh.ahqy.entity.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.njhh.ahqy.entity.UserCoupon;
import com.njhh.ahqy.dao.impl.mapper.UserCouponMapper;

@Service
public class UserCouponDaoImpl implements UserCouponDao {

    @Autowired(required = false)
    private UserCouponMapper userCouponMapper;

    public int insert(UserCoupon pojo) {
        return userCouponMapper.insert(pojo);
    }

    public int insertSelective(UserCoupon pojo) {
        return userCouponMapper.insertSelective(pojo);
    }

    public int insertList(List<UserCoupon> pojos) {
        return userCouponMapper.insertList(pojos);
    }

    public int update(UserCoupon pojo) {
        return userCouponMapper.update(pojo);
    }

    @Override
    public List<Coupon> getUserCouponList() {
        return null;
    }

    @Override
    public int addUserCoupon(UserCoupon userCoupon){
            userCouponMapper.insert(userCoupon);
            return ResultCode.SUCCESS;
    }
}
