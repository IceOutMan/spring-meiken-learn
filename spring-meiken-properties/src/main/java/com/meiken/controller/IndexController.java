package com.meiken.controller;

import com.meiken.config.PropertiesBeanConfig;
import com.meiken.config.PropertiesXmlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private PropertiesBeanConfig propertiesBeanConfig;
    @Autowired
    private PropertiesXmlConfig propertiesXmlConfig;
    @Autowired
    private PropertiesXmlConfig otherPropertiesXmlConfig;

    @RequestMapping(value = "/properties",method = RequestMethod.GET)
    @ResponseBody
    public Object index(){
        Map<String,Object> result = new HashMap<>();

        result.put("bean", propertiesBeanConfig);
        result.put("xml", propertiesXmlConfig);
        result.put("otherXml", otherPropertiesXmlConfig);

        return result;
    }

}
