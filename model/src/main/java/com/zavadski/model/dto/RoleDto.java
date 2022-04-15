package com.zavadski.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleDto {

    private UUID id = UUID.randomUUID();
    private String name;

}
