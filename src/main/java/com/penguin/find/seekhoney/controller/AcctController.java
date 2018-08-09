package com.penguin.find.seekhoney.controller;

import com.alibaba.fastjson.JSON;
import com.penguin.find.seekhoney.constant.ErrorCode;
import com.penguin.find.seekhoney.mapper.UserMapper;
import com.penguin.find.seekhoney.model.User;
import com.penguin.find.seekhoney.util.Log;
import com.penguin.find.seekhoney.util.Util;
import com.penguin.find.seekhoney.vo.ResponseVo;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
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
        Map inMap = Util.getJsonParam(request);
        System.out.println("请求参数:" + inMap);
        User user = new User();
        String name = MapUtils.getString(inMap, "name", "");
        String password = MapUtils.getString(inMap, "password", "");
        User existUser = userMapper.getByName(name);
        if (null != existUser && name.equals(existUser.getName()) && !StringUtils.isEmpty(name)) {
            return new ResponseVo(ErrorCode.EXIST_USER).toJson();
        }
        user.setName(name);
        user.setPassword(password);
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
        User newUser = userMapper.getByName(name);
        ResponseVo responseVo = new ResponseVo();
        boolean isNameEqual = name.equals(newUser.getName());
        boolean isPwdEqual = password.equals(newUser.getPassword());
        if (isNameEqual && isPwdEqual) {
            responseVo.setCodeAndMsg(ErrorCode.SUCCESS);
            Map retMap = new HashMap();
            retMap.put("user_name", newUser.getName());
            responseVo.setData(retMap);
        } else {
            responseVo.setCodeAndMsg(ErrorCode.REGISTER);
        }
        return responseVo.toJson();
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @PostMapping("login")
    public String login(HttpServletRequest request) {
        Map inMap = Util.getJsonParam(request);
        System.out.println("请求参数:" + inMap);
        User user = new User();
        String name = MapUtils.getString(inMap, "name", "");
        String password = MapUtils.getString(inMap, "password", "");
        User existUser = userMapper.getByName(name);
        if (null == existUser || !password.equals(existUser.getPassword())) {
            return new ResponseVo(ErrorCode.LOGIN_ERR).toJson();
        } else {
            ResponseVo responseVo = new ResponseVo(ErrorCode.SUCCESS);
            Map userInfo = new HashMap();
            userInfo.put("user_name", name);
            userInfo.put("sex", existUser.getSex());
            userInfo.put("country", existUser.getCountry());
            userInfo.put("province", existUser.getProvince());
            userInfo.put("city", existUser.getCity());
            userInfo.put("head_img", existUser.getHeadimgurl());
            responseVo.setData(userInfo);
            return responseVo.toJson();
        }
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
