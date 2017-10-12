package com.njhh.ahqy.service;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public interface LoginService {
    /**
     * 微信登陆
     * @param weCode
     * @param time
     * @param sign
     * @param httpSession
     * @return
     */
    int wxLogin(String weCode, String time, String sign, HttpSession httpSession);

    /**
     *  web登陆
     *
     * @param phoneNum
     * @param authCode
     * @param userName
     * @param passWord
     * @param picCode
     * @param time
     * @param sign
     * @param httpSession
     * @return
     */
    int webLogin(String phoneNum, String authCode,String userName,String passWord ,String picCode ,String time, String sign, HttpSession httpSession);

    /**
     * net取号
     * @param headers
     * @param time
     * @param sign
     * @param httpSession
     * @return
     */
    int netLogin(HttpHeaders headers, String time, String sign, HttpSession httpSession);

    /**
     * api接入
     * @return
     */
    int apiLogin();

    /**
     * 验证码
     * @param PhoneNum
     * @return
     */
    int loginCode(String PhoneNum);

}
