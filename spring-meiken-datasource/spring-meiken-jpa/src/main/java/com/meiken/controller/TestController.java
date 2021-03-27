package com.meiken.controller;

import com.meiken.service.TeacherDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2020/8/19
 */
@RequestMapping("/jpa")
@RestController
public class TestController {


    @Autowired
    private TeacherDAOImpl teacherDAOImpl;

    @RequestMapping(value = "/test",method= RequestMethod.GET)
    public void test(){

        System.out.println();
    }


    @RequestMapping(value = "/queryDsl",method= RequestMethod.GET)
    public void queryDsl(){

        teacherDAOImpl.queryDsl();
    }
}
