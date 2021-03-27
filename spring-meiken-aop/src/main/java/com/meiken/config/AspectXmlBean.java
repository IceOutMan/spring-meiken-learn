package com.meiken.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author glf
 * @Date 2021/1/19
 */
@Aspect
public class AspectXmlBean {
    @Pointcut("execution(* com.meiken.config.Performance.performXml(..))")
    public void perform(){}

    @Before("perform()")
    public void performBefore(){
        System.out.println("Perform Before");
    }

    @After("perform()")
    public void performAfter(){
        System.out.println("Perform After");
    }

    @Around("perform()")
    public void performAround(ProceedingJoinPoint jp){
        System.out.println("Perform Around Start");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Perform Around End");
    }


    @AfterThrowing("perform()")
    public void performAfterThrowing(){
        System.out.println("Perform After Throwing");
    }
}
