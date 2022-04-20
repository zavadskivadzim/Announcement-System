package com.zavadski.model.dto;

import com.zavadski.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id = UUID.randomUUID();
    private String firstName;
    private String surname;
    private LocalDate birthday;
    private String email;
    private String password;


    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setSurname(surname);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setSurname(user.getSurname());
        userDto.setBirthday(user.getBirthday());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
