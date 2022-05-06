package com.zavadski.dao;

import com.zavadski.dao.api.MessageDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MessageDaoImpl implements MessageDao {

    private final Logger logger = LogManager.getLogger(MessageDaoImpl.class);

    @Override
    public List<Message> findAll() {

        logger.info("Get all Messages");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Message> messages = session.createQuery("from Message", Message.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return messages;
    }

    @Override
    public Message findById(UUID id) {

        logger.info("Find Message by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Message message = session.get(Message.class, id);
        session.getTransaction().commit();
        session.close();

        return message;
    }

    @Override
    public Message save(Message message) {

        logger.info("Create Message {}", message);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        session.close();

        return message;
    }

    @Override
    public Message update(Message message) {

        logger.info("Update Message {}", message);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(message);
        session.getTransaction().commit();
        session.close();

        return message;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete Message by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Message message = session.get(Message.class, id);
        session.delete(message);
        session.getTransaction().commit();
        session.close();
    }

}
