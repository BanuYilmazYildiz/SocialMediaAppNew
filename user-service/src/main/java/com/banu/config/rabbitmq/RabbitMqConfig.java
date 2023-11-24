package com.banu.config.rabbitmq;



import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    @Value("${rabbitmq.queue-register}") //register-queue
    private String queueNameRegister;

//    @Value("${rabbitmq.exchange-user}")
//    private String exchange;


//    @Value("${rabbitmq.register-key}")
//    private String registerBindingKey;


//    @Value("${rabbitmq.queue-register}")
//    private String queueNameRegister;

    @Bean
    public Queue registerQueue(){
        return new Queue(queueNameRegister);
    }

}
