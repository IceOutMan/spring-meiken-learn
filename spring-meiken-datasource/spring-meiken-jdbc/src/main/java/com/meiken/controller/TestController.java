package com.meiken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2020/8/19
 */
@RequestMapping
@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        int rowCount = jdbcTemplate.queryForObject("select count(*) from Teacher", Integer.class);

        System.out.println(rowCount);
    }

}
