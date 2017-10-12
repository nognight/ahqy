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
     * @param id
     * @return
     */
    Coupon getCouponNameById(int id, HttpSession httpSession);

    /**
     * getCouponById
     *
     * @param id
     * @return
     */
    Coupon getCouponById(int id, HttpSession httpSession);

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
     * @param type        卡券类型
     * @param source      卡券来源
     * @param status      用户卡券现在状态
     * @param httpSession
     * @return
     */
    List<UserCoupon> getUserCouponList(int type, int source, int status, HttpSession httpSession);


    /**
     * 使用卡券
     *
     * @param id
     * @param httpSession
     * @return
     */
    int useUserCoupon(int id, HttpSession httpSession);


    /**增加一张卡券后消耗对应权益
     * @param couponId
     * @param privilegeId
     * @param source
     * @param startTime
     * @param expire      有效期单位h
     * @param httpSession
     * @return
     */

    int addUserCoupon(int couponId, int privilegeId, int source, String startTime, int expire, HttpSession httpSession);

    /**
     * 批量增加卡券后消耗对应权益
     *
     * @param privilegeId
     * @param source
     * @param startTime
     * @param expire      有效期单位h
     * @param httpSession
     * @return
     */
    int addUserCoupons(int privilegeId, int source, String startTime, int expire, HttpSession httpSession);

    /**
     * 用户更新卡券
     *
     * @param id
     * @param httpSession
     * @return
     */
    int updateUserCoupon(int id, HttpSession httpSession);


}
