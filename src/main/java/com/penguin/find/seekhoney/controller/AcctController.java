package com.penguin.find.seekhoney.controller;

import com.alibaba.fastjson.JSONObject;
import com.penguin.find.seekhoney.constant.ErrorCode;
import com.penguin.find.seekhoney.mapper.UserMapper;
import com.penguin.find.seekhoney.model.User;
import com.penguin.find.seekhoney.util.BeanUtil;
import com.penguin.find.seekhoney.util.Log;
import com.penguin.find.seekhoney.util.WxUtil;
import com.penguin.find.seekhoney.vo.ResponseVo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public String home(HttpServletRequest request) {
        Log.info("根请求");
        return new ResponseVo().toJson();
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

    @GetMapping("/register")
    public String register() {
        User user = new User();
        user.setName("Tom");
        user.setPassword("112233");
        userMapper.insert(user);
        return "注册成功";
    }
}
