package com.njhh.ahqy.service;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public interface LoginService {
    int wxLogin(String weCode, String time, String sign, HttpSession httpSession);

    int webLogin(String phoneNum, String authCode, String time, String sign, HttpSession httpSession);

    int netLogin(HttpHeaders headers, String time, String sign, HttpSession httpSession);

    int apiLogin();

    int loginCode(String PhoneNum);

}
