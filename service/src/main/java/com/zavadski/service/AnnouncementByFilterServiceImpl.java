package com.zavadski.service;

import com.zavadski.dao.api.AnnouncementByFilterDao;
import com.zavadski.model.dto.AnnouncementByFilterDto;
import com.zavadski.service.api.AnnouncementByFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementByFilterServiceImpl implements AnnouncementByFilterService {

    private final AnnouncementByFilterDao announcementDao;

    @Autowired
    public AnnouncementByFilterServiceImpl(AnnouncementByFilterDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    public List<AnnouncementByFilterDto> filterAnnouncementsByCategory(String category) {
        return announcementDao.filterAnnouncementsByCategory(category);
    }

    @Override
    public List<AnnouncementByFilterDto> filterAnnouncementsByPrice (Integer maxPrice) {
        return announcementDao.filterAnnouncementsByPrice(maxPrice);
    }

}
