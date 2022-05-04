package com.zavadski.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommentDto {

    private UUID id;
    private String body;
    private UUID announcement;

}
