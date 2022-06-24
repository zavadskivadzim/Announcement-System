package com.zavadski.service;

import com.zavadski.model.dto.AnnouncementByFilterDto;

import java.util.List;

public interface AnnouncementByFilterService {

    List<AnnouncementByFilterDto> filterAnnouncements(String category, Integer maxPrice, Integer minPrice);

}
