package com.zavadski.dao;

import com.zavadski.dao.api.GradeDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Grade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class GradeDaoImpl implements GradeDao {

    private final Logger logger = LogManager.getLogger(GradeDaoImpl.class);

    @Override
    public List<Grade> findAll() {

        logger.info("Get all Grades");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Grade> grades = session.createQuery("from Grade", Grade.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return grades;
    }

    @Override
    public Grade findById(UUID id) {

        logger.info("Find Grade by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Grade grade = session.get(Grade.class, id);
        session.getTransaction().commit();
        session.close();

        return grade;
    }

    @Override
    public Grade save(Grade grade) {

        logger.info("Create Grade {}", grade);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(grade);
        session.getTransaction().commit();
        session.close();

        return grade;
    }

    @Override
    public Grade update(Grade grade) {

        logger.info("Update Grade {}", grade);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(grade);
        session.getTransaction().commit();
        session.close();

        return grade;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete Grade by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Grade grade = session.get(Grade.class, id);
        session.delete(grade);
        session.getTransaction().commit();
        session.close();
    }

}
