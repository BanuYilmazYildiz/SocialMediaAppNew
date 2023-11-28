package com.banu.rabbitmq.consumer;

import com.banu.rabbitmq.model.RegisterElasticModel;
import com.banu.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "${rabbitmq.queue-register-elastic}") //register-queue
    public void createNewUser(RegisterElasticModel model){
        log.info("User {}",model.toString());
        userProfileService.createUserWithRabbitMq(model);
//        userProfileService.createUserWithRabbitMq(UserMapper.INSTANCE.fromRegisterModelToUserCreateDto(model));
    }






}
