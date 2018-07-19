package com.penguin.find.seekhoney.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 与微信后台交互工具类
 */
@Component("wxUtil")
public class WxUtil {

    /**
     * 微信appid
     */
    private static final String appId = "wxbbae07643dedb8a7";

    /**
     * 微信appSecret
     */
    private static final String appSecret = "e701593f6e6d193a4a411a1196b980ca";

    /**
     * 普通access_token
     */
    private String accessToken = "";

    /**
     * 普通access_token的有效期<br/>
     * 单位：毫秒
     */
    private long expiration = 0;

    /**
     * 上一次获取普通access_token的时间点
     */
    private long lastTime = 0;

    /**
     * 微信JS-SDK的jsapi_ticket<br/>
     * 公众号用于调用微信JS接口的临时票据，正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取
     */
    private String jsApiTicket = "";

    /**
     * 微信JS-SDK的上一次获取时间点
     */
    private long jsApiTicketLastTime = 0;

    /**
     * 微信JS-SDK的有效期
     */
    private long jsApiTicketExpiration = 0;

    /**
     * 获取普通access_token<br/>
     * 如果发现已经过期，会重新请求新的普通access_token
     * @return 普通access_token
     */
    public String getAccessToken() throws Exception {
        boolean isNeedRefresh = System.currentTimeMillis() - lastTime > expiration;
        if (isNeedRefresh) {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            Map map = new HashMap();
            map.put("grant_type", "client_credential");
            map.put("appid", appId);
            map.put("secret", appSecret);
            url += Util.getQueryString(map);
            JSONObject obj = Http.get(url);
            if (Util.isOk(obj)) {
                String errMsg = obj.getString("errmsg");
                String msg = "获取普通access_token失败:" + errMsg;
                Log.error(msg);
                throw new Exception(msg);
            } else {
                String accessToken = obj.getString("access_token");
                this.accessToken = accessToken;
                lastTime = System.currentTimeMillis();
                expiration = obj.getLong("expires_in") * 1000;//将单位转化为毫秒
                Log.info("获取普通access_token:" + accessToken);
            }
        }
        return accessToken;
    }

    /**
     * 刷新微信JS-SDK请求票据
     */
    public String getJsApiTicket() {
        boolean needRefresh = System.currentTimeMillis() - jsApiTicketLastTime > jsApiTicketExpiration;
        if (needRefresh) {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
            Map map = new HashMap();
            try {
                map.put("access_token", getAccessToken());
                map.put("type", "jsapi");
                url += Util.getQueryString(map);
                JSONObject obj = Http.get(url);
                if (Util.isOk(obj)) {
                    jsApiTicket = obj.getString("ticket");
                    jsApiTicketExpiration = obj.getLongValue("expires_in");
                    jsApiTicketLastTime = System.currentTimeMillis();
                }
            } catch (Exception e) {

            }
        }
        return jsApiTicket;
    }

    /**
     * 获取网页授权凭证
     * 微信网页授权的access_token和openid
     *
     * @param code 用于获取access_token等信息的关键码
     * @return 授权凭证
     */
    public JSONObject getWebAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("code", code);
        map.put("grant_type", "authorization_code");
        url += Util.getQueryString(map);
        JSONObject obj = Http.get(url);
        Log.info("微信网页授权凭证:" + obj);
        return obj;
    }

    /**
     * 获取微信用户信息
     *
     * @param accessToken 微信access_token
     * @param openId      微信openid
     * @return 用户信息
     */
    public JSONObject getUserInfo(String accessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        Map map = new HashMap();
        map.put("access_token", accessToken);
        map.put("openid", openId);
        map.put("lang", "zh_CN");
        url += Util.getQueryString(map);
        JSONObject obj = Http.get(url);
        Log.info("微信用户信息:" + obj);
        return obj;
    }

    /**
     * 获取微信服务器IP地址列表
     * @return IP列表
     */
    public JSONObject getWxServerIPs() throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip";
        Map map = new HashMap();
        map.put("access_token", getAccessToken());
        url += Util.getQueryString(map);
        JSONObject obj = Http.get(url);
        Object errCode = obj.getInteger("errcode");
        if (null != errCode) {
            Object errMsg = obj.getString("errmsg");
            String msg = "获取微信服务器IP地址列表失败:" + errMsg;
            Log.error(msg);
            throw new Exception(msg);
        }
        return obj;
    }

    /**
     * 校验授权凭证是否有效
     * 微信网页授权的access_token和openid
     *
     * @param accessToken 微信access_token
     * @param openId      微信openid
     * @return 授权凭证是否有效标志
     */
    public boolean checkAccessToken(String accessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/auth";
        Map map = new HashMap();
        map.put("access_token", accessToken);
        map.put("openid", openId);
        url += Util.getQueryString(map);
        JSONObject obj = Http.get(url);
        Integer errCode = (Integer) obj.get("errcode");
        return errCode.equals(0);
    }

    /**
     * 刷新微信网页授权凭证
     *
     * @param refreshToken 刷新所需token
     * @return 授权凭证
     */
    public JSONObject refreshWebAccessToken(String refreshToken) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        Map map = new HashMap();
        map.put("appid", appId);
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);
        url += Util.getQueryString(map);
        JSONObject obj = Http.get(url);
        Log.info("刷新微信网页授权凭证:" + obj);
        return obj;
    }
}
