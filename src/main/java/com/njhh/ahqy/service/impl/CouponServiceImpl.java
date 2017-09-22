package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CouponDao;
import com.njhh.ahqy.dao.PrivilegeDao;
import com.njhh.ahqy.dao.UserCouponDao;
import com.njhh.ahqy.dao.UserPrivilegeDao;
import com.njhh.ahqy.entity.*;
import com.njhh.ahqy.service.CouponService;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.thread.OrderCallback.CouponCallback;
import com.njhh.ahqy.util.StringUtil;
import com.xiaoleilu.hutool.date.DateField;
import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CouponDao couponDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private UserPrivilegeDao userPrivilegeDao;


    @Override
    public Coupon getCouponById(int id) {

        Coupon coupon = new Coupon();
        coupon.setId(id);
        return couponDao.getCouponList(coupon).get(0);
    }

    @Override
    public List<Coupon> getCouponList(int type, HttpSession httpSession) {
        Coupon coupon = new Coupon();
        coupon.setType(type);
        return couponDao.getCouponList(coupon);
    }

    @Override
    public List<UserCoupon> getUserCouponList(int type, int source, int status, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return userCouponDao.getUserCouponList(user.getId(), source, status, type);

    }


    @Override
    public int addUserCoupon(int couponId, int privilegeId, int source, String startTime, int expire, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(SESSION_USER);
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setPrivilegeId(privilegeId);
        userPrivilege.setUserId(user.getId());
        List<UserPrivilege> userPrivilegeList = userPrivilegeDao.getUserPrivilegeList(userPrivilege);
        if (null == userPrivilegeList || userPrivilegeList.isEmpty()) {
            logger.info(" userPrivilegeList is null or empty");
            return ResultCode.ERROR;
        }

        Privilege privilege = new Privilege();
        privilege.setId(privilegeId);
        privilege = privilegeDao.getPrivilege(privilege);
        //判断权益是否是领取卡券
        if (privilege.getType() != AhqyConst.PRIVILEGE_TYPE_LQKQ) {
            return ResultCode.ERROR;
        }
        String[] couponIds = StringUtil.splitBy(privilege.getCouponIds());
        //判断卡券id是否在权益里
        if (!ArrayUtil.contains(couponIds, "" + couponId)) {
            return ResultCode.ERROR;
        }


        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCouponId(couponId);
        userCoupon.setUserId(user.getId());
        userCoupon.setSource(source);
        userCoupon.setStatus(0);
        userCoupon.setGetTime(new DateTime());
        DateTime startDate = new DateTime(startTime, DatePattern.NORM_DATETIME_FORMAT);
        userCoupon.setStartTime(startDate);

        Long expireTime = startDate.getTime() + expire * 3600000L;
        DateTime expireDateTime = new DateTime(expireTime);
        userCoupon.setExpireTime(expireDateTime);
        userCouponDao.addUserCoupon(userCoupon);
        return ResultCode.SUCCESS;
    }

    @Override
    public int addUserCoupons(int privilegeId, int source, String startTime, int expire, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(SESSION_USER);
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setPrivilegeId(privilegeId);
        userPrivilege.setUserId(user.getId());
        userPrivilege.setStatus(0);
        List<UserPrivilege> userPrivilegeList = userPrivilegeDao.getUserPrivilegeList(userPrivilege);
        if (null == userPrivilegeList || userPrivilegeList.isEmpty()) {
            logger.info(" userPrivilegeList is null or empty");
            return ResultCode.ERROR;
        }
        Privilege privilege = new Privilege();
        privilege.setId(privilegeId);
        privilege = privilegeDao.getPrivilege(privilege);
        if (null == privilege) {
            logger.info("privilege id " + privilegeId + " is null");
            return ResultCode.ERROR;
        } else {
            if (privilege.getType() != AhqyConst.PRIVILEGE_TYPE_LQKQ) {
                return ResultCode.ERROR;
            }
        }

        String[] couponIds = StringUtil.splitBy(privilege.getCouponIds());
        logger.info("addUserCoupons" + privilege.getCouponIds());
        userPrivilege.setStatus(3);
        userPrivilege.setRemark("addUserCoupons:");
        for (String couponId : couponIds) {
            int cid = Integer.valueOf(couponId);
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setCouponId(cid);
            userCoupon.setUserId(user.getId());
            userCoupon.setSource(source);
            userCoupon.setStatus(0);
            userCoupon.setGetTime(new DateTime());
            DateTime startDate = new DateTime(startTime, DatePattern.NORM_DATETIME_PATTERN);
            if (AhqyConst.INITAL_TIME.equals(startTime)) {
                startDate = new DateTime();
            }
            userCoupon.setStartTime(startDate);
            Long expireTime = startDate.getTime() + expire * 3600000L;
            DateTime expireDateTime = new DateTime(expireTime);
            //默认一个月
            if (0 == expire) {
                expireDateTime = startDate.offsetNew(DateField.MONTH, 1);
            }

            userCoupon.setExpireTime(expireDateTime);
            userCouponDao.addUserCoupon(userCoupon);
            userPrivilege.setRemark(userPrivilege.getRemark()+userCoupon.getCouponId()+"|" );
            userPrivilegeDao.updatePrivilege(userPrivilege);
        }
        userPrivilege.setStatus(1);
        userPrivilegeDao.updatePrivilege(userPrivilege);
        return ResultCode.SUCCESS;
    }

    @Override
    public int useUserCoupon(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        UserCoupon userCoupon = userCouponDao.getUserCouponById(id, user.getId());

        Coupon coupon = couponDao.getCouponById(userCoupon.getCouponId());
        //流量券
        if (AhqyConst.COUPON_TYPE_LLQ == coupon.getType()) {

            CouponCallback couponCallback = new CouponCallback();
            couponCallback.setUser(user);
            couponCallback.setUserCoupon(userCoupon);
            couponCallback.setPhoneNum(user.getPhoneNum());
            userService.orderProducts(StringUtil.splitBy(coupon.getProductIds()), user, couponCallback, AhqyConst.ORDER_COUPON, AhqyConst.AUTHCODE_PRIVILEGE);

            //折扣券
        } else if (AhqyConst.COUPON_TYPE_ZKQ == coupon.getType()) {

        }

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

