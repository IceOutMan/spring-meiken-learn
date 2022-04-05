package com.meiken.config;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource( value = { "classpath:rabbitConfig.properties" })
@Configuration
@EnableRabbit
public class RabbitBeanConfiguration {

    @Value("${rabbit.host}")
    private String host;

    @Value("${rabbit.port}")
    private int port;
    @Value("${rabbit.username}")
    private String username;
    @Value("${rabbit.password}")
    private String password;

    @Value("${meiken.queue}")
    private String queue;
    @Value("${meiken.direct.exchange}")
    private String exchange;
    @Value("${meiken.bind.key}")
    private String bindKey;

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin ampqAdmin(){
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory());
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public Queue myQueue() {
        return new Queue(queue,true);
    }

    @Bean
    public DirectExchange myDirectExchange(){
        return new DirectExchange(exchange,true, false);
    }

    @Bean
    public Binding myBinding(){
        return new  Binding(queue,
                Binding.DestinationType.QUEUE,
                exchange,
                bindKey, null);
    }

}