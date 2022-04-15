package com.zavadski.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id = UUID.randomUUID();
    private String login;
    private String password;

}
