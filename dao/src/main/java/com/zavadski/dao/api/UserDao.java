package com.zavadski.dao.api;

import com.zavadski.model.User;
import com.zavadski.model.dto.UserWithRating;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> findAll();

    List<UserWithRating> findAllUsersWithRating();

    User save(User user);

    User findById(UUID id);

    User update(User user);

    void delete(UUID id);

    User findByLogin(String login);

}
