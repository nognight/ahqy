package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.dao.CouponDao;
import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by HiWin10 on 2017/6/28.
 */
@Service
public  class CouponServiceImpl implements CouponService {
    @Autowired
    private  CouponDao couponDao;
    @Override
    public List<Coupon> getCouponList(int type, HttpSession httpSession) {
        Coupon coupon = new Coupon();
        coupon.setType(type);
        return couponDao.getCouponList(coupon);
    }

    @Override
    public List<Coupon> getUserCouponList(int type, HttpSession httpSession) {
        return null;
    }
}

