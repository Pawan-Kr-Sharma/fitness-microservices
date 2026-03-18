package com.pk.fitness.userservice.services;

import com.pk.fitness.userservice.dtos.RegisterRequest;
import com.pk.fitness.userservice.dtos.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);
    UserResponse getUserProfile(String userId);
    Boolean validateUserById(String userId);
}
