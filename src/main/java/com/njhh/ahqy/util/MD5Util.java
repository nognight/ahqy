package com.njhh.ahqy.util;
import java.security.MessageDigest;

/**
 * Created by HiWin10 on 2017/5/4.
 */
public class MD5Util {
    /**
     *  对字符串md5加密
     *
     * @param plainText
     * @return
     */


    public static String HEXAndMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try {
                md.update(plainText.getBytes("UTF8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer(200);
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset] & 0xff;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获得MD5加密字符串
     *
     * @param source 源字符串
     *
     * @return 加密后的字符串
     *
     */
    public static String getMD5(String source) {
        String mdString = null;
        if (source != null) {
            try {
        //关键是这句
                mdString = getMD5(source.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mdString;
    }

    /**
     * 获得MD5加密字符串
     *
     * @param source 源字节数组
     *
     * @return 加密后的字符串
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char [] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        final int temp = 0xf;
        final int arraySize = 32;
        final int strLen = 16;
        final int offset = 4;
        try {
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte [] tmp = md.digest();
            char [] str = new char[arraySize];
            int k = 0;
            for (int i = 0; i < strLen; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> offset & temp];
                str[k++] = hexDigits[byte0 & temp];
            }
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
