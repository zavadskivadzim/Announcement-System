package com.zavadski.model.dto;

import com.zavadski.model.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleDto {

    private UUID id = UUID.randomUUID();
    private String name;

    public Role toRole() {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        return role;
    }

    public static RoleDto fromRole(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

}
