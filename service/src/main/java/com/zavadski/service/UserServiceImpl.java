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
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userDao.findById(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(UUID id) {
        userDao.delete(id);
    }

    @Override
    public User register(User user) {

        Role userRole = roleDao.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);

    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
