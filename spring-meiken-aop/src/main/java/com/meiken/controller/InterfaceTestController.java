package com.meiken.controller;

import com.meiken.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2020/9/24
 */
@RequestMapping("/interface")
@RestController
public class InterfaceTestController {

    @Autowired
    private InterfaceService meikenService;

    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    @ResponseBody
    public void sayHi(){
        meikenService.sayHi();
    }

    @RequestMapping(value = "/insertTo", method = RequestMethod.GET)
    public void insertTo(){
        meikenService.insertTo("content");
    }

}
