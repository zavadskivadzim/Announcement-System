package com.zavadski.dao.api;

import com.zavadski.model.dto.AnnouncementByFilterDto;

import java.util.List;

public interface AnnouncementByFilterDao {

    List<AnnouncementByFilterDto> filterAnnouncements(String category, Integer maxPrice, Integer minPrice);

}
