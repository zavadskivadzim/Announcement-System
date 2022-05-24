package com.zavadski.model.dto;

import com.zavadski.model.User;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    @Size(max = 50, message = "firstName can't be more then {max} characters long")
    private String firstName;
    @Size(max = 50, message = "surname can't be more then {max} characters long")
    private String surname;
    private LocalDate birthday;
    @Size(max = 50, message = "email can't be more then {max} characters long")
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
