package com.zavadski.rest;

import com.zavadski.model.Announcement;
import com.zavadski.model.Category;
import com.zavadski.model.dto.AnnouncementDto;
import com.zavadski.model.dto.CategoryDto;
import com.zavadski.model.dto.CreateAnnouncementDto;
import com.zavadski.service.api.AnnouncementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

//    @GetMapping(value = "/announcements")
//    public final List<CategoryDto> findAllCategory() {
//
//        logger.info("find All Categories");
//
//        return categoryService.findAll()
//                .stream().map(CategoryDto::fromCategory).collect(Collectors.toList());
//    }

    @GetMapping(value = "/announcements/{id}")
    public final AnnouncementDto getAnnouncementById(@PathVariable UUID id) {

        logger.info("get announcement by Id={}", id);

        Announcement announcement = announcementService.findById(id);
        return AnnouncementDto.fromAnnouncement(announcement);
    }

    @PostMapping(path = "/announcements")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createAnnouncement(@RequestBody CreateAnnouncementDto createAnnouncementDto,
                                         @RequestHeader("${request.id}") UUID id) {

        logger.info("create Announcement ({})", createAnnouncementDto);

        announcementService.save(createAnnouncementDto.toAnnouncementDto().toAnnouncement());
    }

//    @PostMapping
//    public EventDto createEvent(@RequestBody CreateEventDto createEventDto,
//                                @RequestHeader("${request.id}") Long id) {
//        return eventService.createEvent(createEventDto, id);
//    }
//
//    @PutMapping(value = "/categories")
//    @ResponseStatus(HttpStatus.OK)
//    public final Category updateCategory(@RequestBody CategoryDto category) {
//
//        logger.info("update Category {}", category);
//
//        return categoryService.update(category.toCategory());
//    }
//
//    @Secured("ROLE_ADMIN")
//    @DeleteMapping(value = "/categories/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteCategory(@PathVariable UUID id) {
//
//        logger.info("delete Category by id={}", id);
//
//        categoryService.delete(id);
//    }
}
