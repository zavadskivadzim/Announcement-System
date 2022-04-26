package com.zavadski.dao;

import com.zavadski.dao.api.UserRatingDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.dto.UserWithRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRatingDaoImpl implements UserRatingDao {

    private final Logger logger = LogManager.getLogger(UserRatingDaoImpl.class);

    @Value("${USERS_WITH_RATING}")
    private String userSql;

    @Override
    public List<UserWithRating> findAllUsersWithRating() {

        logger.info("Get all users");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<UserWithRating> users = session.createQuery(userSql, UserWithRating.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return users;
    }

}
