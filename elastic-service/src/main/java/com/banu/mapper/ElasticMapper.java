package com.banu.mapper;

import com.banu.rabbitmq.model.RegisterElasticModel;
import com.banu.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElasticMapper {

    ElasticMapper INSTANCE = Mappers.getMapper(ElasticMapper.class);

    UserProfile fromElasticRegisterModelToUserProfile(RegisterElasticModel model);



}
