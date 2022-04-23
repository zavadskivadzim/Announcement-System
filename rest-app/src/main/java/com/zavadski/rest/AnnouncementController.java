package com.zavadski.rest;

import com.zavadski.model.Announcement;
import com.zavadski.model.dto.AnnouncementDto;
import com.zavadski.model.dto.CreateAnnouncementDto;
import com.zavadski.service.api.AnnouncementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping(value = "/announcements/{id}")
    public final AnnouncementDto getAnnouncementById(@PathVariable UUID id) {

        logger.info("get announcement by Id={}", id);

        return AnnouncementDto.fromAnnouncement(announcementService.findById(id));
    }

    @PostMapping(path = "/announcements")
    @ResponseStatus(HttpStatus.CREATED)
    public final Announcement createAnnouncement(@RequestBody CreateAnnouncementDto createAnnouncementDto) {

        logger.info("create Announcement ({})", createAnnouncementDto);

        return announcementService.save(createAnnouncementDto.toAnnouncement());
    }

}
