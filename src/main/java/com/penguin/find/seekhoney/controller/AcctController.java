package com.penguin.find.seekhoney.controller;

import com.alibaba.fastjson.JSON;
import com.penguin.find.seekhoney.mapper.UserMapper;
import com.penguin.find.seekhoney.model.User;
import com.penguin.find.seekhoney.util.Log;
import com.penguin.find.seekhoney.util.Util;
import com.penguin.find.seekhoney.vo.ResponseVo;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Burt Hughes
 * @Date: 2018/6/11 21:36
 * @Description: 账户类控制器
 */
@RestController
@EnableAutoConfiguration
public class AcctController {

    @Resource
    private UserMapper userMapper;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String home() {
        Log.info("根请求");
        return new ResponseVo().toJson();
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping("register")
    public String register(HttpServletRequest request) {
        Map inMap = Util.getParam(request);
        System.out.println("请求参数:"+inMap);
        User user = new User();
        user.setName(MapUtils.getString(inMap, "name", ""));
        user.setPassword(MapUtils.getString(inMap, "password", ""));
        userMapper.insert(user);
        return "注册成功";
    }

    /**
     * 查询用户
     * @param id 用户ID
     * @return
     */
    @GetMapping("user/{id}")
    public String qryUser(@PathVariable("id") int id) {
        User user = userMapper.getById(id);
        Map retMap = new HashMap();
        retMap.put("id", user.getId());
        retMap.put("name", user.getName());
        retMap.put("sex", user.getSex());
        retMap.put("country", user.getCountry());
        retMap.put("province", user.getProvince());
        retMap.put("city", user.getCity());
        retMap.put("headimgurl", user.getHeadimgurl());
        return JSON.toJSONString(retMap);
    }
}
