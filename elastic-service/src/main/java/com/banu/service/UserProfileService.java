package com.banu.service;


import com.banu.mapper.ElasticMapper;
import com.banu.rabbitmq.model.RegisterElasticModel;
import com.banu.repository.UserProfileRepository;
import com.banu.repository.entity.UserProfile;
import com.banu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }


    public UserProfile createUserWithRabbitMq(RegisterElasticModel model) {
        return save(ElasticMapper.INSTANCE.fromElasticRegisterModelToUserProfile(model));
    }

    //databasei olan user service user-service modulunde istek oraya atılacak


}
