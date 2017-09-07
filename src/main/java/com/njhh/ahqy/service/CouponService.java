package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.UserCoupon;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/27.
 */
public interface CouponService {

    /**
     * getCouponById
     * @param id
     * @return
     */
    Coupon getCouponById(int id);
    /**
     * 获得券列表
     *
     * @param httpSession
     * @return
     */
    List<Coupon> getCouponList(int type, HttpSession httpSession);

    /**
     * 获得用户卡券表
     *
     * @param type
     * @param httpSession
     * @return
     */
    List<UserCoupon> getUserCouponList(int type, HttpSession httpSession);


    /**
     * 使用卡券
     *
     * @param id
     * @param httpSession
     * @return
     */
    int useUserCoupon(int id, HttpSession httpSession);


    /**
     *
     * @param couponId
     * @param privilegeId
     * @param httpSession
     * @return
     */
    int addUserCoupon(int couponId,  int privilegeId, String startTime ,HttpSession httpSession);

    /**
     * 用户更新卡券
     *
     * @param id
     * @param httpSession
     * @return
     */
    int updateUserCoupon(int id, HttpSession httpSession);


}
