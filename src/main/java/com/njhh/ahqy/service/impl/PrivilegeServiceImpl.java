package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.*;
import com.njhh.ahqy.entity.*;
import com.njhh.ahqy.service.LoginService;
import com.njhh.ahqy.service.PrivilegeService;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.thread.OrderCallback.PrivilegeCallback;
import com.njhh.ahqy.sms.SmsClient;
import com.njhh.ahqy.util.MD5Util;
import com.njhh.ahqy.util.StringUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private PrivilegeAdDao privilegeAdDao;
    @Autowired
    private CacheDao cacheDao;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SmsRecordDao smsRecordDao;
    @Autowired
    private UserPrivilegeDao userPrivilegeDao;


    @Override
    public List<Privilege> getPrivilegeList(int type, int category, int giftType, HttpSession httpSession) {
        logger.info("getPrivilegeAdList type:" + type + " category:" + category + " giftType:" + giftType);
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setStatus(0);
        privilege.setType(type);
        privilege.setCategory(category);
        privilege.setGiftType(giftType);
        privilege.setNetType(user.getNetType());
        privilege.setPayType(user.getPayType());
        return privilegeDao.getPrivilegeList(privilege);
    }

    @Override
    public List<PrivilegeAd> getPrivilegeAdList(int type, int source, int id, HttpSession httpSession) {
        logger.info("getPrivilegeAdList type:" + type + " source:" + source + " id:" + id);
        User user = (User) httpSession.getAttribute("user");
        PrivilegeAd privilegeAd = new PrivilegeAd();

        if (0 != id) {
            privilegeAd.setId(id);
        }
        if (0 != source) {
            privilegeAd.setSource(source);
        }
        if (0 != type) {
            privilegeAd.setType(type);
        }
        privilegeAd.setStatus(0);
        return privilegeAdDao.getPrivilegeAdList(privilegeAd);
    }

    @Override
    public Privilege getPrivilegeInfo(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setId(id);
        return privilegeDao.getPrivilege(privilege);
    }

    @Override
    public Privilege getPrivilegeName(int id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        return privilegeDao.getPrivilegeName(id);
    }

    @Override
    public List<UserPrivilege> getUserPrivilegeList(HttpSession httpSession, int type) {
        logger.info("getUserPrivilegeList type:" + type);
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setUserId(user.getId());
        logger.info(userPrivilege.toString());
        return userPrivilegeDao.getUserPrivilegeList(userPrivilege, type);
    }

    @Override
    public int getAuthCode(int id, HttpSession httpSession) {
        Privilege privilege = new Privilege();
        privilege.setId(id);
        privilege = privilegeDao.getPrivilege(privilege);
        if (null == privilege) {
            return ResultCode.ERROR;
        }

        try {
            User user = (User) httpSession.getAttribute("user");
            String code = cacheDao.sendAuthCode(AhqyConst.AUTHCODE_PRIVILEGE, id, user.getId(), user.getPhoneNum(), "");
            StringBuilder content = new StringBuilder("您本次订购的验证码是[");
            content.append(code);
            content.append("]订购的权益是");
            content.append(privilege.getName());
            content.append(".五分钟内有效—[“安徽联通”微信公众号权益]");
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setSendDate(new Date());
            smsRecord.setPhoneNum(user.getPhoneNum());
            smsRecord.setSmsNum("10015888");
            smsRecord.setMsg(content.toString());
            smsRecord.setStatus(0);
            SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());
            smsRecordDao.addRecord(smsRecord);
            return ResultCode.SUCCESS;
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }


        return ResultCode.ERROR;
    }

    @Override
    public int usePrivilege(int id, String authCode, int source, HttpSession httpSession) {

        if (null == authCode) {
            return ResultCode.ERROR;
        }
        User user = (User) httpSession.getAttribute("user");
        if (!AhqyConst.AUTHCODE_WITHOUT.equals(authCode) && !AhqyConst.AUTHCODE_SMS.equals(authCode)) {
            if (!authCode.equals(cacheDao.getAuthCode(AhqyConst.AUTHCODE_PRIVILEGE, id, user.getId(), user.getPhoneNum(), ""))) {
                return ResultCode.AUTHCODE_ERROR;
            }
        }


        Privilege privilege = new Privilege();
        privilege.setId(id);
        privilege = privilegeDao.getPrivilege(privilege);
        logger.info("usePrivilege  phoneNumber:" + user.getPhoneNum() + "privilege:" + privilege.toString());

        if (null == privilege) {
            return ResultCode.DB_NOTFOUND;
        }

        //订购送
        if (AhqyConst.PRIVILEGE_TYPE_DG == privilege.getType()) {
            String[] productIds = StringUtil.splitBy(privilege.getProductIds());
//            int gitType = privilege.getGiftType();//礼物类型 1是卡券，2是产品
//            String[] giftIds = StringUtil.splitBy(privilege.getGiftId());

//            StringBuilder content = new StringBuilder("您本次订购的权益是[");
//            content.append(privilege.getName());
//            content.append("]请等待参加结果，以收到的短信为准——安徽权益");
//            SmsRecord smsRecord = new SmsRecord();
//            smsRecord.setSendDate(new Date());
//            smsRecord.setPhoneNum(user.getPhoneNum());
//            smsRecord.setSmsNum("10015888");
//            smsRecord.setMsg(content.toString());
//            smsRecord.setStatus(0);
//            SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());
//            smsRecordDao.addRecord(smsRecord);


            UserPrivilege userPrivilege = new UserPrivilege();
            userPrivilege.setPrivilegeId(privilege.getId());
            userPrivilege.setUserId(user.getId());
            userPrivilege.setGetTime(new Date());
            userPrivilege.setStatus(-1);
            userPrivilege.setSource(source);
            userPrivilege.setExpireTime(DateUtil.endOfMonth(new Date()));
            userPrivilege.setRemark("privilegeOrder:start:");

            userPrivilegeDao.addUserPrivilege(userPrivilege);


            PrivilegeCallback privilegeCallback = new PrivilegeCallback();
            privilegeCallback.setUser(user);
            privilegeCallback.setPrivilege(privilege);
            privilegeCallback.setUserPrivilege(userPrivilege);
            privilegeCallback.setPhoneNum(user.getPhoneNum());

            userService.orderProducts(productIds, httpSession, privilegeCallback, AhqyConst.ORDER_PRIVILEGE, authCode);

        }


        return ResultCode.ERROR;
    }

    @Override
    public int addUserPrivilege(int id, int source, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return ResultCode.ERROR;
        }
        logger.info("addUserPrivilege " + " id " + id + " source " + source + " user.getPhoneNum:" + user.getPhoneNum());
        Privilege privilege = new Privilege();
        privilege.setId(id);
        privilege = privilegeDao.getPrivilege(privilege);
        if(null == privilege){
            logger.info("no  privilege  id" + id);
            return ResultCode.ERROR;
        }
        //直接领取卡券
        if (3 != privilege.getType() && 4 != privilege.getType()) {
            logger.info("addUserPrivilege privilege.getType() addUserPrivilege is not allow");
            return ResultCode.ERROR;
        }

        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setPrivilegeId(id);
        userPrivilege.setUserId(user.getId());
        userPrivilege.setSource(source);
        userPrivilege.setExpireTime(new Date());
        List<UserPrivilege> userPrivilegeList = userPrivilegeDao.getUserPrivilegeList(userPrivilege, 0);
        if (null != userPrivilegeList) {
            if (userPrivilegeList.isEmpty()) {
                logger.info("userPrivilege has not exist");
            } else {
                logger.info("userPrivilege has  exist");
                return -1;
            }
        } else {
            return -1;
        }
        userPrivilege.setGetTime(new Date());
        userPrivilege.setStatus(0);
        userPrivilege.setExpireTime(DateUtil.endOfMonth(new Date()));
        userPrivilegeDao.addUserPrivilege(userPrivilege);

        return 0;
    }

    @Override
    public int usePrivilegeById(String channel, int id, String phoneNum, String timestamp, String sign, HttpSession httpSession) {


        // TODO: 2017/7/26 验证签名
        //签名规则
        //微信短信订购  MD5（channel+{#id}+{#phoneNum}+{#timestamp}+key）  md5 16位小写  timestamp10位
        //channel = smsPrivilege   key = sp123456
        //例如  md5（smsPrivilege123186516144821501054645sp123456）

        logger.info("start usePrivilegeById " + " channel:" + channel + " id:" + id + " phoneNum:" + phoneNum + " timestamp:" + timestamp + " sign:" + sign);

        try {
            long timeNow = System.currentTimeMillis() / 1000;
            long time = Long.parseLong(timestamp);
            //十分钟
            if (600 < timeNow - time || -600 > timeNow - time) {
                logger.info("request timestamp is timeout");
                return ResultCode.ERROR;
            }


        } catch (Exception e) {
            logger.warn("Exception : " + e.getMessage());
            return ResultCode.ERROR;
        }


        String key = "sp123456";
        String authSign = MD5Util.getMD5(channel + id + phoneNum + timestamp + key);
        if (!authSign.equals(sign)) {
            logger.info("sign is error , current sign is " + authSign);
            return ResultCode.ERROR;
        }

        loginService.webLogin(phoneNum, "debug123", "0", "0", "0", timestamp, sign, httpSession);

        return usePrivilege(id, AhqyConst.AUTHCODE_SMS, 1, httpSession);

    }
}
