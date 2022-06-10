package com.zavadski.rest;

import com.zavadski.model.User;
import com.zavadski.model.dto.UserDto;
import com.zavadski.service.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/users/current")
    public final UserDto getCurrentUser() {

        logger.info("get current user");
        User user = userService.findCurrentUser();
        return UserDto.fromUser(user);

    }

    @PutMapping(value = "/users")
    public final String updateCurrentUser(@RequestBody UserDto userDto) {

        logger.info("update Current User {}", userDto);

        userService.updateUser(userDto.toUser());
        return "Your profile successfully updated";
    }

}
