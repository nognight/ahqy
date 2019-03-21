package com.njhh.ahqy.test;

import com.njhh.ahqy.entity.UserCoupon;
import com.njhh.ahqy.util.MD5Util;
import com.njhh.ahqy.util.StringUtil;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.lang.Console;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by HiWin10 on 2017/7/14.
 */
public class TestStr {
    public static void main(String args[]) {

//        String[] giftIds = StringUtil.splitBy("");
//        if (null == giftIds || "".equals(giftIds)) {
//
//        }

//        String[] codes = StringUtil.splitBy("9845$$00|9846$$00|9847$$00|9848$$00|9849$$00|9850$$00|9851$$00|9852$$00|9853$$00|9854$$00");
//        codes.toString();
//
//        String s = StringUtil.handOrderMsg("{\"code\":\"9999\"，\"detail\":\"checkUserInfo接口返回:[INDETERMINATE]CheckAllInfoNodeParser.cpp:6921，CRMException-8888: TradeCheck_SvcstateTradeLimit:用户具有不能办理该业务的服务状态[语音主服务.高额半停机]！$$$00001$$$;TRANSIDO为:3017071310550747.\"}");
//        System.out.println(s);

        System.out.println(MD5Util.getMD5("smsPrivilege123186516144821501054645sp123456"));

        String startTime="2017-08-08 19:23:12";
        int expire = 24;
        Date startDate = new Date();
        if (null != startTime) {
            DateTime dateTime = new DateTime(startTime, DatePattern.NORM_DATETIME_FORMAT);
            startDate = dateTime;
        }
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setGetTime(new DateTime());
        userCoupon.setStartTime(startDate);

        Long expireTime = startDate.getTime() + expire*3600000L;
        DateTime expireDateTime = new DateTime(expireTime);

        userCoupon.setExpireTime(expireDateTime);

        Console.log(userCoupon.toString());


        String[] productIds = StringUtil.splitBy("88|00");
        Console.log(productIds);

        Console.log(SecureUtil.md5("123456"));


    }
}
