package com.meiken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author glf
 * @Date 2021/1/19
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class CommonConfig {
    public CommonConfig(){
        System.out.println("COMMON CONFIG");
    }

    @Bean
    public AspectConfigBean aspectConfigBean(){
        return new AspectConfigBean();
    }
}
