package com.penguin.find.seekhoney.controller;

import com.alibaba.fastjson.JSONObject;
import com.penguin.find.seekhoney.util.Http;
import com.penguin.find.seekhoney.util.Log;
import com.penguin.find.seekhoney.util.Util;
import com.penguin.find.seekhoney.vo.ResponseVo;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Burt Hughes
 * @Date: 2018/6/11 21:36
 * @Description: 账户类控制器
 */
@RestController
@EnableAutoConfiguration
public class AcctController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        Log.info("根请求");
        return new ResponseVo().toJson();
    }
    
    /**
     * 微信Token验证
     * @param request
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

    @RequestMapping("/authSuccess")
    public String authSuccess(HttpServletRequest request) {
        Map param = Util.getParam(request);
        Log.info("授权成功:"+param.toString());
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wxaf8ebc4b41979524&secret=e92e32bacc12ae65d507d6282b6c89a4&code=";
        url += String.valueOf(param.get("code")) + "&grant_type=authorization_code";
        JSONObject obj = Http.get(url);
        String accessToken = String.valueOf(obj.get("access_token"));
        param.put("accessToken", accessToken);
        return "授权成功！" + param;
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map map) {
        String name = String.valueOf(request.getParameter("username"));
        String pwd = String.valueOf(request.getParameter("pwd"));
        if ("tom".equals(name) && "112233".equals(pwd)) {
            map.put("name", name);
            return "home";
        } else {
            return "error";
        }
    }
}
