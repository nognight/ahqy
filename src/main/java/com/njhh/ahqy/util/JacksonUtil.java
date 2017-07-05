package com.njhh.ahqy.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HiWin10 on 2017/6/29.
 */
public class JacksonUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ObjectMapper mapper = new ObjectMapper().setDateFormat(sdf);



    /**
     * json string 转map
     *
     * @param sjson
     * @return
     */
    public static Map returnMap(String sjson) {
        try {
            Map map = mapper.readValue(sjson, Map.class);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json  map转 String
     *
     * @param map
     * @return
     */
    public static String mapReturnJson(Map map) {
        try {
            String sjson = mapper.writeValueAsString(map);
            return sjson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json  obj转 String
     *
     * @param o
     * @return
     */
    public static String objReturnJson(Object o) {
        try {
            String sjson = mapper.writeValueAsString(o);
            return sjson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * json转类
     *
     * @param sjson
     * @param valueType
     * @return
     */
    public static Object returnObject(String sjson, Class valueType) {
        try {
            Object o = mapper.readValue(sjson, valueType);

            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * json to list
     * @param jsonString
     * @return
     */
    public static List returnList(String jsonString) {

        try {
            List lst = mapper.readValue(jsonString, List.class);

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
