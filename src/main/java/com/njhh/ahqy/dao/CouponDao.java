package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Coupon;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public interface CouponDao {
    List<Coupon> getCouponList(Coupon pojo);
}
