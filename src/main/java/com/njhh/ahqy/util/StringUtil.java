package com.njhh.ahqy.util;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public class StringUtil {
    public static String[] splitBy(String s) {
        String[] sArray = s.split("\\|");
        return sArray;
    }

    public static String handOrderMsg(String s) {
        String msg = s;

        if ("{".equals(s.substring(0, 1))) {
// TODO: 2017/7/14 处理4G订购返回消息。
            msg = "总部接口产品订购失败";

        }
        return msg;
    }
}
