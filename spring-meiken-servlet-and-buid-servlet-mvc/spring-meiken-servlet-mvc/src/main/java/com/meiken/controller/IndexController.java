package com.meiken.controller;

import com.meiken.bean.User;
import com.meiken.framework.GetMapping;
import com.meiken.framework.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author glf
 * @Date 2021/1/17
 */
public class IndexController {
    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return new ModelAndView("/index.html", "user", user);
    }

    @GetMapping("/hello")
    public ModelAndView hello(String name) {
        if (name == null || "".equals(name)) {
            name = "World";
        }
        return new ModelAndView("/hello.html", "name", name);
    }
}
