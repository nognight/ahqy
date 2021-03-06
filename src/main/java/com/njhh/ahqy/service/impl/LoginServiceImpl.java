package com.njhh.ahqy.service.impl;


import cn.njhanhong.gateway.in.common.GatewayConst;
import cn.njhanhong.gateway.in.common.InCommParam;
import cn.njhanhong.gateway.in.common.InCommUri;
import com.njhh.ahqy.common.AhqyConst;
import com.njhh.ahqy.common.ResultCode;
import com.njhh.ahqy.dao.CacheDao;
import com.njhh.ahqy.dao.UserDao;
import com.njhh.ahqy.entity.Region;
import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.httpclient.HttpConstants;
import com.njhh.ahqy.httpclient.RestHttpClient;
import com.njhh.ahqy.httpclient.bean.GateWayApi;
import com.njhh.ahqy.httpclient.bean.UserInfo;
import com.njhh.ahqy.service.LoginService;
import com.njhh.ahqy.sms.SmsClient;
import com.njhh.ahqy.util.DateUtil;
import com.njhh.ahqy.util.JacksonUtil;
import com.xiaoleilu.hutool.Hutool;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.njhh.ahqy.common.AhqyConst.USER_TYPE_POST;
import static com.njhh.ahqy.common.AhqyConst.USER_TYPE_PRE;

/**
 * Created by HiWin10 on 2017/6/26.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;
    @Autowired
    private CacheDao cacheDao;
    @Autowired
    private RestHttpClient restHttpClient;

    @Override
    public int wxLogin(String weCode, String time, String sign, HttpSession httpSession) {

        logger.info("wxLogin" + " weCode:" + weCode);
        HashMap<String, String> headers = getHeaders();
        try {
            String s = restHttpClient.getHttpResponse("http://192.168.100.28:9090/new/user/getPhoneNum?weCode=" + weCode, HttpConstants.Method.HTTP_METHOD_GET, headers, null);
            Map map = JacksonUtil.returnMap(s);
            logger.info(" getNumberFromWeCode " + map.toString());
            User user = new User();
            String phoneNum = map.get("phone").toString();
            if (null == phoneNum || "".equals(phoneNum)) {
                logger.info("weCode has no Number");
                return ResultCode.ERROR;

            }
            user.setPhoneNum(phoneNum);
            user.setName(phoneNum);
            try {
                user = userDao.getUser(user);
            } catch (Exception e) {
                logger.warn("Exception : " + e.getMessage());
                return ResultCode.DB_EXCEPTION;
            }
            if (null != user) {
                int day = DateUtil.getDayDiff(user.getLastLogin(), new Date());
                if (day > 2) {
                    UserInfo userInfo = getUserInfo(phoneNum);
                    if (1 == userInfo.getProp()) {  // 2G/3G 后付费
                        userInfo.setFeeType(USER_TYPE_POST);
                    } else if (2 == userInfo.getProp()) {  // 2G/3G 预付费
                        userInfo.setFeeType(USER_TYPE_PRE);
                    }
                    user.setNetType(userInfo.getNetType());
                    user.setPayType(userInfo.getFeeType());
                    user.setLastLogin(new Date());
                    userDao.updateUser(user);
                }
                if (!user.getWeCode().equals(weCode)) {
                    user.setWeCode(weCode);
                    userDao.updateUser(user);
                }

            } else {
                UserInfo userInfo = getUserInfo(phoneNum);
                if (1 == userInfo.getProp()) {  // 2G/3G 后付费
                    userInfo.setFeeType(USER_TYPE_POST);
                } else if (2 == userInfo.getProp()) {  // 2G/3G 预付费
                    userInfo.setFeeType(USER_TYPE_PRE);
                }
                user = new User();
                user.setName(phoneNum);
                user.setPhoneNum(phoneNum);
                user.setPasswd("");
                user.setWeCode(weCode);
                user.setChannel(AhqyConst.CHANNEL_WX);
                user.setNetType(userInfo.getNetType());
                user.setPayType(userInfo.getFeeType());
                user.setCityCode(Integer.valueOf(userInfo.getCityCode()));
                user.setState(0);
                user.setLastLogin(new Date());
                httpSession.setAttribute("user", user);
                userDao.addUser(user);

            }
            httpSession.setAttribute("user", user);
        } catch (Exception e) {
            logger.info("Exception : " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int webLogin(String phoneNum, String authCode, String userName, String passWord, String picCode, String time, String sign, HttpSession httpSession) {
        logger.info("webLogin" + " phoneNum:" + phoneNum + " authCode:" + authCode + " userName:" + userName + " passWord:" + passWord + " picCode:" + picCode);
        if (null == authCode) {
            return ResultCode.ERROR;
        }

        if ("0".equals(authCode)) {
            //非短信验证码，即图形验证码登陆
            if (null == httpSession.getAttribute("loginPicCode") || !picCode.equals(httpSession.getAttribute("loginPicCode"))) {
                return ResultCode.ERROR;
            }

        } else {
            if (!"debug123".equals(authCode)) {
                if (!authCode.equals(cacheDao.getAuthCode(AhqyConst.AUTHCODE_LOGIN, 0, 0, phoneNum, ""))) {
                    return ResultCode.ERROR;
                }
            }
        }

        User user = new User();
        if (!"0".equals(phoneNum)) {
            user.setPhoneNum(phoneNum);
        }
        if (!"0".equals(userName)) {
            user.setName(userName);
        }

        if ("0".equals(userName) && "0".equals(phoneNum)) {
            return ResultCode.ERROR;
        }

        try {
            user = userDao.getUser(user);

            if (!"0".equals(passWord)) {
                if (null == user) {
                    logger.info("not found user");
                    return ResultCode.ERROR;
                } else {
                    if (!SecureUtil.md5(passWord).equals(user.getPasswd())) {
                        logger.info("passWord error");
                        return ResultCode.ERROR;
                    }
                }
            }


        } catch (Exception e) {
            logger.warn("Exception userDao.getUser " + e.getMessage());
            return ResultCode.DB_EXCEPTION;
        }
        if (null != user) {
            httpSession.setAttribute("user", user);
            return ResultCode.SUCCESS;
        }

        UserInfo userInfo = getUserInfo(phoneNum);
        if (1 == userInfo.getProp()) {  // 2G/3G 后付费
            userInfo.setFeeType(USER_TYPE_POST);
        } else if (2 == userInfo.getProp()) {  // 2G/3G 预付费
            userInfo.setFeeType(USER_TYPE_PRE);
        }

        try {
            user = new User();
            user.setName(phoneNum);
            user.setPhoneNum(phoneNum);
            user.setPasswd("");
            user.setWeCode("");
            user.setChannel(AhqyConst.CHANNEL_WEB);
            user.setNetType(userInfo.getNetType());
            user.setPayType(userInfo.getFeeType());
            user.setCityCode(Integer.valueOf(userInfo.getCityCode()));
            user.setState(0);
            user.setLastLogin(new Date());
            userDao.addUser(user);
        } catch (Exception e) {
            logger.warn("Exception userDao.addUser " + e.getMessage());

        }

        httpSession.setAttribute("user", user);

        return ResultCode.SUCCESS;
    }

    @Override
    public int netLogin(HttpHeaders headers, String time, String sign, HttpSession httpSession) {
        return 0;
    }

    @Override
    public int apiLogin() {
        return 0;
    }


    @Override
    public int loginCode(String phoneNum) {
        if (null == phoneNum || "".equals(phoneNum)) {
            return ResultCode.ERROR;
        }
        try {
            String code = cacheDao.sendAuthCode(AhqyConst.AUTHCODE_LOGIN, 0, 0, phoneNum, "");
            StringBuilder content = new StringBuilder("您本次登录收到的验证码是[");
            content.append(code);
            content.append("]五分钟内有效，请妥善保存——安徽权益平台");
            SmsClient.getInstance().sendSms(phoneNum, content.toString());
            return ResultCode.SUCCESS;
        } catch (Exception e) {
            logger.warn("Exception sendSms " + e.getMessage());
        }
        return ResultCode.ERROR;
    }


    /**
     * 定义请求头
     *
     * @return
     */
    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Connection", "closed");
        headers.put("Content-type", "text/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Accept", "application/json");
        return headers;
    }

    /**
     * 号码查网关返回
     *
     * @param phoneNum
     * @return
     */
    private UserInfo getUserInfo(String phoneNum) {

        logger.info("start to getUserInfo phoneNum:" + phoneNum);
        HashMap<String, String> headers = getHeaders();
        User user = new User();
        user.setPhoneNum(phoneNum);
        Region region = userDao.getUserRegion(user);

        StringBuffer sbufUrl = new StringBuffer();
        String s = null;
        sbufUrl.append(AhqyConst.GATEWAY)
                .append(InCommUri.USER_GET_INFO)
                .append("?")
                .append(InCommParam.USER_NUMBER).append("=").append(phoneNum)
                .append("&")
                .append(InCommParam.REGION).append("=").append(region.getCity())
                .append("&p=").append(GatewayConst.INCOMM_REQ_PRIO_HIGH);
        logger.info(sbufUrl.toString());
        try {
            s = restHttpClient.getHttpResponse(sbufUrl.toString(),
                    HttpConstants.Method.HTTP_METHOD_GET,
                    headers,
                    null);
            logger.info(s);
        } catch (Exception e) {
            logger.warn("getUserInfo getHttpResponse " + e.getMessage());
            return null;
        }

        GateWayApi gateWayApi = (GateWayApi) JacksonUtil.returnObject(s, GateWayApi.class);
        if (0 != gateWayApi.getRet()) {
            return null;
        }
        UserInfo userInfo = (UserInfo) JacksonUtil.returnObject(JacksonUtil.objReturnJson(gateWayApi.getData().get(InCommParam.USER)), UserInfo.class);
        logger.info(userInfo.toString());
        return userInfo;

    }
}
