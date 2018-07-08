package com.penguin.find.seekhoney.controller;

import com.alibaba.fastjson.JSONObject;
import com.penguin.find.seekhoney.util.BeanUtil;
import com.penguin.find.seekhoney.util.Log;
import com.penguin.find.seekhoney.util.Util;
import com.penguin.find.seekhoney.util.WxUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 与微信后台交互相关接口
 */
@RestController
@EnableAutoConfiguration
public class WxController {

    /**
     * 微信Token验证
     * @param request 请求对象
     * @return
     */
    @RequestMapping("/wxTokenCheck")
    public String wxCheck(HttpServletRequest request) {
        Map param = Util.getParam(request);
        String signature = MapUtils.getString(param, "signature", "");
        String timestamp = MapUtils.getString(param, "timestamp", "");
        String nonce = MapUtils.getString(param, "nonce", "");
        String echoStr = MapUtils.getString(param, "echostr", "");
        String token = "mytoken";
        List list = new ArrayList();
        list.add(timestamp);
        list.add(token);
        list.add(nonce);
        Collections.sort(list);
        String str = (String)list.get(0) + list.get(1) + list.get(2);
        String result = DigestUtils.sha1Hex(str);
        if (signature.equals(result)) {
            return echoStr;
        } else {
            return "";
        }
    }

    /**
     * 微信网页授权回调
     * @param request 请求对象
     * @return
     */
    @RequestMapping("/authCallBack")
    public String authCallBack(HttpServletRequest request) {
        Map param = Util.getParam(request);
        String code = String.valueOf(param.get("code"));
        Log.info("微信网页授权回调参数:" + param.toString());

        // 请求微信网页授权的access_token和openid
        WxUtil wxUtil = (WxUtil) BeanUtil.getBean("wxUtil");
        JSONObject obj = wxUtil.getWebAccessToken(code);
        String accessToken = String.valueOf(obj.get("access_token"));
        String openId = String.valueOf(obj.get("openid"));

        // 请求用户信息
        JSONObject userInfo = wxUtil.getUserInfo(accessToken, openId);

//        User user = new User();
//        user.setNickname(userInfo.getString("nickname"));
//        user.setSex(userInfo.getString("sex"));
//        user.setOpenid(userInfo.getString("openid"));
//        user.setAccessToken(accessToken);
//        user.setRefreshToken(userInfo.getString("refresh_token"));
//        user.setExpiresIn(userInfo.getInteger("expires_in"));
//        user.setCountry(userInfo.getString("country"));
//        user.setProvince(userInfo.getString("province"));
//        user.setCity(userInfo.getString("city"));
//        user.setHeadimgurl(userInfo.getString("headimgurl"));
//        user.setPrivilege(userInfo.getString("privilege"));
//        userMapper.insert(user);

        return userInfo.toJSONString();
    }
}
