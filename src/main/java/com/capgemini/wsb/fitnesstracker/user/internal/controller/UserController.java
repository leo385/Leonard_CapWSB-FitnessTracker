package com.capgemini.wsb.fitnesstracker.user.internal.controller;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import com.capgemini.wsb.fitnesstracker.user.api.exceptions.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.dto.BasicUserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.impl.UserServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.internal.dto.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * GET all users.
     * @return An {@link List} containing the located users.
     */

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * GET user by id.
     * @return An {@link List} containing the located user.
     */
    @GetMapping("/{id}")
    public List<UserDto> getUserById(@PathVariable("id") Long id) {
            return userService.getUser(id).stream().map(userMapper::toDto).toList();

    }

    /**
     * GET user by id with basic information.
     * @return An {@link List} containing the located users.
     */
    @GetMapping("/by-basic")
    public List<BasicUserDto> getBasicInformationUsersById(@RequestParam("id") Long id)
    {
        return userService.getUser(id).stream().map(userMapper::basicToDto).toList();
    }

    /**
     * GET user by email
     * @return An {@link List} containing the located users.
     */
    @GetMapping("/by-email")
    public List<UserDto> getUsersByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email).stream().map(userMapper::toDto).toList();
    }

    /**
     * GET user by its age
     * @return An {@link List} containing the located users.
     */
    @GetMapping("/by-age")
    public List<UserDto> getUsersByAge(@RequestParam("age") int age) {

        return userService.getUsersByAge(age).stream().map(userMapper::toDto).toList();
    }

    /**
     * CREATE user in database
     *
     */
    @PostMapping("/{firstname}/{lastname}/{birthdate}/{email}")
    public void addUserToDatabase(@PathVariable("firstname") String firstname,
                                  @PathVariable("lastname") String lastname,
                                  @PathVariable("birthdate") LocalDate birthdate,
                                  @PathVariable("email") String email) {

        UserEntity user = new UserEntity(firstname, lastname, birthdate, email);
        userService.createUser(user);

    }

    /**
     * DELETE user from database.
     *
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserFromDatabase(@PathVariable("id") Long id){
        boolean isDeleted = userService.deleteUser(id);

        if(isDeleted){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * UPDATE user in database.
     *
     */
    @PutMapping("/{id}/{firstname}/{lastname}/{birthdate}/{email}")
    public ResponseEntity<UserDto> updateUserInDatabase(@PathVariable Long id, @PathVariable String firstname,
                                                        @PathVariable String lastname, @PathVariable LocalDate birthdate,
                                                        @PathVariable String email) {

            UserEntity tmpUser = new UserEntity(firstname, lastname, birthdate, email);
            UserEntity updatedUser = userService.updateUser(id, tmpUser);

            UserDto userdto = userMapper.toDto(updatedUser);

            return ResponseEntity.ok(userdto);
    }

}