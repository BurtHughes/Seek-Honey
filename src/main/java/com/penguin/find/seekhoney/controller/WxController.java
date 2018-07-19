package com.penguin.find.seekhoney.controller;

import com.alibaba.fastjson.JSONObject;
import com.penguin.find.seekhoney.constant.ErrorCode;
import com.penguin.find.seekhoney.util.*;
import com.penguin.find.seekhoney.vo.ResponseVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/WeXinServerIP")
    public String getWeXinServerIP() {
        WxUtil wxUtil = (WxUtil) BeanUtil.getBean("wxUtil");
        try {
            JSONObject result = wxUtil.getWxServerIPs();
            return new ResponseVo(result).toJson();
        } catch (Exception e) {
            return new ResponseVo(ErrorCode.WX_GET_SERVER_IP).toJson();
        }
    }

    /**
     * 获取微信jsapi_ticket<br/>
     * 微信JS-SDK注入权限验证配置时，生成签名字段时需要用到
     * @return jsapi_ticket
     */
    @GetMapping("/jsApiTicket")
    public String getJsApiTicket() {
        WxUtil wxUtil = (WxUtil) BeanUtil.getBean("wxUtil");
        return wxUtil.getJsApiTicket();
    }

    /**
     * 获取微信JS-SDK权限验证配置所需参数<br/>
     * 所需参数：appId、timestamp、nonceStr、signature
     * @return 参数Map
     */
    @GetMapping("/jsApiAuthParam")
    public String getJsApiAuthParam(HttpServletRequest request) {
        ResponseVo response = new ResponseVo(ErrorCode.SUCCESS);
        Map inMap = Util.getParam(request);
        String url = MapUtils.getString(inMap, "url", "");
        if (StringUtils.isEmpty(url)) {
            response.setCodeAndMsg(ErrorCode.WX_JS_API_AUTH_URL_EMPTY);
        }
        WxJsApi wxJsApi = (WxJsApi) BeanUtil.getBean("wxJsApiAuth");
        Map retMap = wxJsApi.getAuthParam(url);
        response.setData(retMap);
        return response.toJson();
    }
}
