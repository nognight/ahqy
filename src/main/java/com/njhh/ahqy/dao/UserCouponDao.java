package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.UserCoupon;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserCouponDao {

    /**
     *
     * @param pojo
     * @return
     */
    int update(UserCoupon pojo);
    /**
     * 获得用户卡券列表
     * @return
     */
    List<UserCoupon> getUserCouponList(int userId);

    /**
     * 添加用户卡券
     * @param userCoupon
     * @return
     */
    int addUserCoupon(UserCoupon userCoupon);

    /**
     * 通过id获得用户某个卡券
     * @param id
     * @param userId
     * @return
     */
    UserCoupon getUserCouponById(int id , int userId);
}

