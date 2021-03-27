package com.meiken.customtag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Author glf
 * @Date 2020/11/5
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}
