package com.njhh.ahqy.service;

import com.njhh.ahqy.dao.CacheDao;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.util.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/14.
 */
public interface InitService {
    User getUser();

    ValidateCode getValidateCode(String type, String phoneNum, HttpSession session);
}
