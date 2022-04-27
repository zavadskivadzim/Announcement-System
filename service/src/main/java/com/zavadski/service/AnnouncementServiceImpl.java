package com.zavadski.service;

import com.zavadski.dao.api.AnnouncementDao;
import com.zavadski.model.Announcement;
import com.zavadski.model.User;
import com.zavadski.model.dto.CreateAnnouncementDto;
import com.zavadski.model.enumeration.Status;
import com.zavadski.service.api.AnnouncementService;
import com.zavadski.service.api.CategoryService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementDao announcementDao;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementDao announcementDao, UserService userService, CategoryService categoryService) {
        this.announcementDao = announcementDao;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Announcement> findAllAnnouncements() {
        return announcementDao.findAll();
    }

    @Override
    public List<Announcement> findMyAnnouncements() {

        User currentUser = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        return announcementDao.findAll().stream()
                .filter(announcement -> announcement.getUser()
                        .equals((currentUser)))
                .collect(Collectors.toList());
    }

    @Override
    public Announcement findAnnouncementById(UUID id) {
        return announcementDao.findById(id);
    }

    @Override
    public Announcement createAnnouncement(CreateAnnouncementDto createAnnouncementDto) {
        Announcement announcement = new Announcement();
        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        announcement.setUser(author);
        announcement.setBody(createAnnouncementDto.getBody());
        announcement.setPrice(createAnnouncementDto.getPrice());
        announcement.setDateOfCreating(LocalDateTime.now());
        announcement.setStatus(Status.ACTIVE);
        announcement.setCategory(categoryService.findCategoryById(createAnnouncementDto.getCategory()));
        return announcementDao.save(announcement);
    }

    @Override
    public Announcement updateAnnouncement(CreateAnnouncementDto createAnnouncementDto) throws Exception {

        Announcement announcement = findAnnouncementById(createAnnouncementDto.getId());

        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(announcement.getUser())) {
            announcement.setBody(createAnnouncementDto.getBody());
            announcement.setPrice(createAnnouncementDto.getPrice());
            announcement.setCategory(categoryService.findCategoryById(createAnnouncementDto.getCategory()));
            return announcementDao.update(announcement);
        } else {
            throw new Exception("you can't update this announcement");
        }
    }

    @Override
    public void deleteAnnouncement(UUID id) throws Exception {

        Announcement announcement = findAnnouncementById(id);
        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(announcement.getUser())) {
            announcementDao.delete(id);
        } else {
            throw new Exception("you can't delete this announcement");
        }
    }

}
