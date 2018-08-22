package com.penguin.find.seekhoney.controller;

import com.penguin.find.seekhoney.mapper.ProductMapper;
import com.penguin.find.seekhoney.vo.ResponseVo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    @GetMapping("product")
    public String getAll() {
        List prdList = productMapper.getAll();
        ResponseVo responseVo = new ResponseVo();
        Map map = new HashMap();
        map.put("list", prdList);
        responseVo.setData(map);
        return responseVo.toJson();
    }
}
