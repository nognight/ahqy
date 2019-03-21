package com.njhh.ahqy.test;

import cn.njhanhong.gateway.in.common.InCommParam;
import cn.njhanhong.gateway.in.model.UserProductOrderInfo;
import com.njhh.ahqy.httpclient.bean.GateWayApi;
import com.njhh.ahqy.httpclient.bean.UserInfo;
import com.njhh.ahqy.util.JacksonUtil;
import com.njhh.ahqy.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public class Test {
    public static void main(String args[]){
        String[] sA=StringUtil.splitBy("123");
        for (String s:sA) {
            System.out.println(s.toString()+"  |  ");
        }

        int a = (int)(Math.random()*9000+1000);
        System.out.println(a);



        String s="{\"ret\":0,\"data\":{\"orders\":[{\"orderId\":\"1326737549\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"v99002147\",\"productName\":\"沃邮箱\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2014-06-29 17:15:22\",\"effectiveDate\":\"2014-07-01 00:00:00\",\"disabledDate\":\"2044-07-01 00:00:00\"},{\"orderId\":\"1326737550\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"99002147\",\"productName\":\"ESS-66元基本套餐A\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2014-06-29 17:15:22\",\"effectiveDate\":\"2014-07-01 00:00:00\",\"disabledDate\":\"2044-07-01 00:00:00\"},{\"orderId\":\"1458525354\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"94630000\",\"productName\":\"LTE功能包-后付\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2016-10-06 16:34:40\",\"effectiveDate\":\"2016-10-06 00:00:00\",\"disabledDate\":\"2050-01-01 00:00:00\"},{\"orderId\":\"1332711110\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"99200000\",\"productName\":\"WLAN功能\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2014-08-05 11:15:58\",\"effectiveDate\":\"2014-08-05 00:00:00\",\"disabledDate\":\"2050-01-01 00:00:00\"},{\"orderId\":\"1332711114\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"99004817\",\"productName\":\"WLAN 0元体验套餐\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2014-08-05 11:15:58\",\"effectiveDate\":\"2014-08-05 00:00:00\",\"disabledDate\":\"2050-01-01 00:00:00\"},{\"orderId\":\"1351152150\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"93830000\",\"productName\":\"0元包30小时WLAN-ESS后\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2014-10-28 18:14:41\",\"effectiveDate\":\"2014-10-28 00:00:00\",\"disabledDate\":\"2050-01-01 00:00:00\"},{\"orderId\":\"1382368362\",\"subscribeType\":1,\"orderMethod\":1,\"serviceType\":1,\"payMDN\":\"10010\",\"productId\":\"prm01153\",\"productName\":\"多方聊体验版\",\"spName\":\"SP名称\",\"subscriptionTime\":\"2015-04-16 11:00:26\",\"effectiveDate\":\"2015-04-16 00:00:00\",\"disabledDate\":\"2045-04-16 00:00:00\"}]}}";



        GateWayApi gateWayApi = (GateWayApi) JacksonUtil.returnObject(s, GateWayApi.class);
        if (0 != gateWayApi.getRet()) {

        }
        List list =  JacksonUtil.returnList(JacksonUtil.objReturnJson(gateWayApi.getData().get(InCommParam.ORDERS)));
        System.out.println(list.toString());

        UserProductOrderInfo userProductOrderInfo  =(UserProductOrderInfo) JacksonUtil.returnObject(JacksonUtil.objReturnJson(list.get(1)),UserProductOrderInfo.class);
        System.out.print(userProductOrderInfo.getProductId());

    }

}
