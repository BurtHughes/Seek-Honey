package com.penguin.find.seekhoney.model;

import java.io.Serializable;

/**
 * 用户
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2964411961913444530L;

    /**
     * 用户ID
     */
    private int id = 0;

    /**
     * 用户名
     */
    private String name = "";

    /**
     * 用户密码
     */
    private String password = "";

    /**
     * 性别
     */
    private String sex = "";

    /**
     * 国家
     */
    private String country = "";

    /**
     * 省份/州
     */
    private String province = "";

    /**
     * 城市
     */
    private String city = "";

    /**
     * 头像url
     */
    private String headimgurl = "";

    /**
     * 微信openid
     */
    private String openid = "";

    /**
     * 微信网页授权token
     */
    private String webAccessToken = "";

    /**
     * 微信网页授权刷新token
     */
    private String webRefreshToken = "";

    /**
     * 微信网页授权权限
     */
    private String privilege = "";

    /**
     * 微信网页授权有效期<br/>
     * 单位：秒
     */
    private long expiresIn = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getWebAccessToken() {
        return webAccessToken;
    }

    public void setWebAccessToken(String webAccessToken) {
        this.webAccessToken = webAccessToken;
    }

    public String getWebRefreshToken() {
        return webRefreshToken;
    }

    public void setWebRefreshToken(String webRefreshToken) {
        this.webRefreshToken = webRefreshToken;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
