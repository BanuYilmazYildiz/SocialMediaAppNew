package com.banu.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterElasticModel implements Serializable {

    private String id;
    private Long authId;
    private String username;
    private String email;

}
