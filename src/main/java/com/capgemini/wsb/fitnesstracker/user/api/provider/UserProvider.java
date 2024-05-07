package com.capgemini.wsb.fitnesstracker.user.api.provider;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<UserEntity> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */

    Optional<UserEntity> getUserByEmail(String email);


    /**
     * Retrieves a users based on their predictable age.
     *
     * @param age age of users to search
     * @return An {@link List} containing the located users.
     */

    List<UserEntity> getUsersByAge(int age);


    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<UserEntity> findAllUsers();

}
