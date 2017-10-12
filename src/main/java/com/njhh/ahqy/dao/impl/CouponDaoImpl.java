package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.CouponDao;
import com.njhh.ahqy.dao.impl.mapper.CouponMapper;
import com.njhh.ahqy.entity.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponDaoImpl implements CouponDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private CouponMapper couponMapper;

    public int insert(Coupon pojo) {
        try {
            return couponMapper.insert(pojo);
        } catch (Exception e) {
            logger.warn("err insert");
            return -1;
        }

    }

    public int insertSelective(Coupon pojo) {
        try {
            return couponMapper.insertSelective(pojo);
        } catch (Exception e) {
            logger.warn("err insertSelective");
            return -1;
        }

    }

    public int insertList(List<Coupon> pojos) {
        try {
            return couponMapper.insertList(pojos);
        } catch (Exception e) {
            logger.warn("err insertList");
            return -1;
        }

    }

    public int update(Coupon pojo) {
        try {
            return couponMapper.update(pojo);
        } catch (Exception e) {
            logger.warn("err update");
            return -1;
        }
    }

    @Override
    public List<Coupon> getCouponList(Coupon pojo) {
        try {
            return couponMapper.getCouponList(pojo);
        } catch (Exception e) {
            logger.warn("err getCouponList");
            return null;
        }

    }

    @Override
    public Coupon getCouponById(int id) {
        try {
            return couponMapper.getCouponById(id);
        } catch (Exception e) {
            logger.warn("err getCouponById");
            return null;
        }
    }

    @Override
    public Coupon getCouponNameById(int id) {
        try {
            return couponMapper.getCouponNameById(id);
        } catch (Exception e) {
            logger.warn("err getCouponNameById");
            return null;
        }
    }
}
