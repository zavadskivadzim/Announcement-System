package com.zavadski.service.api;

import com.zavadski.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    List<Role> findAll();

    Role save(Role role);

    Role findById(UUID id);

    Role update(Role role);

    void delete(UUID id);
}
