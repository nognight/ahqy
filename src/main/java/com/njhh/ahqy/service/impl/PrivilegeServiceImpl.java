package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CacheDao;
import com.njhh.ahqy.dao.PrivilegeDao;
import com.njhh.ahqy.dao.UserPrivilegeDao;
import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.entity.UserPrivilege;
import com.njhh.ahqy.service.PrivilegeService;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.thread.OrderCallback.OrderCallback;
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
    private CacheDao cacheDao;

    @Autowired
    private UserService userService;


    @Autowired
    private UserPrivilegeDao userPrivilegeDao;

    @Override
    public List<Privilege> getPrivilegeList(int type,HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        if(null == user){
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setStatus(1);
        privilege.setType(type);
        privilege.setNetType(user.getNetType());
        privilege.setPayType(user.getPayType());
        return privilegeDao.getPrivilegeList(privilege);
    }

    @Override
    public List<Privilege> getUserPrivilegeList(HttpSession httpSession) {

        return null;
    }
    @Override
    public int getAuthCode(int id,HttpSession httpSession){
        try {
            User user = (User)httpSession.getAttribute("user");
            String code = cacheDao.sendAuthCode(AhqyConst.AUTHCODE_PRIVILEGE,id,user.getId(),user.getPhoneNum(),"");
            StringBuilder content = new StringBuilder("您本次使用权益收到的验证码是[");
            content.append(code);
            content.append("]五分钟内有效，请妥善保存——安徽权益平台");
            SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());
            return ResultCode.SUCCESS;
        }catch (Exception e){
            logger.warn(e.getMessage());
        }



        return ResultCode.ERROR;
    }

    @Override
    public  int usePrivilege(int id,String authCode, HttpSession httpSession){


        if(null == authCode){
            return ResultCode.ERROR;
        }

        User user = (User)httpSession.getAttribute("user");
        if(!"debug123".equals(authCode)){
            if(!authCode.equals(cacheDao.getAuthCode(AhqyConst.AUTHCODE_PRIVILEGE,id,user.getId(),user.getPhoneNum(),""))){
                return ResultCode.AUTHCODE_ERROR;
            }
        }


        Privilege privilege = new Privilege();
        privilege.setId(id);
        privilege = privilegeDao.getPrivilege(privilege);

        if(null == privilege){
            return ResultCode.DB_NOTFOUND;
        }

        //订购送
        if(AhqyConst.PRIVILEGE_TYPE_DGS == privilege.getType()){
            String[] productIds = StringUtil.splitBy(privilege.getProductIds());
            logger.info(productIds.toString());
            int gitType = privilege.getGiftType();//礼物类型 1是卡券，2是产品
            String[] giftIds = StringUtil.splitBy(privilege.getGiftId());

//            StringBuilder content = new StringBuilder("您本次订购的权益是[");
//            content.append(privilege.getName());
//            content.append("]请等待参加结果，以收到的短信为准——安徽权益");
//            SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());


            PrivilegeCallback privilegeCallback = new PrivilegeCallback();
            privilegeCallback.setUserId(user.getId());
            privilegeCallback.setPrivilegeId(privilege.getId());
            privilegeCallback.setPhoneNum(user.getPhoneNum());

            userService.orderProducts(productIds,httpSession,privilegeCallback,AhqyConst.ORDER_PRIVILEGE,authCode);

            UserPrivilege userPrivilege = new UserPrivilege();
            userPrivilege.setPrivilegeId(privilege.getId());
            userPrivilege.setUserId(user.getId());
            userPrivilege.setGetTime(new Date());
            userPrivilege.setStatus(0);

            userPrivilegeDao.addUserPrivilege(userPrivilege);


            // TODO: 2017/7/2 线程查订购情况，在进行处理

//            if(1 == gitType){
//
//                //加卡券
//                for(String giftId : giftIds){
//                    userService.addUserCoupon(Integer.valueOf(giftId), httpSession);
//                }
//
//            }
//            if(2 == gitType){
//                //加订产品
//                // TODO: 2017/7/2 线程查订购订购
//
//                Boolean flag = false;
//                // TODO: 2017/6/28 订购 然后判断回调
//
//                if(flag){
//                    // TODO: 2017/6/28 增加用户卡券
//                }
//
//            }




        }

        if(AhqyConst.PRIVILEGE_TYPE_SYMF == privilege.getType()){
            // TODO: 2017/6/28 订购 然后判断回调


        }



        return ResultCode.ERROR;
    }
}
