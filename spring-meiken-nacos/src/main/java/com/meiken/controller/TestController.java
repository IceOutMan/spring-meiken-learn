package com.meiken.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2020/9/2
 */
@RestController
@RequestMapping("/nacos")
public class TestController {

    @NacosValue(value = "${nacosTestField:isNull}",autoRefreshed = true)
    private String nacosTestField;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        System.out.println("test");
        return nacosTestField+"";
    }
}
