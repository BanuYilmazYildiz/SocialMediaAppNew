package com.banu.mapper;

import com.banu.dto.request.UserCreateRequestDto;
import com.banu.rabbitmq.model.RegisterElasticModel;
import com.banu.rabbitmq.model.RegisterModel;
import com.banu.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserProfile fromCreateRequestToUser(UserCreateRequestDto dto);

    UserProfile fromRegisterModelToUserProfile(RegisterModel model);
    UserCreateRequestDto fromRegisterModelToUserCreateDto(RegisterModel model);

    RegisterElasticModel fromUserToRegisterElasticModel(UserProfile userProfile);
}
