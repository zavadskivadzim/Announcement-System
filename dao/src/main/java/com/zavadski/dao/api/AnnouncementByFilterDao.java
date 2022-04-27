package com.zavadski.dao.api;

import com.zavadski.model.dto.AnnouncementByFilterDto;

import java.util.List;

public interface AnnouncementByFilterDao {

    List<AnnouncementByFilterDto> filterAnnouncementsByCategory(String category);

    List<AnnouncementByFilterDto> filterAnnouncementsByPrice(Integer maxPrice);

}
