package com.zavadski.service;

import com.zavadski.dao.api.RoleDao;
import com.zavadski.dao.api.UserDao;
import com.zavadski.model.Role;
import com.zavadski.model.User;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(UUID id) {
        return userDao.findById(id);
    }

    @Override
    public void updateUser(User user) {
        User currentUser = findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        user.setId(currentUser.getId());
        user.setLogin(currentUser.getLogin());
        user.setPassword(currentUser.getPassword());
        user.setRole(currentUser.getRole());
        userDao.update(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userDao.delete(id);
    }

    @Override
    public void register(User user) {
        Role userRole = findAllUsers().isEmpty()
                ?(roleDao.findByName("ROLE_ADMIN"))
                :(roleDao.findByName("ROLE_USER"));
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = findUserByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
