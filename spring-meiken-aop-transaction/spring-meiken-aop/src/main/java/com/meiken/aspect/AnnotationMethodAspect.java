package com.meiken.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationMethodAspect {

    @Pointcut(value = "@annotation(com.meiken.annotation.LogAnnotation)")
    public void annotationAspect(){

    }

    @Before("annotationAspect()")
    public void annotationAspectBefore(){
        System.out.println("annotationAspect Before:");
    }

    @Around("annotationAspect()")
    public void annotationAspectAround(ProceedingJoinPoint jp){
        System.out.println("Annotation sayHi Around Start");
        try {
            Object proceed = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Annotation sayHi Around End");
    }


}
