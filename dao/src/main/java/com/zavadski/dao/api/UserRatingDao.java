package com.zavadski.dao.api;

import com.zavadski.model.dto.UserWithRating;

import java.util.List;

public interface UserRatingDao {

    List<UserWithRating> findAllUsersWithRating();

}
