package com.njhh.ahqy.test;

import com.njhh.ahqy.util.MD5Util;
import com.njhh.ahqy.util.StringUtil;

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
    }
}
