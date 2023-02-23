package com.meiken;

import com.meiken.config.ProviderDubboConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @Author glf
 * @Date 2023/2/23
 */

public class ProviderMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provicer-context.xml");
        context.start();

//        ProviderDubboConfig providerDubboConfig = (ProviderDubboConfig) context.getBean("providerDubboConfig");
//        System.out.println(providerDubboConfig.getName());

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
