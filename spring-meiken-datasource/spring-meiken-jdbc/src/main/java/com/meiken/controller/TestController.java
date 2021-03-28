package com.meiken.controller;

import com.meiken.pojo.User;
import com.meiken.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author glf
 * @Date 2020/8/19
 */
@RequestMapping
@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        int rowCount = jdbcTemplate.queryForObject("select count(*) from Teacher", Integer.class);

        System.out.println(rowCount);
    }


    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public void insertUser(){
        User newUser = new User();
        newUser.setName("insertUser");
        newUser.setAge(1);
        userServiceImpl.save(newUser);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(){
        return userServiceImpl.getUsers();
    }


}
