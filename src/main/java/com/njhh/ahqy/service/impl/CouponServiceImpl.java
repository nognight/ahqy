package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CouponDao;
import com.njhh.ahqy.dao.PrivilegeDao;
import com.njhh.ahqy.dao.UserCouponDao;
import com.njhh.ahqy.entity.Coupon;
import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.entity.UserCoupon;
import com.njhh.ahqy.service.CouponService;
import com.njhh.ahqy.util.DateUtil;
import com.njhh.ahqy.util.StringUtil;
import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static com.njhh.ahqy.common.AhqyConst.SESSION_USER;


/**
 * Created by HiWin10 on 2017/6/28.
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private PrivilegeDao privilegeDao;

    @Override
    public List<Coupon> getCouponList(int type, HttpSession httpSession) {
        Coupon coupon = new Coupon();
        coupon.setType(type);
        return couponDao.getCouponList(coupon);
    }

    @Override
    public List<UserCoupon> getUserCouponList(int type, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        return userCouponDao.getUserCouponList(user.getId());

    }


    @Override
    public int addUserCoupon(int couponId, int privilgeId, String startTime ,HttpSession httpSession) {

        Privilege privilege = new Privilege();
        privilege.setId(privilgeId);
        privilege = privilegeDao.getPrivilege(privilege);
        //判断权益是否是领取卡券
        if (privilege.getType() != AhqyConst.PRIVILEGE_TYPE_LQKQ) {
            return -1;
        }
        String[] couponIds = StringUtil.splitBy(privilege.getCouponIds());
        //判断卡券id是否在权益里
        if (!ArrayUtil.contains(couponIds, "" + couponId)) {
            return -1;
        }

        User user = (User) httpSession.getAttribute(SESSION_USER);
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCouponId(couponId);
        userCoupon.setUserId(user.getId());
        userCoupon.setStatus(0);
        userCoupon.setGetTime(new Date());
        Date startDate = new Date();
        if( null != startTime){
            DateTime  dateTime = new DateTime(startTime,DatePattern.NORM_DATETIME_FORMAT);
            startDate = dateTime;
        }
        userCoupon.setStartTime(startDate);

        userCouponDao.addUserCoupon(userCoupon);
        return ResultCode.SUCCESS;
    }

    @Override
    public int useUserCoupon(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        UserCoupon userCoupon = userCouponDao.getUserCouponById(id, user.getId());

        // TODO: 2017/9/7  使用卡券，进行订购等，之后更新卡券状态 考虑到异步订购，所以需要给出订购回调，更新卡券状态

        userCouponDao.update(userCoupon);
        return 0;
    }

    @Override
    public int updateUserCoupon(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        UserCoupon userCoupon = userCouponDao.getUserCouponById(id, user.getId());

        // TODO: 2017/9/7  更新卡券状态

        userCouponDao.update(userCoupon);


        return 0;
    }
}

