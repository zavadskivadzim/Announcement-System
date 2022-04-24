package com.zavadski.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateAnnouncementDto {

    private UUID id;
    private String body;
    private Integer price;
    private UUID category;

}
