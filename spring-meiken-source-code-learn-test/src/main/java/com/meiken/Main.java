package com.meiken;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {

        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        BeanTest beanTest = (BeanTest) factory.getBean("beanTest");
        System.out.println(beanTest.getName());

        User user = (User) factory.getBean("userTest");
        System.out.println(user.getUsername() + "-" + user.getEmail());



//        ApplicationContext bf = new ClassPathXmlApplicationContext("applicationContext.xml");
//        BeanTest beanTest = (BeanTest) bf.getBean("beanTest");
//        beanTest.test();

    }
}
