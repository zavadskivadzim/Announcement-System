package com.zavadski.rest;

import com.zavadski.model.User;
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

    @GetMapping(value = "/user/users")
    public final List<UserDto> findAllUsers() {

        logger.info("find All Users");

        return userService.findAll()
                .stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @GetMapping(value = "/users/{id}")
    public final UserDto getUserById(@PathVariable UUID id) {

        logger.info("get Role By Id={}", id);

        User user = userService.findById(id);
        return UserDto.fromUser(user);
    }
//
//    @PostMapping(path = "/roles")
//    @ResponseStatus(HttpStatus.CREATED)
//    public final void createOrder(@RequestBody UserDto role) {
//
//        logger.info("create Role ({})", role);
//
//        userService.save(mappingDto.mapToUser(role));
//    }
//
//    @PutMapping(value = "/roles")
//    @ResponseStatus(HttpStatus.OK)
//    public final User updateUser(@RequestBody UserDto user) {
//
//        logger.info("update User {}", user);
//
//        return userService.save(mappingDto.mapToUser(user));
//    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable UUID id) {

        logger.info("delete User by id={}", id);

        userService.delete(id);
    }
}
