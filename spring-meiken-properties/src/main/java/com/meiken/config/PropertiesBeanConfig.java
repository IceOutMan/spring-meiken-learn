package com.meiken.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource( value = { "classpath:meiken.2.properties" })
public class PropertiesBeanConfig {
    @Value("${spring.meiken.bean.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
