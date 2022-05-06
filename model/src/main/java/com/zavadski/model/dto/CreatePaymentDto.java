package com.zavadski.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePaymentDto {

    private UUID announcementId;
    private Integer duration;

}
