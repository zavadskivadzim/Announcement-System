package com.zavadski.dao;

import com.zavadski.dao.api.AnnouncementByFilterDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.dto.AnnouncementByFilterDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementByFilterDaoImpl implements AnnouncementByFilterDao {

    private final Logger logger = LogManager.getLogger(AnnouncementByFilterDaoImpl.class);

    @Value("${FILTER_ANNOUNCEMENT_BY_CATEGORY}")
    private String announcementsByCategorySql;

    @Value("${FILTER_ANNOUNCEMENT_BY_PRICE}")
    private String announcementsByPriceSql;

    @Override
    public List<AnnouncementByFilterDto> filterAnnouncementsByCategory(String category) {

        logger.info("filter Announcements By Category {}", category);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery(announcementsByCategorySql, AnnouncementByFilterDto.class);
        query.setParameter("category", category);
        List<AnnouncementByFilterDto> announcements = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return announcements;
    }

    @Override
    public List<AnnouncementByFilterDto> filterAnnouncementsByPrice(Integer maxPrice) {

        logger.info("filter Announcements By Price {}", maxPrice);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery(announcementsByPriceSql, AnnouncementByFilterDto.class);
        query.setParameter("maxPrice", maxPrice);
        List<AnnouncementByFilterDto> announcements = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return announcements;
    }


}
