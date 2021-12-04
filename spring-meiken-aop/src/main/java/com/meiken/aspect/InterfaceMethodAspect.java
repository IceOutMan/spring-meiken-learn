package com.meiken.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InterfaceMethodAspect {

    @Pointcut("execution(* com.meiken.service.InterfaceService.sayHi(..))")
    public void sayHi(){}

    @Pointcut("execution(* com.meiken.service.InterfaceService.insertTo(String)) && args(content)")
    public void insertTo(String content){}

    @Before("sayHi()")
    public void sayHiBefore(){
        System.out.println("sayHi Before:");
    }

    @Around("sayHi()")
    public void sayHiAround(ProceedingJoinPoint jp){
        System.out.println("sayHi Around Start");
        try {
            Object proceed = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("sayHi Around End");
    }

    @Before("insertTo(String)")
    public void insertToInfoBefore(){
        System.out.println("insertTo Before:");
    }

    @Around("insertTo(String)")
    public void insertToAround(ProceedingJoinPoint jp){
        System.out.println("insertTo Around Start");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("insertTo Around End");
    }


}
