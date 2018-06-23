package com.penguin.find.seekhoney.vo;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应数据对象
 * @Auther: Burt Hughes
 * @Date: 2018/6/23 11:52
 * @Description: 返回至前端的数据结构
 */
public class ResponseVo {

    /* 响应码 */
    private String code = "";

    /* 响应信息 */
    private String msg = "";

    /* 下一步的URL */
    private String nextUrl = "";

    /* 返回的数据对象 */
    private Map data = new HashMap();

    /**
     * 将响应对象转换为json格式字符串
     * @return
     */
    public String toJson(){
        return JSON.toJSONString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
