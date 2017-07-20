package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.UserCoupon;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserCouponDao {
    /**
     * 获得用户卡券列表
     * @return
     */
    List<Coupon> getUserCouponList();

    /**
     * 添加用户卡券
     * @param userCoupon
     * @return
     */
    int addUserCoupon(UserCoupon userCoupon);
}

