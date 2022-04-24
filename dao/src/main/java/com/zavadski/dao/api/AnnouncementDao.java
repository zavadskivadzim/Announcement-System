package com.zavadski.dao.api;

import com.zavadski.model.Announcement;

import java.util.List;
import java.util.UUID;

public interface AnnouncementDao {

    List<Announcement> findAll();

    Announcement findById(UUID id);

    Announcement save(Announcement announcement);

    Announcement update(Announcement announcement);

    void delete(UUID id);

}
