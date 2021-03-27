package com.meiken.controller;

import com.meiken.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private IStudentService iStudentService;

    @RequestMapping(value = "/getStudentByPage.do",method = RequestMethod.GET)
    @ResponseBody
    public Object getStudentByPage(){
        return  iStudentService.getStudentByPage(1,3);
    }

    @RequestMapping(value = "/getAll.do",method = RequestMethod.GET)
    @ResponseBody
    public Object getAll(){
        return  iStudentService.getAllStudent();
    }
}
