package com.zavadski.model.dto;

import com.zavadski.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserWithAvgGradeDto {

    private UUID id = UUID.randomUUID();
    private String firstName;
    private String surname;
    private LocalDate birthday;
    private String email;
    private Double grade;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setSurname(surname);
        user.setBirthday(birthday);
        user.setEmail(email);
        return user;
    }

    public static UserWithAvgGradeDto fromUser(User user) {
        UserWithAvgGradeDto userDto = new UserWithAvgGradeDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setSurname(user.getSurname());
        userDto.setBirthday(user.getBirthday());
        userDto.setEmail(user.getEmail());
        userDto.setGrade(7.2);
        return userDto;
    }

}
