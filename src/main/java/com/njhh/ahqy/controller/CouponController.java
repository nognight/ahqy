package com.njhh.ahqy.controller;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.ObjBean;
import com.njhh.ahqy.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/28.
 */
@RestController
@RequestMapping("api/coupon/")
public class CouponController {
    @Autowired
    private CouponService couponService;


    @RequestMapping("getList")
    private ApiBean getCouponList(HttpSession httpSession,
                                  @RequestParam(value = "type", defaultValue = "0") int type) {
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.getCouponList(type, httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }

    @RequestMapping("getUserList")
    private ApiBean getUserList(HttpSession httpSession,
                                @RequestParam(value = "type", defaultValue = "0") int type,
                                @RequestParam(value = "source", defaultValue = "0") int source,
                                @RequestParam(value = "status", defaultValue = "0") int status) {

        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.getUserCouponList(type, source, status, httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }

    @RequestMapping("addUserCoupon")
    private ApiBean addUserCoupon(HttpSession httpSession,
                                  @RequestParam(defaultValue = "0") int couponId,
                                  @RequestParam(defaultValue = "0") int privilegeId,
                                  @RequestParam(defaultValue = "0") int source,
                                  @RequestParam(defaultValue = AhqyConst.INITAL_TIME) String startTime,
                                  @RequestParam(defaultValue = "0") int expire) {
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.addUserCoupon(couponId, privilegeId, source, startTime, expire, httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }

    @RequestMapping("addUserCoupons")
    private ApiBean addUserCoupons(HttpSession httpSession,
                                   @RequestParam(defaultValue = "0") int privilegeId,
                                   @RequestParam(defaultValue = "0") int source,
                                   @RequestParam(defaultValue = AhqyConst.INITAL_TIME) String startTime,
                                   @RequestParam(defaultValue = "0") int expire) {
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.addUserCoupons(privilegeId, source, startTime, expire, httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }
}
