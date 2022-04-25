package com.zavadski.dao;

import com.zavadski.dao.api.AnnouncementByFilterDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.dto.AnnouncementByFilterDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementByFilterDaoImpl implements AnnouncementByFilterDao {

    private final Logger logger = LogManager.getLogger(AnnouncementByFilterDaoImpl.class);

    @Override
    public List<AnnouncementByFilterDto> findAnnouncementByFilter(String category) {

        logger.info("Get all Announcements");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select new com.zavadski.model.dto.AnnouncementDto2 (a.id, a.body, a.price)" +
                " from Announcement a INNER JOIN Grade g ON a.user = g.receiver Order BY g.grade";

        List<AnnouncementByFilterDto> announcementByFilterDtos = session.createQuery(hql, AnnouncementByFilterDto.class).getResultList();

        session.getTransaction().commit();
        session.close();

        return announcementByFilterDtos;
    }

}
