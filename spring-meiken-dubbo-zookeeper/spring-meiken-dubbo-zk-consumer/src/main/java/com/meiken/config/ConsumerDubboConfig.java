package com.meiken.config;

import com.meiken.ICommonService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author glf
 * @Date 2023/2/24
 */
@EnableDubbo(scanBasePackages = "com.meiken")
@PropertySource("classpath:dubbo.properties")
@Configuration
public class ConsumerDubboConfig {

    @DubboReference(interfaceClass = ICommonService.class)
    private ICommonService commonService;
}
