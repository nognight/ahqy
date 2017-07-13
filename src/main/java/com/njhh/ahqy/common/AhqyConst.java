package com.njhh.ahqy.common;


import java.util.HashMap;

/**
 * 定义全局常量
 * Created by HiWin10 on 2017/6/14.
 */
public class AhqyConst {
    //gateway url
    public static final String GATEWAY = "http://192.168.100.6:8084";




    // 综合网关携带过来的用户号码字段
    public static final String HEADER_USER_NAME = "x-up-calling-line-id";

    public static final String HEADER_SOURCE_IP = "x-source-id";

    public static final String HEADER_REFERER = "referer";

    //用户登陆类型
    public static final int LOGIN_NONE = 0;
    public static final int LOGIN_WX = 1;
    public static final int LOGIN_WEB = 2;
    public static final int LOGIN_NET = 3;
    public static final int LOGIN_API = 4;


    //用户渠道类型

    public static final int CHANNEL_WX = 1;
    public static final int CHANNEL_WEB = 2;
    public static final int CHANNEL_NET = 3;


    /**
     * PRIVILEGE_TYPE
     */

    //订购送
    public static final int PRIVILEGE_TYPE_DGS = 1;



    //首月免费
    public static final int PRIVILEGE_TYPE_SYMF= 4;








    //获取验证码类型
    public static final String AUTHCODE_LOGIN = "login";
    public static final String AUTHCODE_PRIVILEGE = "privilege";
    public static final String AUTHCODE_PRODUCT = "product";
    public static final String AUTHCODE_INTENT = "intent";
    public static final String AUTHCODE_COUPON = "coupon";

    //无需验证码
    public static final String AUTHCODE_WITHOUT="debug123";



//session里放用户信息

    public static final String  SESSION_USER ="user";


    // JSON 编码
    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    //订购 退订类型
    public static final int SUBTYPE_ORDER= 0;
    public static final int SUBTYPE_UNORDER= 1;


    // 业务订购状态
    public static final int MB_STATUS_ORDER = 1;
    public static final int MB_STATUS_ORDER_CANCEL_EVER = 2;
    public static final int MB_STATUS_ORDER_CANCEL_DAY = 3;
    public static final int MB_STATUS_ORDER_CANCEL_WEEK = 4;
    public static final int MB_STATUS_ORDER_CANCEL_MONTH = 5;


    //业务订购类型
    public static final int ORDER_PRIVILEGE = 1;
    //业务订购类型权益回调订购
    public static final int ORDER_PRIVILEGE_CALLBACK = 2;

    // 返回 KEY
    public static final String K_RET = "ret";
    public static final String K_EXPAND = "expand";
    public static final String K_FLOW_NOTICE = "flowNotice";
    public static final String K_USER_NUMBER = "userNumber";
    public static final String K_ACTION = "action";
    public static final String K_RESULT_CODE = "resultCode";
    public static final String K_RESULT_MSG = "resultMsg";
    public static final String K_PRODUCT_ID = "productId";
    public static final String K_TOKEN = "token";
    public static final String K_SUMMARY = "summary";
    public static final String K_STAT = "stat";
    public static final String K_DETAIL = "detail";
    public static final String K_NET_TYPE = "netType";
    public static final String K_REAL_NAME = "realName";
    public static final String K_CONTENT = "content";
    public static final String K_PERIOD = "period";
    public static final String K_COUNT = "count";
    public static final String K_SCORE = "score";
    public static final String K_SVC = "svc";
    public static final String K_FEE_TYPE = "feeType";


    /**
     * --- microbar 显示展开方式 ---
     */

    // 不显示图标 -- 针对用户主动取消显示的
    public static final int EXPAND_NO_ICON = 0;

    // 只显示图标，但不展开(菜单 || 流量提醒)
    public static final int EXPAND_NOT = 1;

    // 显示图标，且显示提醒内容
    public static final int EXPAND_FLOW_NOTICE = 2;

    // 显示图标，且展开功能菜单
    public static final int EXPAND_FUNC = 3;

    // 显示图标，且显示扩展业务
    public static final int EXPAND_SVC = 4;

    // 显示图标，且显示扩展业务
    public static final int EXPAND_FLOWPIC = 5;

    // 流量套餐类型 KEY
    public static final String FLOW_PKG_DOMESTIC = "domestic";  // 国内
    public static final String FLOW_PKG_PROVINCE = "province";  // 省内
    public static final String FLOW_PKG_IDLE = "idle";  // 闲时
    public static final String FLOW_PKG_DIRECTION = "direction";  // 定向
    public static final String FLOW_PKG_RECHARGE = "recharge";  // 充值

    // 周期类型
    public static final int PERIOD_EVER = 2;  // 永久
    public static final int PERIOD_DAY = 3;  // 本日
    public static final int PERIOD_WEEK = 4;  // 本周
    public static final int PERIOD_MONTH = 5;  // 本月
    public static final int PERIOD_SPECIAL = 6;  // 指定周期天数

    /**
     * --- microbar 显示展开方式 ---
     */

    public static final String YWLX_GSM_HFF = "01"; //01    GSM后付费
    public static final String YWLX_CDMA_HFF = "02";//02    CDMA后付费
    public static final String YWLX_PPS = "03";//03 PPS
    public static final String YWLX_QGRYT = "04";//04   全国如意通
    public static final String YWLX_SN_PPC = "05";//05  省内PPC
    public static final String YWLX_QG_PPC = "06";//06  全国PPC
    public static final String YWLX_OTHER_NET = "07";//07   非本网号码
    public static final String YWLX_ERROR_NUM = "08";//08   号码错误
    public static final String YWLX_OCS = "09";//09 OCS用户
    public static final String YWLX_NO_HOST = "10";//10 无主用户

    // 订单状态
    public static final int ORDER_STATUS_INIT = 0;
    public static final int ORDER_STATUS_SUCC = 1;
    public static final int ORDER_STATUS_FAIL = 2;
    public static final int ORDER_STATUS_EXCEPTION = 3;
    public static final int ORDER_STATUS_PREORDER = 4;

    // 业务订购响应
    public static final String ORDER_RES_SUCC = "0000";  // 成功
    public static final String ORDER_RES_EXPIRE = "0001";  // 产品到期
    public static final String ORDER_RES_CANNOT = "0002";  // 用户无法订购

    // 业务订购来源
    public static final int XYL = 1;  // XYL接口
    public static final int AOP = 2;  // AOP接口
    public static final int ZNKF = 3;  // 总台智能客服
    public static final int XF4HALF = 4;  // 讯飞4G半年包
    public static final int AOPSP = 5; //AOP SP接口


    // 用户类型
    public static final int USER_TYPE_UNKNOWN = 0;  // 未知
    public static final int USER_TYPE_PRE = 1;  // 预付费用户
    public static final int USER_TYPE_POST = 2;  // 后付费用户

    //用户网络类型
    public static final int NET_TYPE_UNKNOWN = 0;
    public static final int NET_TYPE_2G = 1;
    public static final int NET_TYPE_3G = 2;
    public static final int NET_TYPE_4G = 3;

    //业务地址
    //public static final String REFERER_Q3RD = "http://microbar.ahhbs365.com:9080/mgr/conf/activity/raffle.html"; //3季度营销活动
    //public static final String REFERER_AOYUN = "http://microbar.ahhbs365.com:9080/mgr/conf/activity/prizes.html"; //奥运营销活动
    public static final String REFERER_Q3RD = "sdfgsfgdssfgs"; //3季度营销活动
    public static final String REFERER_AOYUN = "asgafgdsfwere"; //奥运营销活动

    // 根据品牌获取对应的用户类型
    public static int getUserTypeByBrand(String brand) {

        if (brand == null || brand.length() == 0) {
            return USER_TYPE_UNKNOWN;
        }

        if (brand.contains("ocs") || brand.contains("OCS")
                || brand.contains("预付费")) {
            return USER_TYPE_PRE;
        }

        return USER_TYPE_POST;
    }

    // 根据用户属性获取对应的用户付费类型
    public static int getUserFeeTypeByProp(int prop) {

        if (prop == 1) {  // 2G/3G 后付费
            return USER_TYPE_POST;
        } else if (prop == 2) {  // 2G/3G 预付费
            return USER_TYPE_PRE;
        }

        return USER_TYPE_UNKNOWN;
    }

    // 内部通信接口的请求优先级
    public static final int INCOMM_REQ_PRIO_HIGH = 100;
    public static final int INCOMM_REQ_PRIO_MEDIUM = 50;
    public static final int INCOMM_REQ_PRIO_LOW = 10;

    /*------ 业务类型 ------*/

    // 抽奖活动
    public static final int SVC_TYPE_ACT_WHEEL = 100;

    // 秒杀业务
    public static final int SVC_TYPE_ACK_SK = 101;
}
