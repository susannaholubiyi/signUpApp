package com.semicolon.signinapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponse {
    private String email;
    private String password;
    private String message;
}