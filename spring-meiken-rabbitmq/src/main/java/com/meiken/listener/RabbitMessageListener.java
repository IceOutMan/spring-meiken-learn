package com.meiken.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class RabbitMessageListener {

    @RabbitListener(queues = "XMLY_MEIKEN_QUEUE")
    public void onMessage(Message message, Channel channel) {
        String msg = new String(message.getBody(), Charset.forName("UTF-8"));
        try {
            System.out.println(msg);
        } catch (Exception e) {
            // 除了业务异常之外，别的都进行重试
            try {
                // 消费拒绝，直接被服务端丢弃
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            try {
                // 手动确认后，服务端丢弃该消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
