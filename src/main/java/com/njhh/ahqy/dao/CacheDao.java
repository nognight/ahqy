package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by HiWin10 on 2017/6/14.
 */

public interface CacheDao {
    User getUser();

    /**
     * 验证码入redis
     * @param type  类型string
     * @param id 产品id，卡券id等
     * @param userId 用户id
     * @param PhoneNum 用户号码
     * @param remark 备用
     * @return  验证码
     */
    String sendAuthCode(String type,Integer id,Integer userId,String PhoneNum,String remark);
    /**
     * redis取验证码
     * @param type  类型string
     * @param id 产品id，卡券id等
     * @param userId 用户id
     * @param PhoneNum 用户号码
     * @param remark 备用
     * @return  验证码
     */
    String getAuthCode(String type,Integer id,Integer userId,String PhoneNum,String remark);

}
