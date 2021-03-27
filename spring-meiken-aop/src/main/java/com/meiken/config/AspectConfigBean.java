package com.meiken.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author glf
 * @Date 2021/1/19
 */
@Aspect
public class AspectConfigBean {

    @Pointcut("execution(* com.meiken.config.Performance.perform(..))")
    public void perform(){}

    @Pointcut("execution(* com.meiken.config.Performance.printCount(int)) && args(num)")
    public void printCount(int num){}

    @Before("printCount(num)")
    public void printCountBefore(int num){
        System.out.println("Print Count Before :" + num);
    }


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
