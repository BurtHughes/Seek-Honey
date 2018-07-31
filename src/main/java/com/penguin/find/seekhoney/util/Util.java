package com.penguin.find.seekhoney.util;

import com.alibaba.fastjson.JSONObject;
import com.penguin.find.seekhoney.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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

    public static Map getParam(HttpServletRequest request, String type) {
        Map param = new HashMap();
        try {
            InputStream is = request.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader reader1 = new BufferedReader(reader);
            String line = "";
            StringBuffer str = new StringBuffer();
            while ((line=reader1.readLine()) != null) {
                str.append(line);
            }
            Log.info(str.toString());
            param = (Map) JSONObject.parse(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return param;
    }

    /**
     * 将Map转换为查询串
     * @param map 待转换的Map
     * @return 查询串
     */
    public static String getQueryString(Map map) {
        String queryString = "?";
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (queryString.length() == 1) {
                queryString += key + "=" + value;
            } else {
                queryString += "&" + key + "=" + value;
            }
        }
        return queryString;
    }

    /**
     * 判断请求微信接口是否成功
     * @param object 返回对象
     * @return 成功与否标志
     */
    public static boolean isOk(JSONObject object) {
        Integer errCode = object.getInteger("errcode");
        return null == errCode || 0 == errCode;
    }

}
