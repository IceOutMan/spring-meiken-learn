package com.meiken.controller;

import com.meiken.service.MeikenDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

/**
 * @Author glf
 * @Date 2020/8/18
 */
@RequestMapping("/ssh")
@RestController
public class TestController {



    @Autowired
    private MeikenDaoImpl meikenDaoImpl;

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){
        return meikenDaoImpl.getTeacher(2L);
    }


    @RequestMapping("/save")
    @ResponseBody
    public void save(){
        meikenDaoImpl.saveOne();
    }

    @RequestMapping("/queryByQueryDsl")
    @ResponseBody
    public void queryByQueryDsl(){
        meikenDaoImpl.queryByQueryDsl();
    }
}
