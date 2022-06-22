package com.zavadski.rest.security;

import com.zavadski.dao.api.UserDao;
import com.zavadski.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);
        return UserDetailsImpl.fromUserToUserDetails(user);
    }
}
