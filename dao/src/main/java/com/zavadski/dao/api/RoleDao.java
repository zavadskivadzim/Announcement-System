package com.zavadski.dao.api;

import com.zavadski.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Role findByName(String name);

}
