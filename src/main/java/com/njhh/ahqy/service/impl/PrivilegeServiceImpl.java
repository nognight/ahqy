package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.*;
import com.njhh.ahqy.entity.*;
import com.njhh.ahqy.service.PrivilegeService;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.thread.OrderCallback.PrivilegeCallback;
import com.njhh.ahqy.sms.SmsClient;
import com.njhh.ahqy.util.StringUtil;
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
    private SmsRecordDao smsRecordDao;


    @Autowired
    private UserPrivilegeDao userPrivilegeDao;


    @Override
    public List<Privilege> getPrivilegeList(int type, int category, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setStatus(0);
        privilege.setType(type);
        privilege.setCategory(category);
        privilege.setNetType(user.getNetType());
        privilege.setPayType(user.getPayType());
        return privilegeDao.getPrivilegeList(privilege);
    }

    @Override
    public List<PrivilegeAd> getPrivilegeAdList(int type,int source, int id, HttpSession httpSession) {
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
    public List<UserPrivilege> getUserPrivilegeList(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            return null;
        }
        UserPrivilege userPrivilege = new UserPrivilege();
        userPrivilege.setUserId(user.getId());


        return userPrivilegeDao.getUserPrivilegeList(userPrivilege);
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
    public int usePrivilege(int id, String authCode, HttpSession httpSession) {


        if (null == authCode) {
            return ResultCode.ERROR;
        }

        User user = (User) httpSession.getAttribute("user");
        if (!AhqyConst.AUTHCODE_WITHOUT.equals(authCode)) {
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
        if (AhqyConst.PRIVILEGE_TYPE_DGS == privilege.getType()) {
            String[] productIds = StringUtil.splitBy(privilege.getProductIds());
            int gitType = privilege.getGiftType();//礼物类型 1是卡券，2是产品
            String[] giftIds = StringUtil.splitBy(privilege.getGiftId());

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
            userPrivilege.setRemark("prililege:start:");

            userPrivilegeDao.addUserPrivilege(userPrivilege);


            PrivilegeCallback privilegeCallback = new PrivilegeCallback();
            privilegeCallback.setUser(user);
            privilegeCallback.setPrivilege(privilege);
            privilegeCallback.setUserPrivilege(userPrivilege);
            privilegeCallback.setPhoneNum(user.getPhoneNum());

            userService.orderProducts(productIds, httpSession, privilegeCallback, AhqyConst.ORDER_PRIVILEGE, authCode);

        }
        //首月免费
        if (AhqyConst.PRIVILEGE_TYPE_SYMF == privilege.getType()) {
            // TODO: 2017/6/28 订购 然后判断回调


        }


        return ResultCode.ERROR;
    }
}
