package com.meiken.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author glf
 * @Date 2021/1/17
 */
public class GetPatcher extends AbstractPatcher {
    Object instance;//Controller实例
    Method method;//Controller方法
    String[] parameterNames;//方法参数名
    Class<?>[] parameterClasses;//方法参数类型

    public GetPatcher(Object instance, Method method, String[] parameterNames, Class<?>[] parameterClasses) {
        this.instance = instance;
        this.method = method;
        this.parameterNames = parameterNames;
        this.parameterClasses = parameterClasses;
    }
    @Override
    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Object[] arguments = new Object[parameterClasses.length];

        for (int i = 0; i < parameterClasses.length; i++) {
            String parameterName = parameterNames[i];
            Class<?> parameterClass = parameterClasses[i];
            if(parameterClass == HttpServletRequest.class){
                arguments[i] = request;
            }else if(parameterClass == HttpServletResponse.class){
                arguments[i] = response;
            }else if(parameterClass == int.class){
                arguments[i] = Integer.valueOf(getOrDefault(request,parameterName,"0"));
            }else if(parameterClass == long.class){
                arguments[i] = Long.valueOf(getOrDefault(request,parameterName,"0"));
            }else if(parameterClass == String.class){
                arguments[i] = getOrDefault(request,parameterName,"");
            }else{
                throw new RuntimeException("Missing handler for types:" + parameterClass);
            }
        }
        return (ModelAndView) this.method.invoke(this.instance,arguments);
    }

    private String getOrDefault(HttpServletRequest request, String name, String defaultValue){
        String s = request.getParameter(name);
        return s == null ? defaultValue : s;
    }
}
