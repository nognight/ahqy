package com.njhh.ahqy.controller;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.ObjBean;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/14.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/api/user/getInfo")
    private ApiBean getUserInfo(HttpSession httpSession) {
        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean((User) httpSession.getAttribute("user")));
        apiBean.setRet(0);

        return apiBean;
    }

    @RequestMapping("/api/user/order")
    private ApiBean userOrder(HttpSession httpSession,
                              Integer productId,
                              String authCode) {
        ApiBean apiBean = new ApiBean();
        userService.orderByProductId(productId, authCode, httpSession);
        return apiBean;
    }

    @RequestMapping("/api/user/authCode")
    private ApiBean sendAuthCode(HttpSession httpSession,
                                 String type,
                                 Integer id) {
        ApiBean apiBean = new ApiBean();
        userService.sendAuthCode(type,id,httpSession);
        return apiBean;
    }
}
