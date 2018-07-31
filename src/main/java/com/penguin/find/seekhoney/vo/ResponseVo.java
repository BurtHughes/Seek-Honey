package com.penguin.find.seekhoney.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.penguin.find.seekhoney.constant.ErrorCode;

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
    private int code = -1;

    /* 响应信息 */
    private String msg = "";

    /* 返回的数据对象 */
    private Map data = new HashMap();

    /**
     * 构造方法
     */
    public ResponseVo() {

    }

    /**
     * 构造方法
     */
    public ResponseVo(ErrorCode errorCode) {
        this.code = errorCode.code();
        this.msg = errorCode.msg();
    }

    /**
     * 构造方法
     * @param code 返回码
     * @param msg 返回信息
     */
    public ResponseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造方法
     * @param map 返回数据
     */
    public ResponseVo(Map map) {
        data = map;
        this.code = ErrorCode.SUCCESS.code();
        this.msg = ErrorCode.SUCCESS.msg();
    }

    /**
     * 构造方法
     * @param json 返回数据
     */
    public ResponseVo(JSONObject json) {
        data = json;
        this.code = ErrorCode.SUCCESS.code();
        this.msg = ErrorCode.SUCCESS.msg();
    }

    /**
     * 设置返回码和返回信息
     * @param errorCode 返回码以及返回信息
     */
    public void setCodeAndMsg(ErrorCode errorCode) {
        this.code = errorCode.code();
        this.msg = errorCode.msg();
    }

    /**
     * 将响应对象转换为json格式字符串
     * @return
     */
    public String toJson() {
        return JSON.toJSONString(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
