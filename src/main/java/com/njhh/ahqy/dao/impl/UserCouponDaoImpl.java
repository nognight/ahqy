package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.UserCouponDao;
import com.njhh.ahqy.dao.impl.mapper.UserCouponMapper;
import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.UserCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCouponDaoImpl implements UserCouponDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private UserCouponMapper userCouponMapper;

    public int insert(UserCoupon pojo) {
        try {
            return userCouponMapper.insert(pojo);
        } catch (Exception e) {
            logger.warn("err insert");
            return -1;
        }

    }

    public int insertSelective(UserCoupon pojo) {
        try {
            return userCouponMapper.insertSelective(pojo);
        } catch (Exception e) {
            logger.warn("err insertSelective");
            return -1;
        }

    }

    public int insertList(List<UserCoupon> pojos) {
        try {
            return userCouponMapper.insertList(pojos);
        } catch (Exception e) {
            logger.warn("err insertList");
            return -1;
        }

    }

    public int update(UserCoupon pojo) {
        try {
            return userCouponMapper.update(pojo);
        } catch (Exception e) {
            logger.warn("err update");
            return -1;
        }

    }

    @Override
    public List<Coupon> getUserCouponList() {
        return null;
    }

    @Override
    public int addUserCoupon(UserCoupon userCoupon) {
        try {
            userCouponMapper.insert(userCoupon);
            return ResultCode.SUCCESS;
        } catch (Exception e) {
            logger.warn("err addUserCoupon");
            return -1;
        }

    }
}
