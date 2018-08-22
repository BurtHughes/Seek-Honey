package com.penguin.find.seekhoney.filter;


import com.penguin.find.seekhoney.util.Util;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 解决跨域访问过滤器
 * @Auther: Burt Hughes
 * @Date: 2018/6/23 22:13
 * @Description:
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "CrossFilter")
public class CrossFilter implements Filter {

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowOrigin = "http://127.0.0.1:3000," +
                "http://192.168.137.1:3000," +
                "http://16j38760b0.iask.in:16504," +
                "http://16j38760b0.iask.in:18821";
        allowMethods = "POST,GET,PUT,DELETE,OPTIONS";
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (!StringUtils.isEmpty(allowOrigin)) {
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            if (!Util.isListEmpty(allowOriginList)) {
                String currentOrigin = request.getHeader("Origin");
                if (allowOriginList.contains(currentOrigin)) {
                    response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                }
            }
        }
        if (!StringUtils.isEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (!StringUtils.isEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (!StringUtils.isEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (!StringUtils.isEmpty(exposeHeaders)) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
