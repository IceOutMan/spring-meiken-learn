package com.meiken.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2020/8/18
 */
@RequestMapping
@RestController
public class TestController {

    @RequestMapping(value = "/test.do",method = RequestMethod.GET)
    @ResponseBody
    public Object test(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", null);

        return jsonObject;
    }
}
