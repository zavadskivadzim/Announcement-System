package com.zavadski.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserWithRating {

    private UUID id;
    private String firstName;
    private String surname;
    private Double grade;

}
