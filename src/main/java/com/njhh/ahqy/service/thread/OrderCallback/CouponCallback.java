package com.njhh.ahqy.service.thread.OrderCallback;

import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.entity.UserCoupon;

/**
 * Created by nognight on 2017/9/7.
 */
public class CouponCallback extends OrderCallback{
    private UserCoupon userCoupon;
    private User user;
    private String phoneNum;

    public UserCoupon getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(UserCoupon userCoupon) {
        this.userCoupon = userCoupon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
