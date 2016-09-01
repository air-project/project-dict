/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.air.utils.dict.demo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils {

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 判断常规对象非空
     * 判断对象是否为空,或者是是空值
     * 验证对象包含 String是否为空串如"",  List的size是否为空,  Map的size是否为空
     *
     * @param obj
     * @return boolean  true:为空  false不为空
     */
    public static boolean isBlank(Object obj) {

        boolean isBlank = true;
        if (null != obj) {

            if (obj instanceof String) {

                if (!"".equals((String) obj)) {
                    isBlank = false;
                }

            } else if (obj instanceof List) {

                if (((List) obj).size() > 0) {

                    isBlank = false;
                }

            } else if (obj instanceof Map) {

                if (!((Map) obj).isEmpty()) {

                    isBlank = false;
                }
            }
        }

        return isBlank;
    }

    public static void main(String[] args) {

        List testList = new ArrayList<>();
        String testString = null;
        Map<String,Object> testMap = new HashMap<>();
        testMap.put("1","1");
        testList.add(2);

        System.out.println(StringUtils.isBlank(testList));
        System.out.println(StringUtils.isBlank(testString));
        System.out.println(StringUtils.isBlank(testMap));


    }

}
