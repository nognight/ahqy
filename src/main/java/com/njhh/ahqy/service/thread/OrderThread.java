package com.njhh.ahqy.service.thread;


import cn.njhanhong.gateway.in.common.InCommParam;
import cn.njhanhong.gateway.in.common.InCommUri;
import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.dao.*;
import com.njhh.ahqy.entity.*;
import com.njhh.ahqy.httpclient.HttpConstants;
import com.njhh.ahqy.httpclient.RestHttpClient;
import com.njhh.ahqy.httpclient.bean.GateWayApi;
import com.njhh.ahqy.httpclient.bean.ProductOrderResp;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.thread.OrderCallback.CouponCallback;
import com.njhh.ahqy.service.thread.OrderCallback.OrderCallback;
import com.njhh.ahqy.service.thread.OrderCallback.PrivilegeCallback;
import com.njhh.ahqy.sms.SmsClient;
import com.njhh.ahqy.util.JacksonUtil;
import com.njhh.ahqy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HiWin10 on 2017/6/30.
 */

@Service
public class OrderThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestHttpClient restHttpClient;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private SmsRecordDao smsRecordDao;
    @Autowired
    private UserPrivilegeDao userPrivilegeDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCouponDao userCouponDao;

    private OrderCallback orderCallback;
    private int type;
    private String smsCode;
    private int subType;
    private User user;
    private List<Product> productList;


    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public OrderCallback getOrderCallback() {
        return orderCallback;
    }

    public void setOrderCallback(OrderCallback orderCallback) {
        this.orderCallback = orderCallback;
    }

    public void run() {

        int count = productList.size();
        int countTemp = 0;
        int callbackCode = -2;
        Map<String, Integer> orderMap = new HashMap<>();

        try {

            for (Product product : productList) {

                int resultCode = -2;
                String resultMsg = "";

                logger.info("OrderStart phoneNum:" + user.getPhoneNum() + "  product:" + product.getCode() + " | " + product.getName());
                UserOrder userOrder = new UserOrder();
                userOrder.setCode(product.getCode());
                userOrder.setPhoneNum(user.getPhoneNum());
                userOrder.setProductId(product.getId());
                userOrder.setUserId(user.getId());
                userOrder.setStartTime(new Date());
                userOrder.setState(1);
                userOrder.setSmsCode(smsCode);
                userOrder.setSubType(subType);
                userOrderDao.addUserOrder(userOrder);


                StringBuffer sbufUrl = new StringBuffer();
                sbufUrl.append(AhqyConst.GATEWAY)
                        .append(InCommUri.USER_ORDER_PRODUCT)
                        .append("?")
                        .append(InCommParam.USER_NUMBER).append("=").append(user.getPhoneNum())
                        .append("&")
                        .append(InCommParam.PRODUCT_CODE).append("=").append(product.getCode())
                        .append("&")
                        .append(InCommParam.SUB_TYPE).append("=").append(subType)
                        .append("&")
                        .append(InCommParam.REGION).append("=").append(userDao.getUserRegion(user).getCity())
                        .append("&p=").append(AhqyConst.INCOMM_REQ_PRIO_HIGH)
                        .append("&")
                        .append(InCommParam.ORDER_CHANNEL).append("=").append(product.getSource());

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Connection", "closed");
                headers.put("Content-type", "text/x-www-form-urlencoded; charset=UTF-8");
                headers.put("Accept", "application/json");
                String s = null;


                try {
                    logger.info("orderProduct:" + sbufUrl.toString());
                    s = restHttpClient.getHttpResponse(sbufUrl.toString(),
                            HttpConstants.Method.HTTP_METHOD_GET,
                            headers,
                            null);
                    logger.info(s);
                    if (null == s) {
                        return;
                    }
                } catch (Exception e) {
                    logger.warn(e.getMessage());
                    countTemp = -1;
                    continue;
                }

                GateWayApi gateWayApi = (GateWayApi) JacksonUtil.returnObject(s, GateWayApi.class);
                if (0 != gateWayApi.getRet()) {

                }

                String jsonOrderResp = JacksonUtil.objReturnJson(gateWayApi.getData().get(InCommParam.ORDER_RESP));

                if (jsonOrderResp.contains("returnCode")) {
                    //"returnCode":"999999","returnDesc":"操作失败"针对xf4接口返回体处理
                    String regex1 = "returnCode";
                    String regex2 = "returnDesc";
                    jsonOrderResp = jsonOrderResp.replaceAll(regex1, "respCode");
                    jsonOrderResp = jsonOrderResp.replaceAll(regex2, "respDesc");
                    String regex3 = "000000";
                    jsonOrderResp = jsonOrderResp.replaceAll(regex3, "0000");
                }
                ProductOrderResp productOrderResp = (ProductOrderResp) JacksonUtil.returnObject(jsonOrderResp, ProductOrderResp.class);

                if (null != productOrderResp) {

                    userOrder.setState(2);

                    if ("0000".equals(productOrderResp.getRespCode())) {
                        countTemp = countTemp + 1;
                        orderMap.put("orderId:" + userOrder.getId() + "=>" + userOrder.getCode(), 0);
                        resultCode = 0;

                    } else {
                        orderMap.put("orderId:" + userOrder.getId() + "=>" + userOrder.getCode(), 1);
                        resultCode = 1;
                    }
                    userOrder.setBackCode(productOrderResp.getRespCode());
                    userOrder.setMessage(productOrderResp.getRespDesc());
                    userOrder.setBackTime(new Date());
                    userOrder.setStartTime(new Date());
                    resultMsg = productOrderResp.getRespDesc();

                } else {
                    //网关异常
                    userOrder.setState(9);
                    resultCode = 9;
                    resultMsg = "订购系统维护";
                }
                userOrderDao.updateUserOrder(userOrder);
                sendProductSms(product, resultCode, resultMsg);

            }


            /**
             * 订购结果
             */
            if (count == countTemp) {
                //批量订购都成功
                callbackCode = 0;


            } else if (0 == countTemp) {
                //全失败
                callbackCode = -1;

            } else if (-1 == countTemp) {
                //系统错误
                callbackCode = -2;

            } else {
                //批量部分成功
                callbackCode = 1;
            }


//            //批量短信
//
//            StringBuilder content = new StringBuilder("您本次订购的");
//            if (0 == callbackCode) {
//                content.append("全部产品订购成功。");
//
//            } else if (-1 == callbackCode) {
//                content.append("产品订购失败。我们将及时为您处理。");
//
//            } else if (-2 == callbackCode) {
//                content.append("过程中发生系统错误，请稍后再试。");
//
//            } else if (1 == callbackCode) {
//                content.append("部分产品订购成功，我们将及时为您处理。");
//
//            }
//
//
//            SmsRecord smsRecord = new SmsRecord();
//            smsRecord.setSendDate(new Date());
//            smsRecord.setPhoneNum(user.getPhoneNum());
//            smsRecord.setSmsNum("10015888");
//            smsRecord.setMsg(content.toString());
//            smsRecord.setStatus(-1);
//
//            try {
//                SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());
//                smsRecord.setStatus(0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            smsRecordDao.addRecord(smsRecord);


        } catch (Exception e) {
            logger.warn(e.getMessage());

        }

        //订购类型，权益订购
        if (AhqyConst.ORDER_PRIVILEGE == type) {

            PrivilegeCallback privilegeCallback = (PrivilegeCallback) orderCallback;
            UserPrivilege userPrivilege = privilegeCallback.getUserPrivilege();
            userPrivilege.setStatus(-2);            //设置为增加权益失败
            if (0 == callbackCode) {
                userPrivilege.setStatus(0);//设置为可用
            }
            userPrivilege.setStartTime(new Date());

            userPrivilege.setRemark(userPrivilege.getRemark() + " orderResult：" + orderMap.toString());
            userPrivilegeDao.updatePrivilege(userPrivilege);

            if (0 == userPrivilege.getStatus()) {
                Privilege privilege = privilegeCallback.getPrivilege();


                //无赠品
                if (-1 == privilege.getGiftType()) {
                    logger.info("privilegeGift : no gift");


                }
                //赠品是流量包
                else if (3 == privilege.getGiftType()) {
                    logger.info("privilegeGift :  gift Type is product");

                    String[] giftIds = StringUtil.splitBy(privilege.getGiftId());
                    if (null != giftIds || !"".equals(giftIds)) {
                        logger.info("privilegeGift : has gift ");
                        userService.orderProducts(giftIds, user, privilegeCallback, AhqyConst.ORDER_PRIVILEGE_CALLBACK, AhqyConst.AUTHCODE_COUPON);
                    } else {
                        logger.info("privilegeGift : no gift ");
                    }

                }
                //赠品是卡券
                else if (1 == privilege.getGiftType()) {
                    logger.info("privilegeGift :  gift Type is coupon");
                    String[] couponIds = StringUtil.splitBy(privilege.getGiftId());
                    for(String couponId : couponIds){
                        int cid = Integer.valueOf(couponId);
                        UserCoupon userCoupon = new UserCoupon();
                        userCoupon.setUserId(user.getId());
                        userCoupon.setCouponId(cid);
                        userCoupon.setStatus(0);
                        userCouponDao.addUserCoupon(userCoupon);

                    }


                }
                //赠品是激活卡券
                else if (2 == privilege.getGiftType()) {
                    logger.info("privilegeGift :  gift Type is active coupon");
                    String[] couponIds = StringUtil.splitBy(privilege.getGiftId());
                    for(String couponId : couponIds){
                        int cid = Integer.valueOf(couponId);
                        UserCoupon userCoupon = userCouponDao.getUserCouponById(cid,user.getId());
                        userCoupon.setStatus(0);
                        userCouponDao.update(userCoupon);
                    }
                }

            }
            //订购类型，权益订购回调
        } else if (AhqyConst.ORDER_PRIVILEGE_CALLBACK == type) {
            logger.info("PrivilegeCallback :callbackCode = " + callbackCode);
            PrivilegeCallback privilegeCallback = (PrivilegeCallback) orderCallback;
            UserPrivilege userPrivilege = privilegeCallback.getUserPrivilege();
            userPrivilege.setStatus(2);//设置为使用失败
            if (0 == callbackCode) {
                userPrivilege.setStatus(1);//设置为已经使用
            }
            userPrivilege.setUsedTime(new Date());
            userPrivilege.setRemark(userPrivilege.getRemark() + "giftResult：" + orderMap.toString());
            userPrivilegeDao.updatePrivilege(userPrivilege);

            //订购类型卡券订购
        } else if (AhqyConst.ORDER_COUPON == type) {
            CouponCallback couponCallback = (CouponCallback) orderCallback;
            UserCoupon userCoupon = couponCallback.getUserCoupon();
            if (0 == callbackCode) {
                userCoupon.setStatus(1);

            } else if (1 == callbackCode) {
                userCoupon.setStatus(3);
            }
            userCoupon.setUsedTime(new Date());

            userCouponDao.update(userCoupon);

        }


    }


    private void sendProductSms(Product product, int resultCode, String resultMsg) {

        StringBuilder content = new StringBuilder();

        //0元赠送型
        if (-1 == product.getRetailType()) {

            if (0 == resultCode) {
                content.append("尊敬的用户，您已成功获得“安徽联通”微信公众号权益平台赠送的");
                content.append(product.getName());
                content.append(",有效期到本月底，流量不结转。");
            }

            //收费
        } else {

            content.append("您本次订购的");
            content.append(product.getName());
            content.append(",订购");
            if (0 == resultCode) {
                content.append("成功");
            } else {
                content.append("失败。");
                content.append("原因是：");

                content.append(StringUtil.handOrderMsg(resultMsg));
                logger.info(content.toString());
            }
        }

        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setSendDate(new Date());
        smsRecord.setPhoneNum(user.getPhoneNum());
        smsRecord.setSmsNum("10015888");
        smsRecord.setMsg(content.toString());
        smsRecord.setStatus(-1);

        try {
            SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());
            smsRecord.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        smsRecordDao.addRecord(smsRecord);

    }
}
