package com.zavadski.rest;

import com.zavadski.model.dto.RoleDto;
import com.zavadski.service.api.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController {

    private final RoleService roleService;

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public final List<RoleDto> findAllRoles() {

        logger.info("find All Roles");

        return roleService.findAllRoles()
                .stream().map(RoleDto::fromRole).collect(Collectors.toList());
    }

}
