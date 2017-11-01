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
import com.xiaoleilu.hutool.date.DateUtil;
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
    public Coupon getCouponNameById(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        return couponDao.getCouponNameById(id);
    }


    @Override
    public Coupon getCouponById(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        return couponDao.getCouponById(id);
    }

    @Override
    public List<Coupon> getCouponList(int type, HttpSession httpSession) {
        Coupon coupon = new Coupon();
        coupon.setType(type);
        return couponDao.getCouponList(coupon);
    }

    @Override
    public List<UserCoupon> getUserCouponList(int type, int source, int status, HttpSession httpSession) {
        logger.info("getUserCouponList type:" + type + " source:" + source + " status:" + status);
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        return userCouponDao.getUserCouponList(user.getId(), source, status, type);

    }


    @Override
    public int addUserCoupon(int couponId, int privilegeId, int source, String startTime, int expire, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(SESSION_USER);
        if (null == user) {
            return ResultCode.ERROR;
        }
        logger.info("addUserCoupon " + " couponId " + couponId + " privilegeId " + privilegeId + " user.getPhoneNum:" + user.getPhoneNum());
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setPrivilegeId(privilegeId);
        userPrivilege.setUserId(user.getId());
        List<UserPrivilege> userPrivilegeList = userPrivilegeDao.getUserPrivilegeList(userPrivilege, 0);
        if (null == userPrivilegeList || userPrivilegeList.isEmpty()) {
            logger.info(" userPrivilegeList is null or empty");
            return ResultCode.ERROR;
        }
        userPrivilege = userPrivilegeList.get(0);
        Privilege privilege = new Privilege();
        privilege.setId(privilegeId);
        privilege = privilegeDao.getPrivilege(privilege);
        //判断权益是否是领取卡券
        if (AhqyConst.PRIVILEGE_TYPE_LQKQ != privilege.getType() && AhqyConst.PRIVILEGE_TYPE_JHKQ != privilege.getType()) {
            logger.info("is not PRIVILEGE_TYPE_LQKQ or PRIVILEGE_TYPE_JHKQ");
            return ResultCode.ERROR;
        }
        String[] couponIds = StringUtil.splitBy(privilege.getCouponIds());
        //判断卡券id是否在权益里
        if (!ArrayUtil.contains(couponIds, "" + couponId)) {
            return ResultCode.ERROR;
        }
        //取状态2的卡券
        UserCoupon uCoupon = userCouponDao.getUserCouponByCid(couponId, user.getId(), 2);
        if (null != uCoupon) {
            logger.info("userCouponDao.getUserCouponByCid " + couponId + " is exsit" + " phone : " + user.getPhoneNum());
            return 1;
        }
        UserCoupon userCoupon = new UserCoupon();

        userCoupon.setCouponId(couponId);
        userCoupon.setUserId(user.getId());
        userCoupon.setSource(source);

        if (4 == privilege.getType()) {
            logger.info("addUserCoupon privilege.getType() = 4" + " phone : " + user.getPhoneNum());
            userCoupon.setStatus(2);
        } else if (3 == privilege.getType()) {
            logger.info("addUserCoupon privilege.getType() = 3 " + " phone : " + user.getPhoneNum());
            userCoupon.setStatus(0);
        }


        userCoupon.setGetTime(new DateTime());
        DateTime startDate = new DateTime(startTime, DatePattern.NORM_DATETIME_FORMAT);
        userCoupon.setStartTime(startDate);

        Long expireTime = startDate.getTime() + expire * 3600000L;
        DateTime expireDateTime = new DateTime(expireTime);
        if (0 == expire) {
            //一个月
//                expireDateTime = startDate.offsetNew(DateField.MONTH, 1);
            //默认本月底
            expireDateTime = DateUtil.endOfMonth(new Date());
        }
        userCoupon.setExpireTime(expireDateTime);
        userCouponDao.addUserCoupon(userCoupon);

        userPrivilege.setRemark(userPrivilege.getRemark() + userCoupon.getCouponId() + "|");
        userPrivilege.setStatus(1);
        userPrivilegeDao.updatePrivilege(userPrivilege);
        return ResultCode.SUCCESS;
    }

    @Override
    public int addUserCoupons(int privilegeId, int source, String startTime, int expire, HttpSession httpSession) {
        logger.info("start to addUserCoupons privilegeId:" + privilegeId + " source:" + source + " startTime:" + startTime + " expire:" + expire);
        User user = (User) httpSession.getAttribute(SESSION_USER);
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setPrivilegeId(privilegeId);
        userPrivilege.setUserId(user.getId());
        userPrivilege.setStatus(0);
        List<UserPrivilege> userPrivilegeList = userPrivilegeDao.getUserPrivilegeList(userPrivilege, 0);
        if (null == userPrivilegeList || userPrivilegeList.isEmpty()) {
            logger.info(" userPrivilegeList is null or empty");
            return ResultCode.ERROR;
        }

        userPrivilege = userPrivilegeList.get(0);
        logger.info(userPrivilege.toString());
        Privilege privilege = new Privilege();
        privilege.setId(privilegeId);
        privilege = privilegeDao.getPrivilege(privilege);
        if (null == privilege) {
            logger.info("privilege id " + privilegeId + " is null");
            return ResultCode.ERROR;
        } else {
            //判断权益是否是领取卡券
            if (AhqyConst.PRIVILEGE_TYPE_LQKQ != privilege.getType() && AhqyConst.PRIVILEGE_TYPE_JHKQ != privilege.getType()) {
                logger.info("is not PRIVILEGE_TYPE_LQKQ or PRIVILEGE_TYPE_JHKQ");
                return ResultCode.ERROR;
            }
        }

        String[] couponIds = StringUtil.splitBy(privilege.getCouponIds());
        logger.info("addUserCoupons" + privilege.getCouponIds() + " phone : " + user.getPhoneNum());
        userPrivilege.setStatus(3);
        userPrivilege.setRemark("addUserCoupons:");
        for (String couponId : couponIds) {
            int cid = Integer.valueOf(couponId);
            //取状态2的卡券
            UserCoupon uCoupon = userCouponDao.getUserCouponByCid(cid, user.getId(), 2);
            if (null != uCoupon) {
                logger.info("userCouponDao.getUserCouponByCid " + couponId + " is exsit" + " phone : " + user.getPhoneNum());
                userPrivilege.setRemark(userPrivilege.getRemark() + "addUserCoupons id : " + couponId + " is exsit");
                userPrivilegeDao.updatePrivilege(userPrivilege);
                return 1;
            }
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setCouponId(cid);
            userCoupon.setUserId(user.getId());
            userCoupon.setSource(source);
            if (4 == privilege.getType()) {
                logger.info("addUserCoupons  privilege.getType() = 4" + " phone : " + user.getPhoneNum());
                userCoupon.setStatus(2);
            } else if (3 == privilege.getType()) {
                logger.info("addUserCoupons  privilege.getType() = 3 " + " phone : " + user.getPhoneNum());
                userCoupon.setStatus(0);
            }

            userCoupon.setGetTime(new DateTime());
            DateTime startDate = new DateTime(startTime, DatePattern.NORM_DATETIME_PATTERN);
            if (AhqyConst.INITAL_TIME.equals(startTime)) {
                startDate = new DateTime();
            }
            userCoupon.setStartTime(startDate);
            Long expireTime = startDate.getTime() + expire * 3600000L;
            DateTime expireDateTime = new DateTime(expireTime);
            if (0 == expire) {
                //一个月
//                expireDateTime = startDate.offsetNew(DateField.MONTH, 1);
                //默认本月底
                expireDateTime = DateUtil.endOfMonth(new Date());
            }

            userCoupon.setExpireTime(expireDateTime);
            userCouponDao.addUserCoupon(userCoupon);
            userPrivilege.setRemark(userPrivilege.getRemark() + userCoupon.getCouponId() + "|");
            userPrivilege.setUsedTime(new Date());
            userPrivilegeDao.updatePrivilege(userPrivilege);
        }

        userPrivilege.setStatus(1);

        userPrivilegeDao.updatePrivilege(userPrivilege);
        return ResultCode.SUCCESS;
    }

    @Override
    public int useUserCoupon(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        logger.info("start to useUserCoupon id:" + id + " user.getPhoneNum:" + user.getPhoneNum());
        UserCoupon userCoupon = userCouponDao.getUserCouponById(id, user.getId());
        Coupon coupon = couponDao.getCouponById(userCoupon.getCouponId());
        //流量券
        if (AhqyConst.COUPON_TYPE_LLQ == coupon.getType()) {
            logger.info("useUserCoupon COUPON_TYPE_LLQ");
            userCoupon.setRemark("start order");
            CouponCallback couponCallback = new CouponCallback();
            couponCallback.setUser(user);
            couponCallback.setUserCoupon(userCoupon);
            couponCallback.setPhoneNum(user.getPhoneNum());
            userService.orderProducts(StringUtil.splitBy(coupon.getProductIds()), user, couponCallback, AhqyConst.ORDER_COUPON, AhqyConst.AUTHCODE_PRIVILEGE);

            //折扣券
        } else if (AhqyConst.COUPON_TYPE_ZKQ == coupon.getType()) {
            logger.info("useUserCoupon COUPON_TYPE_ZKQ");

        }
        userCoupon.setStatus(4);
        userCouponDao.update(userCoupon);
        return 0;
    }

    @Override
    public int updateUserCoupon(int id, HttpSession httpSession) {
        logger.info("updateUserCoupon id:" + id);
        User user = (User) httpSession.getAttribute("user");
        UserCoupon userCoupon = userCouponDao.getUserCouponById(id, user.getId());

        // TODO: 2017/9/7  更新卡券状态

        userCouponDao.update(userCoupon);


        return 0;
    }
}

