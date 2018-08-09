package com.penguin.find.seekhoney.constant;

/**
 * 返回码列表
 */
public enum  ErrorCode {
    /* 执行成功 */
    SUCCESS(0, "成功"),
    WX_GET_SERVER_IP(1, "获取微信服务器IP地址失败"),
    WX_JS_API_AUTH_URL_EMPTY(2, "url入参不能为空"),
    REGISTER(3, "注册失败"),
    EXIST_USER(4, "用户名已存在"),
    LOGIN_ERR(5, "用户名或密码错误");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 构造方法
     * @param code 返回码
     * @param msg 返回信息
     */
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取返回码
     * @return
     */
    public int code() {
        return code;
    }

    /**
     * 获取返回信息
     * @return
     */
    public String msg() {
        return msg;
    }
}
