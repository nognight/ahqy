package com.njhh.ahqy.controller;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.LoginBean;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/26.
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;

    /**
     * 登陆接口 get方法
     *
     * @param headers  请求头
     * @param type     请求类型 1是微信登陆 2是短信登陆 3是net取号 4是api接入（暂不实现）
     * @param weCode   type1  时传
     * @param phoneNum type2  时传
     * @param authCode type2  时传
     * @param sign     签名
     * @param time     时间戳
     * @return
     */
    @RequestMapping("/api/login")
    public ApiBean login(HttpSession httpSession,
                         @RequestHeader HttpHeaders headers,
                         @RequestParam(value = "type", defaultValue = "0") int type,
                         @RequestParam(value = "weCode", defaultValue = "0") String weCode,
                         @RequestParam(value = "phoneNum", defaultValue = "0") String phoneNum,
                         @RequestParam(value = "authCode", defaultValue = "0") String authCode,
                         @RequestParam(value = "userName", defaultValue = "0") String userName,
                         @RequestParam(value = "passWord", defaultValue = "0") String passWord,
                         @RequestParam(value = "picCode", defaultValue = "0") String picCode,
                         @RequestParam(value = "sign", defaultValue = "0") String sign,
                         @RequestParam(value = "time", defaultValue = "0") String time) {

        ApiBean apiBean = new ApiBean();
        if (null != httpSession.getAttribute("user")) {
            apiBean.setRet(ResultCode.SUCCESS);
            apiBean.setContent(new LoginBean(ResultCode.ERROR, "已经登陆"));
            return apiBean;
        }
        switch (type) {
            case AhqyConst.LOGIN_NONE:
                apiBean.setRet(ResultCode.SUCCESS);
                return apiBean;
            case AhqyConst.LOGIN_WX:
                apiBean.setContent(new LoginBean(loginService.wxLogin(weCode, time, sign, httpSession)));
                apiBean.setRet(ResultCode.SUCCESS);
                return apiBean;
            case AhqyConst.LOGIN_WEB:
                apiBean.setContent(new LoginBean(loginService.webLogin(phoneNum, authCode,userName,passWord ,picCode,time, sign, httpSession)));
                apiBean.setRet(ResultCode.SUCCESS);
                return apiBean;
            case AhqyConst.LOGIN_NET:
                logger.info(headers.toString());
                apiBean.setContent(new LoginBean(loginService.netLogin(headers, time, sign, httpSession)));
                apiBean.setRet(ResultCode.SUCCESS);
                return apiBean;
            case AhqyConst.LOGIN_API:
                apiBean.setContent(new LoginBean(loginService.apiLogin()));
                apiBean.setRet(ResultCode.SUCCESS);
                return apiBean;
        }
        return apiBean;
    }


    @RequestMapping("/api/loginCode")
    public ApiBean login(HttpSession httpSession,
                         String phoneNum) {
        ApiBean apiBean = new ApiBean();
        loginService.loginCode(phoneNum);
        return apiBean;

    }


    /**
     * 注销接口
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/api/logout")
    public ApiBean login(HttpSession httpSession) {
        ApiBean apiBean = new ApiBean();
        if (null == httpSession.getAttribute(AhqyConst.SESSION_USER)) {

        } else {
            httpSession.invalidate();
            apiBean.setRet(ResultCode.SUCCESS);
            return apiBean;
        }
        return apiBean;
    }

}
