package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.UserCoupon;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserCouponDao {
    List<Coupon> getUserCouponList();
    int addUserCoupon(UserCoupon userCoupon);
}

