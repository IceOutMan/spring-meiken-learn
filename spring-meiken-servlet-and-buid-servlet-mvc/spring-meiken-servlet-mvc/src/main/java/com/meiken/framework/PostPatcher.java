package com.meiken.framework;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author glf
 * @Date 2021/1/17
 */
public class PostPatcher extends AbstractPatcher{
    Object instance;//controller实例
    Method method;//Controller方法
    Class<?>[] parameterClasses; //方法参数映射
    ObjectMapper objectMapper;

    public PostPatcher(Object instance, Method method, Class<?>[] parameterClasses, ObjectMapper objectMapper) {
        this.instance = instance;
        this.method = method;
        this.parameterClasses = parameterClasses;
        this.objectMapper = objectMapper;
    }
    @Override
    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Object[] arguments = new Object[parameterClasses.length];

        for (int i = 0; i < parameterClasses.length; i++) {

            Class<?> parameterClass = parameterClasses[i];

            if(parameterClass == HttpServletRequest.class){
                arguments[i] = request;
            }else if(parameterClass == HttpServletResponse.class){
                arguments[i] = response;
            }else{
                //读取json并解析为 JavaBean
                BufferedReader reader = request.getReader();
                this.objectMapper.readValue(reader,parameterClass);
            }
        }
        return (ModelAndView) this.method.invoke(this.instance,arguments);
    }

    private String getOrDefault(HttpServletRequest request, String name, String defaultValue){
        String s = request.getParameter(name);
        return s == null ? defaultValue : s;
    }
}
