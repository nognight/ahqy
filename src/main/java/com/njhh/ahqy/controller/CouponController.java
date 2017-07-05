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
public class CouponController {
    @Autowired
    private CouponService couponService;


    @RequestMapping("/api/coupon/getList")
    private ApiBean getCouponList(HttpSession session,
                                  @RequestParam (value="type" , defaultValue="0")int type){
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(couponService.getCouponList(type,session)));
        apiBean.setRet(0);
        return apiBean;

    }
}
