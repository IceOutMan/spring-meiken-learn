package com.meiken.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    private final static String QUEUE_NAME = "XMLY_MEIKEN_TEST";

    @RequestMapping(value = "/test.do",method = RequestMethod.GET)
    @ResponseBody
    public Object test(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", null);

        return jsonObject;
    }

    @RequestMapping(value = "/push.do",method = RequestMethod.GET)
    @ResponseBody
    public void mq(){

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
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
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");


        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicQos(1);
            //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            channel.basicQos(1);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    System.out.println("DO TASK");
                } finally {
                    System.out.println(" [x] Done");
                    //????????????-????????????-????????????????????????
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                }
            };

            CancelCallback cancelCallback = consumerTag -> {
                System.out.println("cancle callback");
            };

            //???????????????????????????????????????
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
