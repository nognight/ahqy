package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.service.thread.OrderCallback.OrderCallback;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/14.
 */
public interface UserService {

    /**
     * 订购
     * @param productId
     * @param authCode
     * @return
     */
    int orderByProductId(int productId, String authCode, HttpSession httpSession);


    /**
     * 更新用户信息
     * @param httpSession
     * @return
     */
    int updateUser(HttpSession httpSession);





    /**
     *订购
     * @param ids
     * @param httpSession
     * @param orderCallback
     * @param type
     * @param smsCode
     * @return
     */
    int orderProducts(String[] ids , HttpSession httpSession, OrderCallback orderCallback ,int type,String smsCode);

    int orderProducts(String[] ids , User user ,OrderCallback orderCallback,int type,String smsCode);




    /**
     * 验证码
     * @param type
     * @param id
     * @param httpSession
     * @return
     */
    int sendAuthCode(String type,Integer id,HttpSession httpSession);



}
