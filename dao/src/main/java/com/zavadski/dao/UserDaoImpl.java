package com.zavadski.dao;

import com.zavadski.dao.api.UserDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.User;
import com.zavadski.model.dto.UserWithAvgGradeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    private final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public List<User> findAll() {

        logger.info("Get all users");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public List<UserWithAvgGradeDto> findAllUsersWithAvgGrade() {

        logger.info("Get all users");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "select new UserWithAvgGradeDto(id, firstName, surname) from User u";

//        "select new Summary(a, b, c) FROM A a, B b, C c"

//        String hql = "select u.id as id, u.firstName as firstName, u.surname as surname, avg(g.grade) as grade from User u LEFT JOIN com.zavadski.model.Grade g ON u.id = g.receiver GROUP BY u.id";
        List<UserWithAvgGradeDto> users = session.createQuery(hql, UserWithAvgGradeDto.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public User save(User user) {

        logger.info("Create user {}", user);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

        return user;
    }

    @Override
    public User findById(UUID id) {

        logger.info("Find user by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();

        return user;
    }

    @Override
    public User update(User user) {

        logger.info("Update user {}", user);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();

        return user;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete user by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User findByLogin(String login) {

        logger.info("Find user by login={}", login);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "from User where login = :login";
        Query query = session.createQuery(hql);
        query.setParameter("login", login);
        User user = (User) query.getResultList().get(0);
        session.getTransaction().commit();
        session.close();

        return user;
    }

}
