package com.zavadski.model.security;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String login;
    private String password;

}
