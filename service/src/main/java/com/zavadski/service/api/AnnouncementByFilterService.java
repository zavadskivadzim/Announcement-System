package com.zavadski.service.api;

import com.zavadski.model.dto.AnnouncementByFilterDto;

import java.util.List;

public interface AnnouncementByFilterService {

    List<AnnouncementByFilterDto> filterAnnouncementsByCategory(String category);

    List<AnnouncementByFilterDto> filterAnnouncementsByPrice(Integer maxPrice);

}
