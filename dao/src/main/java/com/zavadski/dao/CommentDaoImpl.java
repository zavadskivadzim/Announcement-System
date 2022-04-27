package com.zavadski.dao;

import com.zavadski.dao.api.CommentDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CommentDaoImpl implements CommentDao {

    private final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    @Override
    public List<Comment> findAll() {

        logger.info("Get all Comments");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Comment> comments = session.createQuery("from Comment", Comment.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return comments;
    }

    @Override
    public Comment findById(UUID id) {

        logger.info("Find Comment by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Comment comment = session.get(Comment.class, id);
        session.getTransaction().commit();
        session.close();

        return comment;
    }

    @Override
    public Comment save(Comment comment) {

        logger.info("Create Comment {}", comment);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
        session.close();

        return comment;
    }

    @Override
    public Comment update(Comment comment) {

        logger.info("Update Comment {}", comment);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(comment);
        session.getTransaction().commit();
        session.close();

        return comment;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete Comment by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Comment comment = session.get(Comment.class, id);
        session.delete(comment);
        session.getTransaction().commit();
        session.close();
    }

}
