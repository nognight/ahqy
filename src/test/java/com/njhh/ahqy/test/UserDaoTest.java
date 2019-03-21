package com.njhh.ahqy.test;

import com.njhh.ahqy.dao.UserDao;
import com.njhh.ahqy.dao.impl.UserDaoImpl;
import com.njhh.ahqy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by HiWin10 on 2017/6/29.
 */
public class UserDaoTest {

    public static void main(String args[]){
        User user = new User();
        user.setPhoneNum("13295699645");
        UserDao userDao = new UserDaoImpl();
            userDao.getUser(user);

    }



}
