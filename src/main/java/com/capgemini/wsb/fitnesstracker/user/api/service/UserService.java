package com.capgemini.wsb.fitnesstracker.user.api.service;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Interface (API) for modifying operations on {@link UserEntity} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
@Component
public interface UserService {

    UserEntity createUser(UserEntity user);
    boolean deleteUser(Long id);
    UserEntity updateUser(Long id, UserEntity updatedUser);
}
