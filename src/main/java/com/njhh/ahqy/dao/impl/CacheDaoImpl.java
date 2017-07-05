package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CacheDao;
import com.njhh.ahqy.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by HiWin10 on 2017/6/14.
 */
@Repository
public class CacheDaoImpl implements CacheDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User getUser() {
        return new User();
    }


    @Override
    public String sendAuthCode(String type, Integer id, Integer userId, String phoneNum,String remark) {

        StringBuilder key = new StringBuilder("AuthCode:");
        key.append(type);
        key.append(":");
        key.append(id);
        key.append(":");
        key.append(userId);
        key.append(":");
        key.append(phoneNum);
        key.append(":");
        key.append(remark);


        int a = (int)(Math.random()*9000+1000);
        String value =""+a;

        stringRedisTemplate.opsForValue().set(key.toString(), value, 5, TimeUnit.MINUTES);
        return value;
    }

    @Override
    public  String getAuthCode(String type,Integer id,Integer userId,String phoneNum,String remark){

        StringBuilder key = new StringBuilder("AuthCode:");
        key.append(type);
        key.append(":");
        key.append(id);
        key.append(":");
        key.append(userId);
        key.append(":");
        key.append(phoneNum);
        key.append(":");
        key.append(remark);

        String value = stringRedisTemplate.opsForValue().get(key);
        return  value;

    }
}
