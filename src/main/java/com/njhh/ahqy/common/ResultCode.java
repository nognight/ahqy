
package com.njhh.ahqy.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义全局结果码
 * Created by HiWin10 on 2017/6/14.
 */

public class ResultCode {

    //服务异常
    public static final int ERROR = -1;


    // 处理成功
    public static final int SUCCESS = 0;

    //短信验证码错误
    public static final int AUTHCODE_ERROR = 10;

    //网关返回异常
    public static final int GATEWAY_ERROR = 20;

    //无效产品
    public static final int DB_NOTFOUND = 99;

    // 数据库执行异常
    public static final int DB_EXCEPTION = 100;
    
    // 无效用户号码
    public static final int INVALID_USER_NUMBER = 500;
    
    // 无效 token
    public static final int INVALID_HH_TOKEN = 501;
    
    // 无效参数
    public static final int INVALID_PARAM = 502;
    
    //------ cache 模块的错误码 -------
    
    public static final int CACHE_SET_SESSION = 800;
    
    //------ cust 模块的错误码 -------
    
    public static final int CUST_FEEDBACK_INVALID = 1000;
    public static final int CUST_WEATHER_INVALID_PARAM = 1001;
    
    //------ user 模块的错误码 -------
    
    public static final int USER_FOL_INVALID_PARAM = 2000;
    
    public static final int USER_SCORE_EXCHANGE_FAILED = 2100;

    // 无效输入参数(如：积分兑换规则标识)
    public static final int USER_SCORE_EXCHANGE_INVALID_PARAM = 2101;

    // 积分不够
    public static final int USER_SCORE_EXCHANGE_NO_MORE = 2102;

    // 产品订购失败
    public static final int USER_SCORE_EXCHANGE_ORDER_FAIL = 2103;

    // 指定的兑换规则下无产品可订购
    public static final int USER_SCORE_EXCHANGE_NO_PRODUCTS = 2104;

    // 本月兑换次数已达上限
    public static final int USER_SCORE_EXCHANGE_COUNT_CURR_MONTH_FULL = 2105;

    // 没有存在指定的兑换规则信息
    public static final int USER_SCORE_EXCHANGE_NO_RULE = 2106;

    // 积分兑换不支持 4G
    public static final int USER_SCORE_EXCHANGE_NOT_SUPPORT_4G = 2107;
    
    public static final int USER_SCORE_TOTAL_FAILED = 2200;
    
    public static final int USER_CHECKIN_FAILED = 2500;
    public static final int USER_CHECKIN_SCORE_FAILED = 2501;
    
    public static final int USER_ORDER_CODE_PUSH_FAILED = 2600;
    public static final int USER_LOGIN_CODE_PUSH_FAILED = 2700;
    
    //------- 设置 模块的错误码 -------
    
    public static final int SETTING_BAR_INVALID_PARAM = 3000;
    public static final int SETTING_BAR_INVALID_PERIOD = 3001;
    
    // 指定了无效的 取消周期的天数
    public static final int SETTING_INVALID_PERIOD_SPECIAL = 3002;
    
    // 前后设置状态相同
    public static final int SETTING_ORDER_STATUS_EQUAL = 3003;
    
    //------- News 模块的错误码 --------
    
    public static final int NEWS_INVALID_PARAM = 4000;
    public static final int NEWS_INVALID_PARAM_CAT = 4001;
    public static final int NEWS_INVALID_PARAM_PAGE = 4002;
    public static final int NEWS_INVALID_PARAM_PAGECNT = 4003;
    public static final int NEWS_INVALID_PARAM_CNT = 4004;
    
    //------- 与 瀚宏 平台接口的错误码 --------
    
    public static final int HH_REQ_TOKEN_ERROR = 5000;
    
    //------- Product 模块的错误码 --------
    
    public static final int PRODUCT_INVALID_PARAM = 6000;

    //------- 活动模块的错误码 --------

    public static final int ACT_INVALID_PARAM = 6500;

    public static final int ACT_SK_INVALID_ROUND = 100000;
    public static final int ACT_SK_OVER_COUNT = 100001;
    public static final int ACT_SK_FINISH = 100002;
    public static final int ACT_SK_MAX_GOOD_CNT = 100003;
    public static final int ACT_SK_OVER_TIME = 100004;
    public static final int ACT_SK_NOT_ENOUGH_SCORES = 100005;

    public static final int ACT_SK_ACTIVATE_NO_LOG = 100010;
    public static final int ACT_SK_ACTIVATE_INVALID_USER = 100011;
    public static final int ACT_SK_ACTIVATE_INVALID_STATUS = 100012;
    public static final int ACT_SK_ACTIVATE_INVALID_PRODUCT = 100013;
    public static final int ACT_SK_ACTIVATE_ORDER_FAILED = 100014;

    //------- Admin 模块的错误码 --------

    public static final int ADMIN_INVALID_PARAM = 7001;
    public static final int ADMIN_BLACK_ALREADY_EXISTS = 7002;
    public static final int ADMIN_BLACK_NOT_EXISTS = 7003;
    public static final int ADMIN_USER_NOT_EXIST = 7004;
    public static final int ADMIN_ACTIVITY_INVALID = 7100;

    // H2 接口队列已满
    public static final int GW_H2_QUEUE_FULL = 10000;

    // XYL 接口队列已满
    public static final int GW_XYL_QUEUE_FULL = 11000;
    
    /**
     * 存放所有的错误码以及错误原因
     */
    public static Map<Integer, String> REASONS = new HashMap<Integer, String>();
    static {
    	REASONS.put(CUST_FEEDBACK_INVALID, "Invalid Feedback!");
        REASONS.put(GW_H2_QUEUE_FULL, "H2 queue already full");
        REASONS.put(GW_XYL_QUEUE_FULL, "XYL queue already full");
    }
    
	/**
	 * BSS接口返回的错误码

	用户档案不存在	00101
	密码错误	00102
	查找不到用户基本信息	00108
	用户已经处于开机状态	00109
	用户已经处于停机状态	00110
	用户已销户,无法开机	00111
	用户属欠费用户	00112
	用户手机号不存在	00800
	该用户处于预销状态	00801
	初始密码不允许办理业务	00802
	报停/报失强开/铉铃失败	00805
	没有此项功能代码	00806
	重复开通业务	00906
	重复关闭业务	00907
	有未完成的业务	00908
		 */    
}
