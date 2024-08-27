package com.semicolon.signinapp.services;

import com.semicolon.signinapp.data.models.User;
import com.semicolon.signinapp.data.repositories.UserRepository;
import com.semicolon.signinapp.dto.requests.RegisterUserRequest;
import com.semicolon.signinapp.dto.response.RegisterUserResponse;
import com.semicolon.signinapp.exceptions.InvalidEmailException;
import com.semicolon.signinapp.exceptions.InvalidPasswordException;
import com.semicolon.signinapp.exceptions.UserAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest){
        checkIfRegisteredUser(registerUserRequest.getEmail());
        validateRegistrationDetails(registerUserRequest);
        User user = modelMapper.map(registerUserRequest, User.class);
        var savedUser = userRepository.save(user);
        var response = modelMapper.map(savedUser, RegisterUserResponse.class);
        response.setMessage("Registration successfully");
        return response;
    }

    private void validateRegistrationDetails(RegisterUserRequest request) {
        if (!request.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
            throw new InvalidEmailException("Invalid Input");
        if (request.getPassword().isEmpty())
            throw new InvalidPasswordException("Invalid Password");
    }

    private void checkIfRegisteredUser(String email){
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                throw new UserAlreadyExistsException("email already exist");
            }
        }
    }
}
