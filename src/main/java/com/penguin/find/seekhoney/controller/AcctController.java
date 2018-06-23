package com.penguin.find.seekhoney.controller;

import com.penguin.find.seekhoney.vo.ResponseVo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String home() {
        return new ResponseVo().toJson();
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
