package com.zavadski.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateGradeDto {

    private UUID Id;
    private UUID receiverId;
    private Byte grade;

}
