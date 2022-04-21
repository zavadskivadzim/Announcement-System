package com.zavadski.dao;

import com.zavadski.dao.api.AnnouncementDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Announcement;
import com.zavadski.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {

    private final Logger logger = LogManager.getLogger(AnnouncementDaoImpl.class);

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

}
