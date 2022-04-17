package com.zavadski.service.api;

import com.zavadski.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User findById(UUID id);

    User update(User user);

    void delete(UUID id);

    void register(User user);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

}
