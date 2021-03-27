package com.meiken.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author glf
 * @Date 2020/9/2
 */
@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "localhost:8848"))
@NacosPropertySource(dataId = "spring-meiken-nacos",autoRefreshed = true)
public class NacosConfiguration {

    public NacosConfiguration(){
        System.out.println("configName");
    }

    @Bean
    public String testBean(){
        System.out.println("testBean");
        return new String("testBean");
    }

}
