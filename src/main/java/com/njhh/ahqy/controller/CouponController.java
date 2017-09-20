package com.njhh.ahqy.controller;

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
                                  @RequestParam (value="type" , defaultValue="0")int type){
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.getCouponList(type,httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }
    @RequestMapping("getUserList")
    private ApiBean getUserList(HttpSession httpSession,
                                int type,
                                int source,
                                int status){

        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.getUserCouponList(type,source,status,httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }
    @RequestMapping("addUserCoupon")
    private ApiBean addUserCoupon(HttpSession httpSession,
                                  int couponId,
                                  int privilegeId,
                                  int source,
                                  String startTime,
                                  int expire){
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.addUserCoupon(couponId,privilegeId,source ,startTime,expire,httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }
    @RequestMapping("addUserCoupons")
    private ApiBean addUserCoupons(HttpSession httpSession,
                                   int privilegeId,
                                   int source,
                                   String startTime,
                                   int expire){
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.addUserCoupons(privilegeId,source ,startTime,expire,httpSession)));
        apiBean.setRet(0);
        return apiBean;

    }
}
