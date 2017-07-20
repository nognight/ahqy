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
    /**
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     *
     * @param user
     * @return
     */
    int delUser(User user);
    int updateUser(User user);
    User getUser(User user);
    List<User> getUserList();

    /**
     *
     * @param weCode
     * @return
     */
    String getPhoneNumFromWeCode(String weCode);

    /**
     * 获得用户归属地
     * @param user
     * @return
     */
    Region getUserRegion(User user);
}
