package com.zavadski.rest;

import com.zavadski.model.dto.UserDto;
import com.zavadski.service.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users")
    public final List<UserDto> findAllUsers() {

        logger.info("find All Users");

        return userService.findAllUsers()
                .stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @GetMapping(value = "/users/{id}")
    public final UserDto getUserById(@PathVariable UUID id) {

        logger.info("get user By Id={}", id);

        return UserDto.fromUser(userService.findUserById(id));
    }

    @PutMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public final void updateUser(@RequestBody UserDto userDto) {

        logger.info("update User {}", userDto);

        userService.updateUser(userDto.toUser());
    }

    @DeleteMapping(value = "/admin/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable UUID id) {

        logger.info("delete User by id={}", id);

        userService.deleteUser(id);
    }
}
