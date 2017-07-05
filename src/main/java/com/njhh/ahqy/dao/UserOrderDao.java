package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.UserOrder;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserOrderDao {

   int addUserOrder(UserOrder userOrder);
    int  updateUserOrder(UserOrder userOrder);
    UserOrder getUserOrder(UserOrder userOrder);
}
