package com.zavadski.rest;

import com.zavadski.model.dto.UserWithRating;
import com.zavadski.service.api.UserRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRatingController {

    private final UserRatingService userService;

    private static final Logger logger = LogManager.getLogger(UserRatingController.class);

    @Autowired
    public UserRatingController(UserRatingService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users_rating")
    public final List<UserWithRating> findAllUsersWithRating() {

        logger.info("find All Users With Rating");

        return userService.findAllUsersWithRating();

    }
}
