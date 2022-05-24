package com.zavadski.model.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Login can't be empty")
    @Size(max = 50, message = "Login can't be more then {max} characters long")
    private String login;

    @NotBlank(message = "Password can't be empty")
    private String password;

}
