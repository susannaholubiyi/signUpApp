package com.semicolon.signinapp.services;

import com.semicolon.signinapp.dto.requests.RegisterUserRequest;
import com.semicolon.signinapp.dto.response.RegisterUserResponse;

public interface UserService {

    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
}