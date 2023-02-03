package com.meiken.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.applet.AppletContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @Author glf
 * @Date 2020/8/18
 */
@RequestMapping("mq/")
@RestController
public class TestController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final static String QUEUE_NAME = "XMLY_MEIKEN_QUEUE";

    @Autowired
    private ApplicationContext  applicationContext;

    @RequestMapping(value = "/test.do",method = RequestMethod.GET)
    @ResponseBody
    public Object test(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", null);

        Object bean = applicationContext.getBean(PropertyPlaceholderConfigurer.class);

        return bean;
    }

    @RequestMapping(value = "/push.do",method = RequestMethod.GET)
    @ResponseBody
    public void mq(){
//        nativePush();
        amqpTemplate.convertAndSend("XMLY_MEIKEN_EXCHANGE","XMLY_MEIKEN_KEY","HELLO");
    }
    private void nativePush(){
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("47.98.193.213");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");


        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            for (int i = 0; i < 5; i++) {
                String message = i + " Message";
                byte[] messageBody = message.getBytes(StandardCharsets.UTF_8);
                channel.basicPublish("", QUEUE_NAME, null, messageBody);
                System.out.println("[X]:Send " + message);
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pull.do",method = RequestMethod.GET)
    @ResponseBody
    public void get(){
//        nativePull();
    }

    private void nativePull(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.98.193.213");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");


        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicQos(1);
            //消费端的只能有一个未被确认的消息，一个消费完确认后才能收到下一个，不会一次缓存多个
            channel.basicQos(1);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    System.out.println("DO TASK");
                } finally {
                    System.out.println(" [x] Done");
                    //手动确认-消费成功-消费被服务端丢弃
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                }
            };

            CancelCallback cancelCallback = consumerTag -> {
                System.out.println("cancle callback");
            };

            //关闭自动确认，进行手动确认
            boolean autoAckFlag = true;
            channel.basicConsume(QUEUE_NAME, autoAckFlag, deliverCallback, cancelCallback);

            Thread.sleep(2000);

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
