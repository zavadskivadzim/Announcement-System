package com.zavadski.model.security;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;
    private String password;

}
