package com.zavadski.service.api;

import com.zavadski.model.Announcement;
import com.zavadski.model.dto.AnnouncementDto;
import com.zavadski.model.dto.CreateAnnouncementDto;

import java.util.List;
import java.util.UUID;

public interface AnnouncementService {

    Announcement findById(UUID id);

    Announcement save(CreateAnnouncementDto createAnnouncementDto);

    //    List<Category> findAll();

//    Category update(Category category);
//    void delete(UUID id);
//
//    EventDto getEventById(Long eventId, Long id);
//    EventDto createEvent(CreateEventDto createEventDto, Long id);
//    EventDto updateEvent(Long eventId, CreateEventDto createEventDto, Long id);
//    void deleteEvent(Long eventId, Long id);
//    EventDto addUser(Long eventId, Long userId, Long id);
//    EventDto deleteUser(Long eventId, Long userId, Long id);
//    Page<EventDto> findMyEvents(Long id, Pageable pageable);
}
