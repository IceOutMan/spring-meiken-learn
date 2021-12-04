package com.meiken.controller;

import com.meiken.service.AnnotationService;
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
@RequestMapping("/annotation")
@RestController
public class AnnotationTestController {

    @Autowired
    private AnnotationService annotationService;


    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    @ResponseBody
    public void sayHi(){
        annotationService.sayHi();
    }

    @RequestMapping(value = "/insertTo", method = RequestMethod.GET)
    public void insertTo(){
        annotationService.insertTo("content");
    }

}
