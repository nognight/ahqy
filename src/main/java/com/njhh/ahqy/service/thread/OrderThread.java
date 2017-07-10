package com.njhh.ahqy.service.thread;


import cn.njhanhong.gateway.in.common.InCommParam;
import cn.njhanhong.gateway.in.common.InCommUri;
import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.dao.SmsRecordDao;
import com.njhh.ahqy.dao.UserDao;
import com.njhh.ahqy.dao.UserOrderDao;
import com.njhh.ahqy.dao.UserPrivilegeDao;
import com.njhh.ahqy.entity.*;
import com.njhh.ahqy.httpclient.HttpConstants;
import com.njhh.ahqy.httpclient.RestHttpClient;
import com.njhh.ahqy.httpclient.bean.GateWayApi;
import com.njhh.ahqy.httpclient.bean.ProductOrderResp;
import com.njhh.ahqy.service.UserService;
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
        logger.info("run" + user.getPhoneNum());

        int count = productList.size();
        int countTemp = 0;
        int callbackCode = 0;



        try {
            logger.info("OrderThread + phoneNum" + user.getPhoneNum());

            for (Product product : productList) {

                logger.info("OrderStart + phoneNum" + user.getPhoneNum() + " product " + product.toString());
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
                    s = restHttpClient.getHttpResponse(sbufUrl.toString(),
                            HttpConstants.Method.HTTP_METHOD_GET,
                            headers,
                            null);

                    logger.info(s);
                } catch (Exception e) {
                    logger.warn(e.getMessage());
                    countTemp = -1;
                    break;
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

                if(null != productOrderResp){

                    userOrder.setState(2);

                    if("0000".equals(productOrderResp.getRespCode())){
                        countTemp = countTemp+1;
                    }
                    userOrder.setBackCode(productOrderResp.getRespCode());
                    userOrder.setMessage(productOrderResp.getRespDesc());
                    userOrder.setBackTime(new Date());
                    userOrder.setStartTime(productOrderResp.getActiveDate());

                }else {
                    userOrder.setState(9);
                }
                userOrderDao.updateUserOrder(userOrder);

            }

            // TODO: 2017/7/2 判断订购结果



            if(count == countTemp){
                //批量订购都成功
                callbackCode=0;


            }else if(0==countTemp){
                //全失败
                callbackCode=-1;

            }else if(-1==countTemp){
                //系统错误
                callbackCode=-2;

            }else {
                //批量部分成功
                callbackCode=1;
            }


            StringBuilder content = new StringBuilder("您本次订购的");
            if(0 == callbackCode){
                content.append("全部产品订购成功。");

            }else if(-1 ==callbackCode){
                content.append("产品订购失败。我们将及时为您处理。");

            }else if( -2 == callbackCode){
                content.append("过程中发生系统错误，请稍后再试。");

            }else if( 1 == callbackCode){
                content.append("部分产品订购成功，我们将及时为您处理。");

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
            }catch (Exception e){
                e.printStackTrace();
            }
            smsRecordDao.addRecord(smsRecord);

        } catch (Exception e) {
            logger.warn(e.getMessage());

        }

        // TODO: 2017/7/2  权益订购订购后回掉
        if (AhqyConst.ORDER_PRIVILEGE == type) {
            // TODO: 2017/7/5 根据订购结果进行回掉

            PrivilegeCallback privilegeCallback = (PrivilegeCallback)orderCallback;
            UserPrivilege userPrivilege =  privilegeCallback.getUserPrivilege();

            userPrivilege.setStatus(1);
            userPrivilege.setStartTime(new Date());
            userPrivilege.setUsedTime(new Date());
            userPrivilegeDao.updatePrivilege(userPrivilege);

            Privilege privilege = privilegeCallback.getPrivilege();
            if(2 == privilege.getGiftType()){

                String[] productIds = StringUtil.splitBy(privilege.getProductIds());
                userService.orderProducts(productIds,user,AhqyConst.ORDER_WITHCODES,AhqyConst.AUTHCODE_WITHOUT);

            }
            if(0 == privilege.getGiftType()){

            }

        }


    }

    private int order(User user, Product product) {
        // TODO: 2017/7/2 发消息给gatwaye
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
                .append(InCommParam.REGION).append("=").append(userDao.getUserRegion(user))
                .append("&p=").append(AhqyConst.INCOMM_REQ_PRIO_HIGH)
                .append("&")
                .append(InCommParam.ORDER_CHANNEL).append("=").append(product.getSource());

        return 0;

    }
}
