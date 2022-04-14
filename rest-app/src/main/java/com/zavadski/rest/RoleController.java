package com.zavadski.rest;

import com.zavadski.model.Role;
import com.zavadski.service.api.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RoleController {

    private final RoleService roleService;

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public final Collection<Role> findAllRoles() {

        logger.debug("rest-app: findAllRoles");

        return roleService.findAll();
    }

    @PostMapping(value = "/roles")
    public final Role createRole(@RequestBody Role role) {

        logger.debug("rest-app: createRole");

        return roleService.save(role);
    }

}
