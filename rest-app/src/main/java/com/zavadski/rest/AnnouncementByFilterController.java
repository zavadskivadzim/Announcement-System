package com.zavadski.rest;

import com.zavadski.model.dto.AnnouncementByFilterDto;
import com.zavadski.service.api.AnnouncementByFilterService;
import com.zavadski.service.api.AnnouncementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncementByFilterController {

    private final AnnouncementByFilterService announcementService;

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @Autowired
    public AnnouncementByFilterController(AnnouncementByFilterService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping(value = "/announcements")
    public final List<AnnouncementByFilterDto> findAnnouncementByFilter(@RequestParam(value = "category", required = false)
                                                                     String category) {

        logger.info("find All Announcements");

        return announcementService.findAnnouncementsByFilter(category);
    }

}
