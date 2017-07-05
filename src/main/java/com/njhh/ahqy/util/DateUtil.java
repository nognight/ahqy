package com.njhh.ahqy.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HiWin10 on 2017/4/7.
 */
public  class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     判断目标时间是否为后i天
     * 如果为同一天，返回 0，如果 src > dst 返回 0
     * @param src
     * @param dst 目标时间
     * @param i  后i天
     * @return
     */
    public static int isLaterDay(Date src, Date dst ,int i) {

        if (src == null || dst == null) {
            return -2;
        }
        //转时间戳
        long time = src.getTime() - dst.getTime();
        double hours = (double)time/3600/1000;
        logger.info("时间差是："+hours+"（小时）");

        if(hours>72){
            return 0;
        }
        return -1;
    }


    /**
     * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)
     * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'
     * @param time Date 日期
     * @return String   字符串
     */
    public static String dateToString(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(time);
        return s;
    }

    /**
     * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd"(24小时制)
     * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11T17:24:21Z'
     * @param time Date 日期
     * @return String   字符串
     */
    public static String dateToStringDay(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy-MM-dd");
        String s = formatter.format(time);
        return s;
    }

    /**
     * 将java.util.Date 格式转换为字符串格式'HH:mm:ss"(24小时制)
     * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11T17:24:21Z'
     * @param time Date 日期
     * @return String   字符串
     */
    public static String dateToStringTime(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("HH:mm:ss");
        String s = formatter.format(time);
        return s;
    }


    /**
     * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)
     * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'
     * @param time Date 日期
     * @return String   字符串
     */
    public static String dateToString2(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyyMMddHHmmss");
        String s = formatter.format(time);
        return s;
    }


    /**
     * 返回一年的第几天
     *
     */
    public static int dayOfYear(Date src) {

        Calendar srcCal = Calendar.getInstance();
        srcCal.setTime(src);
        return srcCal.get(Calendar.DAY_OF_YEAR);
    }


}
