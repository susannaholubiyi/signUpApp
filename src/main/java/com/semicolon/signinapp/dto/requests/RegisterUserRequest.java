package com.semicolon.signinapp.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest   {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}