package com.zavadski.service.api;

import com.zavadski.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(UUID id);

    void updateUser(User user);

    void deleteUser(UUID id);

    void register(User user);

    User findUserByLogin(String login);

    User findUserByLoginAndPassword(String login, String password);

}
