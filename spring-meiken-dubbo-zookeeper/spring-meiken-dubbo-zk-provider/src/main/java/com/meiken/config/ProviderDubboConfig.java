package com.meiken.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author glf
 * @Date 2023/2/24
 */
@EnableDubbo(scanBasePackages = "com.meiken")
@Configuration
@PropertySource("classpath:dubbo.properties")
public class ProviderDubboConfig {

    public String getName(){
        return "HJHH";
    }
}
