package com.zavadski.service.api;

import com.zavadski.model.Announcement;
import com.zavadski.model.dto.CreateAnnouncementDto;

import java.util.List;
import java.util.UUID;

public interface AnnouncementService {

    List<Announcement> findAllAnnouncements();

    List<Announcement> findMyAnnouncements();

    Announcement findAnnouncementById(UUID id);

    Announcement createAnnouncement(CreateAnnouncementDto createAnnouncementDto);

    Announcement updateAnnouncement(CreateAnnouncementDto createAnnouncementDto) throws Exception;

    void deleteAnnouncement(UUID id) throws Exception;

    void buy(UUID uuid);

    List<Announcement> findAnnouncementsHistory();

}
