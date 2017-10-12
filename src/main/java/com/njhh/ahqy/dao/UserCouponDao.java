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
     * @param userId
     * @param source
     * @param status
     * @param type
     * @return
     */
    List<UserCoupon> getUserCouponList(int userId,int source ,int status ,int type);

    /**
     * 添加用户卡券
     * @param userCoupon
     * @return
     */
    int addUserCoupon(UserCoupon userCoupon);

    /**
     * 通过cid获得用户某个卡券
     * @param id
     * @param userId
     * @return
     */
    UserCoupon getUserCouponByCid(int id , int userId,int status);

    /**
     * 通过id获得用户某个卡券
     * @param id
     * @param userId
     * @return
     */
    UserCoupon getUserCouponById(int id , int userId);
}

