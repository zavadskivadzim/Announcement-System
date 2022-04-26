package com.zavadski.service.api;

import com.zavadski.model.dto.UserWithRating;

import java.util.List;

public interface UserRatingService {

    List<UserWithRating> findAllUsersWithRating();

}
