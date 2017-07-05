package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CacheDao;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.service.InitService;
import com.njhh.ahqy.util.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/14.
 */
@Service
public class InitServiceImpl implements InitService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheDao cacheDao;

    @Override
    public User getUser() {

        return cacheDao.getUser();
    }

    @Override
    public ValidateCode getValidateCode(String type, String phoneNum, HttpSession session){
        if(null == phoneNum || "".equals(phoneNum)){
            return null;
        }
        ValidateCode vCode = new ValidateCode(120,40,4,100);
        session.setAttribute(type+"Code", vCode.getCode());
        return vCode;
    }
}
