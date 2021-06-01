package com.meiken.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class RabbitMessageListener {

//    @RabbitListener(queues = "XMLY_MEIKEN_TEST")
    public void onMessage(Message message, Channel channel) {
        String msg = new String(message.getBody(), Charset.forName("UTF-8"));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(msg);
        } catch (Exception e) {// 除了业务异常之外，别的都进行重试
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
