package com.zavadski.dao;

import com.zavadski.dao.api.AnnouncementDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Announcement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {

    private final Logger logger = LogManager.getLogger(AnnouncementDaoImpl.class);

    @Override
    public List<Announcement> findAll() {

        logger.info("Get all Announcements");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Announcement> announcements = session.createQuery("from Announcement", Announcement.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return announcements;
    }

    @Override
    public Announcement findById(UUID id) {

        logger.info("Find announcement by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Announcement announcement = session.get(Announcement.class, id);
        session.getTransaction().commit();
        session.close();

        return announcement;
    }

    @Override
    public Announcement save(Announcement announcement) {

        logger.info("Create announcement {}", announcement);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(announcement);
        session.getTransaction().commit();
        session.close();

        return announcement;
    }

    @Override
    public Announcement update(Announcement announcement) {

        logger.info("Update announcement {}", announcement);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(announcement);
        session.getTransaction().commit();
        session.close();

        return announcement;
    }


}
