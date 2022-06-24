package com.zavadski.service;

import com.zavadski.model.dto.UserWithRating;

import java.util.List;

public interface UserRatingService {

    List<UserWithRating> findAllUsersWithRating();

}
