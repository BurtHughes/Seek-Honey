package com.penguin.find.seekhoney.util;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Auther: Burt Hughes
 * @Date: 2018/6/23 22:22
 * @Description:
 */
public class Util {

    /**
     * 判断List是否为空
     * @param list
     * @return
     */
    public static boolean isListEmpty(List list) {
        return null == list || list.isEmpty();
    }
    
    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public static Map getParam(HttpServletRequest request) {
        Map param = new HashMap();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            String value = request.getParameter(key);
            param.put(key, value);
        }
        return param;
    }
}
