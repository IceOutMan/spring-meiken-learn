package com.meiken.customtag;

import com.meiken.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import sun.swing.StringUIClientPropertyKey;

/**
 * @Author glf
 * @Date 2020/11/5
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    //Element 对应的类
    protected Class getBeanClass(Element element){
        return User.class;
    }

    //从Element中解析并提取对应的元素
    protected void doParse(Element element, BeanDefinitionBuilder bean){
        String userName = element.getAttribute("username");
        String email = element.getAttribute("email");

        //将提取的数据放入到BeanDefinitionBuilder中，待到完成所有bena的解析后统一注册到beanFactory中
        if(StringUtils.hasText(userName)){
            bean.addPropertyValue("username",userName);
        }
        if(StringUtils.hasText(email)){
            bean.addPropertyValue("email",email);
        }

    }
}
