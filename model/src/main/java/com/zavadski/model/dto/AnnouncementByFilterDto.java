package com.zavadski.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AnnouncementByFilterDto {

    private UUID id;
    private String body;
    private Integer price;

}
