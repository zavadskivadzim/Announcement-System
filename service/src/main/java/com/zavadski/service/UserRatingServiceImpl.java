package com.zavadski.service;

import com.zavadski.dao.api.UserRatingDao;
import com.zavadski.model.dto.UserWithRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRatingServiceImpl implements UserRatingService {

    @Autowired
    private UserRatingDao userDao;

    @Override
    public List<UserWithRating> findAllUsersWithRating(){
        return userDao.findAllUsersWithRating();
    }

}
