package com.zavadski.dao.api;

import com.zavadski.model.Announcement;

import java.util.UUID;

public interface AnnouncementDao {

    Announcement findById(UUID id);

    Announcement save(Announcement announcement);

}
