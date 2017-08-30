package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.Coupon;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/27.
 */
public interface CouponService {
    /**
     * 获得券列表
     * @param httpSession
     * @return
     */
    List<Coupon> getCouponList(int type,HttpSession httpSession);

    /**
     * 获得用户卡券表
     * @param type
     * @param httpSession
     * @return
     */
    List<Coupon> getUserCouponList(int type , HttpSession httpSession);



}
