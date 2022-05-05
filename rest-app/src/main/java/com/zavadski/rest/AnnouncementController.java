package com.zavadski.rest;

import com.zavadski.model.Announcement;
import com.zavadski.model.User;
import com.zavadski.model.dto.AnnouncementDto;
import com.zavadski.model.dto.AnnouncementHistory;
import com.zavadski.model.dto.CreateAnnouncementDto;
import com.zavadski.service.CurrentUserService;
import com.zavadski.service.api.AnnouncementService;
import com.zavadski.service.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final UserService userService;

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, UserService userService) {
        this.announcementService = announcementService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin/announcements")
    public final List<AnnouncementDto> findAllAnnouncements() {

        logger.info("find All Announcements");

        return announcementService.findAllAnnouncements()
                .stream().map(AnnouncementDto::fromAnnouncement).collect(Collectors.toList());
    }

    @GetMapping(value = "/announcements/{id}")
    public final AnnouncementDto getAnnouncementById(@PathVariable UUID id) {

        logger.info("get announcement by Id={}", id);

        return AnnouncementDto.fromAnnouncement(announcementService.findAnnouncementById(id));
    }

    @PostMapping(path = "/announcements")
    @ResponseStatus(HttpStatus.CREATED)
    public final Announcement createAnnouncement(@RequestBody CreateAnnouncementDto createAnnouncementDto) {

        logger.info("create Announcement ({})", createAnnouncementDto);

        return announcementService.createAnnouncement(createAnnouncementDto);
    }

    @PutMapping(path = "/announcements")
    @ResponseStatus(HttpStatus.OK)
    public final Announcement updateAnnouncement(@RequestBody CreateAnnouncementDto createAnnouncementDto) throws Exception {

        logger.info("update Announcement ({})", createAnnouncementDto);

        return announcementService.updateAnnouncement(createAnnouncementDto);
    }

    @DeleteMapping(value = "/announcements/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnnouncement(@PathVariable UUID id) throws Exception {

        logger.info("delete Announcement by id={}", id);

        announcementService.deleteAnnouncement(id);
    }

    @GetMapping(value = "/announcements/mine")
    public final List<AnnouncementDto> findMyAnnouncements() {

        logger.info("find My Announcements");

        return announcementService.findAllAnnouncements()
                .stream()
                .filter(announcement -> announcement.getUser()
                        .equals(userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))))
                .map(AnnouncementDto::fromAnnouncement).collect(Collectors.toList());
    }

    @PutMapping(value = "/buy")
    @ResponseStatus(HttpStatus.OK)
    public final void buy(@RequestBody UUID uuid) {

        logger.info("selling {}", uuid);

        announcementService.buy(uuid);
    }

    @GetMapping(value = "/announcements_history")
    public final List<AnnouncementHistory> findAnnouncementsHistory() {

        logger.info("find Announcements History");

        User currentUser = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));

        return announcementService.findAllAnnouncements()
                .stream()
                .filter(announcement -> announcement.getUser().equals((currentUser))
                        && announcement.getStatus().name().equals("SOLD"))
                .sorted(Comparator.comparing(Announcement::getDateOfClosing).reversed())
                .map(AnnouncementHistory::fromAnnouncement).collect(Collectors.toList());
    }

}
