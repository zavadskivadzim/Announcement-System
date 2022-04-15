package com.zavadski.rest;

import com.zavadski.model.Role;
import com.zavadski.model.dto.MappingDto;
import com.zavadski.model.dto.RoleDto;
import com.zavadski.service.api.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class RoleController {

    private final RoleService roleService;
    private final MappingDto mappingDto;

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    public RoleController(RoleService roleService, MappingDto mappingDto) {
        this.roleService = roleService;
        this.mappingDto = mappingDto;
    }

    @GetMapping(value = "/roles")
    public final List<RoleDto> findAllRoles() {

        logger.info("findAllRoles");

        return roleService.findAll()
                .stream().map(mappingDto::mapToRoleDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/roles/{id}")
    public final RoleDto getRoleById(@PathVariable UUID id) {

        logger.info("get Role By Id={}", id);

        return mappingDto.mapToRoleDto(roleService.findById(id));
    }

    @PostMapping(path = "/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createOrder(@RequestBody RoleDto role) {

        logger.info("create Role ({})", role);

        roleService.save(mappingDto.mapToRole(role));
    }

    @PutMapping(value = "/roles")
    @ResponseStatus(HttpStatus.OK)
    public final Role updateRole(@RequestBody RoleDto role) {

        logger.info("update Role {}", role);

        return roleService.save(mappingDto.mapToRole(role));
    }

    @DeleteMapping(value = "/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable UUID id) {

        logger.info("delete Role by id={}", id);

        roleService.delete(id);
    }
}
