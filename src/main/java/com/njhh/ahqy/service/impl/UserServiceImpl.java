package com.njhh.ahqy.service.impl;

import cn.njhanhong.gateway.in.common.InCommParam;
import cn.njhanhong.gateway.in.common.InCommUri;
import cn.njhanhong.gateway.in.model.UserProductOrderInfo;
import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CacheDao;
import com.njhh.ahqy.dao.ProductDao;
import com.njhh.ahqy.dao.UserCouponDao;
import com.njhh.ahqy.dao.UserDao;
import com.njhh.ahqy.entity.Product;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.entity.UserCoupon;
import com.njhh.ahqy.httpclient.HttpConstants;
import com.njhh.ahqy.httpclient.RestHttpClient;
import com.njhh.ahqy.httpclient.bean.GateWayApi;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.service.thread.OrderCallback.OrderCallback;
import com.njhh.ahqy.service.thread.OrderThread;
import com.njhh.ahqy.sms.SmsClient;
import com.njhh.ahqy.util.JacksonUtil;
import com.njhh.ahqy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.njhh.ahqy.common.AhqyConst.SESSION_USER;

/**
 * Created by HiWin10 on 2017/6/27.
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;
    @Autowired
    private CacheDao cacheDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserCouponDao userCouponDao;

    @Autowired
    private RestHttpClient restHttpClient;


    @Autowired
    private OrderThread orderThread;


    @Override
    public int orderByProductId(int productId, String authCode, HttpSession httpSession) {
        // TODO: 2017/6/27 验证订购码

        // TODO: 2017/6/27 往网关发订购消息

        return ResultCode.ERROR;
    }


    @Override
    public int orderProducts(String[] productIds, HttpSession httpSession, OrderCallback orderCallback, int type, String smsCode) {
        logger.info("orderProducts httpSession" + " productIds:" + productIds.toString() + " orderCallback:" + orderCallback.toString() + " type:" + type + " smsCode:" + smsCode);
        if (0 == productIds.length) {
            logger.warn("orderProducts无产品id");
            return 0;
        }

        List<Product> productList = new ArrayList<>();
        User user = (User) httpSession.getAttribute(SESSION_USER);
        productList = getProductList(productIds, user);
        logger.info("getProductList: " + productList.toString());
        if (0 == productList.size()) {
            logger.warn("getProductList无产品id");
            return 0;
        }
        orderThread.setUser(user);
        orderThread.setProductList(productList);
        orderThread.setOrderCallback(orderCallback);
        orderThread.setType(type);
        orderThread.setSubType(AhqyConst.SUBTYPE_ORDER);
        orderThread.setSmsCode(smsCode);

        Thread thread = new Thread(orderThread);
        thread.start();
        logger.info("StartOrderThread:" + thread.getId() + "  phoneNum:" + user.getPhoneNum());

        return 0;
    }


    @Override
    public int orderProducts(String[] productIds, User user, OrderCallback orderCallback, int type, String smsCode) {
        logger.info("orderProducts user" + " productIds:" + productIds.toString() + " orderCallback:" + orderCallback.toString() + " type:" + type + " smsCode:" + smsCode);
        if (0 == productIds.length) {
            logger.warn("无产品id");
            return 0;
        }

        List<Product> productList = new ArrayList<>();

        productList = getProductList(productIds, user);
        logger.info(productList.toString());

        if (0 == productList.size()) {
            logger.warn("无产品id");
            return 0;
        }
        orderThread.setUser(user);
        orderThread.setProductList(productList);
        orderThread.setOrderCallback(orderCallback);
        orderThread.setType(type);

        orderThread.setSubType(AhqyConst.SUBTYPE_ORDER);
        orderThread.setSmsCode(smsCode);

        Thread thread = new Thread(orderThread);
        thread.start();
        logger.info("StartOrderThread:" + thread.getId() + "  phoneNum:" + user.getPhoneNum());

        return 0;
    }


    @Override
    public int sendAuthCode(String type, Integer id, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        String code = cacheDao.sendAuthCode(type, id, user.getId(), user.getPhoneNum(), "");
        StringBuilder content = new StringBuilder("您本次操作收到的验证码是[");
        content.append(code);
        content.append("]五分钟内有效，请妥善保存——安徽权益平台");
        SmsClient.getInstance().sendSms(user.getPhoneNum(), content.toString());

        return ResultCode.ERROR;
    }

    @Override
    public int updateUser(HttpSession httpSession) {
        logger.info("updateUser");
        User user = new User();
        userDao.updateUser(user);
        return 0;
    }


    private List<String> getUserOrdered(User user) {
        StringBuffer sbufUrl = new StringBuffer();
        sbufUrl.append(AhqyConst.GATEWAY)
                .append(InCommUri.USER_ORDER_LIST)
                .append("?")
                .append(InCommParam.USER_NUMBER).append("=").append(user.getPhoneNum())
                .append("&")
                .append(InCommParam.REGION).append("=").append(userDao.getUserRegion(user).getCity())
                .append("&p=").append(AhqyConst.INCOMM_REQ_PRIO_HIGH)
                .append("&netType=").append(user.getNetType());

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Connection", "closed");
        headers.put("Content-type", "text/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Accept", "application/json");
        String s = null;


        try {
            logger.info("getUserOrdered :" + sbufUrl.toString());
            s = restHttpClient.getHttpResponse(sbufUrl.toString(),
                    HttpConstants.Method.HTTP_METHOD_GET,
                    headers,
                    null);

            logger.info(s);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }

        GateWayApi gateWayApi = (GateWayApi) JacksonUtil.returnObject(s, GateWayApi.class);
        if (0 != gateWayApi.getRet()) {
            return null;
        }
        List list = JacksonUtil.returnList(JacksonUtil.objReturnJson(gateWayApi.getData().get(InCommParam.ORDERS)));

        if (null != list) {
            List<String> stringList = new ArrayList<>();

            for (Object temp : list) {
                UserProductOrderInfo info = (UserProductOrderInfo) JacksonUtil.returnObject(JacksonUtil.objReturnJson(temp), UserProductOrderInfo.class);
                stringList.add(info.getProductId());
            }

            return stringList;
        }
        return null;


    }

    private List<Product> getProductList(String[] productIds, User user) {
        logger.info("start to getProductList");
        List<Product> productList = new ArrayList<>();
        List<String> orderedCodeList = getUserOrdered(user);
        logger.info("orderedCodeList" + orderedCodeList.toString());
        if (null != orderedCodeList) {
            Product product = new Product();
            for (String productId : productIds) {
                product.setId(Integer.valueOf(productId));
                product = productDao.getProductById(product);
                if (orderedCodeList.contains(product.getCode())) {
                    logger.info("已经订购code:" + product.getCode());

                    //单个code的时候已经订购不剔除，网关取判断逻辑。
//                    break;
                }
                //多个code的
                if (0 == product.getHasCodes()) {
                    String[] codes = StringUtil.splitBy(product.getCodes());
                    for (String code : codes) {
                        if (orderedCodeList.contains(code)) {
                            logger.info("已经订购codes:" + code);
                        } else {
                            product.setCode(code);
                            //预定的code 视为已定，防止重复code
                            orderedCodeList.add(code);
                        }
                    }
                }
                if (!"".equals(product.getCode())) {
                    productList.add(product);
                }
            }
            return productList;
        } else {
            logger.warn("can not get user ordered try to order");
            Product product = new Product();
            for (String productId : productIds) {
                product.setId(Integer.valueOf(productId));
                product = productDao.getProductById(product);
                productList.add(product);
            }
        }
        return productList;
    }
}
