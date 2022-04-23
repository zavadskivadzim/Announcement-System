package com.zavadski.service;

import com.zavadski.dao.api.AnnouncementDao;
import com.zavadski.model.Announcement;
import com.zavadski.model.User;
import com.zavadski.service.api.AnnouncementService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementDao announcementDao;
    private final UserService userService;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementDao announcementDao, UserService userService) {
        this.announcementDao = announcementDao;
        this.userService = userService;
    }

    @Override
    public Announcement findAnnouncementById(UUID id) {
        return announcementDao.findById(id);
    }

    @Override
    public Announcement createAnnouncement(Announcement announcement) {
        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        announcement.setUser(author);
        return announcementDao.save(announcement);
    }

}
