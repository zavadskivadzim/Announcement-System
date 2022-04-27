package com.zavadski.model.dto;

import com.zavadski.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String firstName;
    private String surname;
    private LocalDate birthday;
    private String email;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setSurname(surname);
        user.setBirthday(birthday);
        user.setEmail(email);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setSurname(user.getSurname());
        userDto.setBirthday(user.getBirthday());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
