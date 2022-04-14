package com.zavadski.service.api;

import com.zavadski.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role save(Role role);
}
