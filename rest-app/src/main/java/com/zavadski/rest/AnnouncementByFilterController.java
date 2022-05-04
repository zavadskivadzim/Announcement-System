package com.zavadski.rest;

import com.zavadski.model.dto.AnnouncementByFilterDto;
import com.zavadski.service.api.AnnouncementByFilterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncementByFilterController {

    private final AnnouncementByFilterService announcementByFilterService;

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @Autowired
    public AnnouncementByFilterController(AnnouncementByFilterService announcementByFilterService) {
        this.announcementByFilterService = announcementByFilterService;
    }

    @GetMapping(value = "/announcements/filter")
    public final List<AnnouncementByFilterDto> findAnnouncementsByFilter(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "minPrice", required = false) Integer minPrice) {

        logger.info("filter announcements");
        return announcementByFilterService.filterAnnouncementsByCategory(category, maxPrice, minPrice);
    }
}
