package com.pk.fitness.userservice.services.impls;

import com.pk.fitness.userservice.dtos.RegisterRequest;
import com.pk.fitness.userservice.dtos.UserResponse;
import com.pk.fitness.userservice.entities.User;
import com.pk.fitness.userservice.entities.UserRole;
import com.pk.fitness.userservice.repositories.UserRepository;
import com.pk.fitness.userservice.services.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) throw new EntityExistsException("User Already exists !!");

        User user = User.builder().email(request.getEmail()).password(request.getPassword()).firstName(request.getFirstName()).lastName(request.getLastName()).role(UserRole.USER).build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder().id(savedUser.getId()).email(savedUser.getEmail()).password(savedUser.getPassword()).firstName(savedUser.getFirstName()).lastName(savedUser.getLastName()).createdAt(savedUser.getCreatedAt()).updateAt(savedUser.getUpdateAt()).build();
    }

    @Override
    public UserResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with given id"));
        return UserResponse.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword()).firstName(user.getFirstName()).lastName(user.getLastName()).createdAt(user.getCreatedAt()).updateAt(user.getUpdateAt()).build();
    }

    @Override
    public Boolean validateUserById(String userId) {
        log.info("Calling User service to validate User by Id: {}", userId);
        return userRepository.existsById(userId);
    }
}
