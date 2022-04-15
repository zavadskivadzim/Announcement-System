package com.zavadski.rest;

import com.zavadski.model.User;
import com.zavadski.model.dto.MappingDto;
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
    private final MappingDto mappingDto;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, MappingDto mappingDto) {
        this.userService = userService;
        this.mappingDto = mappingDto;
    }

    @GetMapping(value = "/admin/users")
    public final List<UserDto> findAllRoles() {

        logger.info("find All Users");

        return userService.findAll()
                .stream().map(mappingDto::mapToUserDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/roles/{id}")
    public final UserDto getRoleById(@PathVariable UUID id) {

        logger.info("get Role By Id={}", id);

        return mappingDto.mapToUserDto(userService.findById(id));
    }

    @PostMapping(path = "/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createOrder(@RequestBody UserDto role) {

        logger.info("create Role ({})", role);

        userService.save(mappingDto.mapToUser(role));
    }

    @PutMapping(value = "/roles")
    @ResponseStatus(HttpStatus.OK)
    public final User updateUser(@RequestBody UserDto user) {

        logger.info("update User {}", user);

        return userService.save(mappingDto.mapToUser(user));
    }

    @DeleteMapping(value = "/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable UUID id) {

        logger.info("delete Role by id={}", id);

        userService.delete(id);
    }
}
