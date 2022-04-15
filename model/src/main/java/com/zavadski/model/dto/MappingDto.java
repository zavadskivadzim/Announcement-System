package com.zavadski.model.dto;

import com.zavadski.model.User;
import org.springframework.stereotype.Component;

@Component
public class MappingDto {

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        return user;
    }

}
