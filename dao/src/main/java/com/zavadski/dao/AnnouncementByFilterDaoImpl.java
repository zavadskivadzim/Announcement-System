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

    @Value("${FILTER_ANNOUNCEMENT_CATEGORY_MIN_MAX_PRICE}")
    private String announcementsFilterByCategoryMaxMinPrice;

    @Value("${FILTER_ANNOUNCEMENT_MIN_MAX_PRICE}")
    private String announcementsFilterByMaxMinPrice;

    @Value("${FILTER_ANNOUNCEMENT_MAX_PRICE}")
    private String announcementsFilterByMaxPrice;

    @Value("${FILTER_ANNOUNCEMENT_MIN_PRICE}")
    private String announcementsFilterByMinPrice;

    @Value("${FILTER_ANNOUNCEMENT_CATEGORY}")
    private String announcementsFilterByCategory;

    @Value("${FILTER_ANNOUNCEMENT_CATEGORY_MIN_PRICE}")
    private String announcementsFilterByCategoryMinPrice;

    @Value("${FILTER_ANNOUNCEMENT_CATEGORY_MAX_PRICE}")
    private String announcementsFilterByCategoryMaxPrice;

    @Value("${ALL_ANNOUNCEMENT}")
    private String announcementsWithoutFilter;

    @Override
    public List<AnnouncementByFilterDto> filterAnnouncements(String category, Integer maxPrice, Integer minPrice) {

        logger.info("filter Announcements");
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<AnnouncementByFilterDto> announcements = null;
        if (category != null & maxPrice != null & minPrice != null) {
            logger.info("filter by category maxPrice minPrice");
            Query query = session.createQuery(announcementsFilterByCategoryMaxMinPrice, AnnouncementByFilterDto.class);
            query.setParameter("category", category);
            query.setParameter("maxPrice", maxPrice);
            query.setParameter("minPrice", minPrice);
            announcements = query.getResultList();
        } else if (category == null & maxPrice != null & minPrice != null) {
            logger.info("filter by maxPrice minPrice");
            Query query = session.createQuery(announcementsFilterByMaxMinPrice, AnnouncementByFilterDto.class);
            query.setParameter("maxPrice", maxPrice);
            query.setParameter("minPrice", minPrice);
            announcements = query.getResultList();
        } else if (category == null & maxPrice != null & minPrice == null) {
            logger.info("filter by maxPrice");
            Query query = session.createQuery(announcementsFilterByMaxPrice, AnnouncementByFilterDto.class);
            query.setParameter("maxPrice", maxPrice);
            announcements = query.getResultList();
        } else if (category == null & maxPrice == null & minPrice != null) {
            logger.info("filter by minPrice");
            Query query = session.createQuery(announcementsFilterByMinPrice, AnnouncementByFilterDto.class);
            query.setParameter("minPrice", minPrice);
            announcements = query.getResultList();
        } else if (category != null & maxPrice == null & minPrice == null) {
            logger.info("filter by category");
            Query query = session.createQuery(announcementsFilterByCategory, AnnouncementByFilterDto.class);
            query.setParameter("category", category);
            announcements = query.getResultList();
        } else if (category != null & maxPrice == null & minPrice != null) {
            logger.info("filter by category minPrice");
            Query query = session.createQuery(announcementsFilterByCategoryMinPrice, AnnouncementByFilterDto.class);
            query.setParameter("category", category);
            query.setParameter("minPrice", minPrice);
            announcements = query.getResultList();
        } else if (category != null & maxPrice != null & minPrice == null) {
            logger.info("filter by category maxPrice");
            Query query = session.createQuery(announcementsFilterByCategoryMaxPrice, AnnouncementByFilterDto.class);
            query.setParameter("category", category);
            query.setParameter("maxPrice", maxPrice);
            announcements = query.getResultList();
        } else {
            logger.info("filter all announcement");
            Query query = session.createQuery(announcementsWithoutFilter, AnnouncementByFilterDto.class);
            announcements = query.getResultList();
        }
        session.getTransaction().commit();
        session.close();
        return announcements;
    }

}
