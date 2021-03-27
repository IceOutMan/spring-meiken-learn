package com.meiken.controller;

import com.meiken.config.LiuLaoGenBigStage;
import com.meiken.config.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2020/9/24
 */
@RequestMapping
@RestController
public class TestController {

    @Autowired
    private Performance performance;

    public TestController(){
        System.out.println("TestController");
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "TEST SUCCESS";
    }

    @RequestMapping(value = "/aopPerformance", method = RequestMethod.GET)
    public void aopPerformance(){
       performance.perform();
    }

    @RequestMapping(value = "/aopPerformanceXml", method = RequestMethod.GET)
    public void aopPerformanceXml(){
       performance.performXml();
    }

    @RequestMapping(value = "/aopPrintCount",method = RequestMethod.GET)
    public void aopPrintCount(){
        performance.printCount(10);
    }
}
