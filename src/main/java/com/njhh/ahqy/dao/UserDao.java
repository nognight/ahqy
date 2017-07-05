package com.njhh.ahqy.dao;

import com.njhh.ahqy.dao.impl.mapper.UserMapper;
import com.njhh.ahqy.entity.Region;
import com.njhh.ahqy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by HiWin10 on 2017/6/14.
 */

public interface UserDao {
    int addUser(User user);
    int delUser(User user);
    int updateUser(User user);
    User getUser(User user);
    List<User> getUserList();
    String getPhoneNumFromWeCode(String weCode);

    Region getUserRegion(User user);
}
