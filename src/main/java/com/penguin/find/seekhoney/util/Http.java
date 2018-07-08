package com.penguin.find.seekhoney.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class Http {

    public static JSONObject get(String url) {
        JSONObject obj = new JSONObject();
        HttpClient httpClient = new HttpClient();
        HttpMethod httpMethod = new GetMethod(url);
        try {
            httpClient.executeMethod(httpMethod);
            String response = httpMethod.getResponseBodyAsString();
            String newStr = new String(response.getBytes("ISO-8859-1"), "UTF-8");
            Log.info("Http请求响应内容:" + newStr);
            obj = (JSONObject) JSONObject.parse(newStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpMethod) {
                httpMethod.releaseConnection();
            }
        }
        return obj;
    }
}
