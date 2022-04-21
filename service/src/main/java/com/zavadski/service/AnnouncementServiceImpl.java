package com.zavadski.service;

import com.zavadski.dao.api.AnnouncementDao;
import com.zavadski.dao.api.CategoryDao;
import com.zavadski.model.Announcement;
import com.zavadski.model.Category;
import com.zavadski.service.api.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementDao announcementDao;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    public Announcement findById(UUID id) {
        return announcementDao.findById(id);
    }

    @Override
    public Announcement save(Announcement announcement, UUID id) {
        return announcementDao.save(announcement);
    }

}
