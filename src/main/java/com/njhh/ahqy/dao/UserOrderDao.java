package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.UserOrder;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public interface UserOrderDao {

    /**
     * 增加用户订购
     * @param userOrder
     * @return
     */
   int addUserOrder(UserOrder userOrder);

    /**
     * 更新用户订购
     * @param userOrder
     * @return
     */
    int  updateUserOrder(UserOrder userOrder);

    /**
     *获得用户订购
     * @param userOrder
     * @return
     */
    UserOrder getUserOrder(UserOrder userOrder);
}
