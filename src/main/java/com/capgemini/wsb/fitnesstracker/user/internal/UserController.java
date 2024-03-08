package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }


    @PostMapping("/{firstname}/{lastname}/{birthdate}/{email}")
    public void addUserToDatabase(@PathVariable("firstname") String firstname,
                                  @PathVariable("lastname") String lastname,
                                  @PathVariable("birthdate") LocalDate birthdate,
                                  @PathVariable("email") String email) {

        User user = new User(firstname, lastname, birthdate, email);

        userService.createUser(user);
    }

}