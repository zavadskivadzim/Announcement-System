package com.zavadski.service.api;

import com.zavadski.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(UUID id);

    User findCurrentUser();

    void updateUser(User user);

    void register(User user);

    User findUserByLogin(String login);

    User findUserByLoginAndPassword(String login, String password);

    boolean checkLoginOnUnique(String login);

}
