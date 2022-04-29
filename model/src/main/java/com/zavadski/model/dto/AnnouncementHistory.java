package com.zavadski.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zavadski.model.Announcement;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AnnouncementHistory {

    private UUID id;
    private String body;
    private Integer price;
    private String category;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private LocalDateTime dateOfCreating;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private LocalDateTime dateOfClosing;
    private UserDto customer;

    public static AnnouncementHistory fromAnnouncement(Announcement announcement) {
        AnnouncementHistory announcementHistory = new AnnouncementHistory();
        announcementHistory.setId(announcement.getId());
        announcementHistory.setBody(announcement.getBody());
        announcementHistory.setPrice(announcement.getPrice());
        announcementHistory.setCategory(announcement.getCategory().getName());
        announcementHistory.setDateOfCreating(announcement.getDateOfCreating());
        announcementHistory.setDateOfClosing(announcement.getDateOfClosing());
        announcementHistory.setCustomer(UserDto.fromUser(announcement.getCustomer()));
        return announcementHistory;
    }

}
