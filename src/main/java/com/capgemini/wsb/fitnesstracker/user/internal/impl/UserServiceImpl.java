package com.capgemini.wsb.fitnesstracker.user.internal.impl;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import com.capgemini.wsb.fitnesstracker.user.api.provider.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.service.UserService;
import com.capgemini.wsb.fitnesstracker.user.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(final UserEntity user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> getUserById(final Long userId) {

        return userRepository.findById(userId);
    }

    @Override
    public Optional<UserEntity> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> getUsersByAge(final int age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)){
                userRepository.deleteById(id);
                return true;
        }
        return false;
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setBirthdate(updatedUser.getBirthdate());
                    user.setEmail(updatedUser.getEmail());

                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    updatedUser.setId(id);
                    return userRepository.save(updatedUser);
                });
    }

    public Collection<UserEntity> findUsersByTheirGreaterAge(LocalDate time) {
        return userRepository.findUsersByTheirGreaterAge(time);
    }
}