package com.meiken.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2021/9/16
 */
@RequestMapping("/index")
@RestController
public class IndexController {

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    @ResponseBody
    public String ok(){
       return "OK";
    }
}
