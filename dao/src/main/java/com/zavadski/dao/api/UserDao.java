package com.zavadski.dao.api;

import com.zavadski.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> findAll();

    User save(User user);

    User findById(UUID id);

    User findCurrentUser(UUID id);

    User update(User user);

    User findByLogin(String login);

    boolean checkLoginOnUnique(String login);

}
