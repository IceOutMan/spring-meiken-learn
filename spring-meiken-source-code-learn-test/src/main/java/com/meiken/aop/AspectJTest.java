package com.meiken.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author glf
 * @Date 2020/11/9
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* com.meiken.BeanTest.test(..))")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("before test");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("after test");
    }

    @Around("test()")
    public Object arroundTest(ProceedingJoinPoint point){
        System.out.println("before1");
        Object o = null;

        try {
            o = point.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("after1");
        return o;
    }
}
