package com.zavadski.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zavadski.model.Announcement;
import com.zavadski.model.Category;
import com.zavadski.model.enumeration.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AnnouncementDto {

    private UUID id;
    private String body;
    private Integer price;
    private Category category;
    private UserDto userDto;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private LocalDateTime dateOfCreating;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private LocalDateTime dateOfClosing;
    private Status status;

    public Announcement toAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement.setBody(body);
        announcement.setPrice(price);
        announcement.setCategory(category);
        announcement.setUser(userDto.toUser());
        announcement.setDateOfCreating(dateOfCreating);
        announcement.setDateOfClosing(dateOfClosing);
        announcement.setStatus(status);
        return announcement;
    }

    public static AnnouncementDto fromAnnouncement(Announcement announcement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setId(announcement.getId());
        announcementDto.setBody(announcement.getBody());
        announcementDto.setPrice(announcement.getPrice());
        announcementDto.setCategory(announcement.getCategory());
        announcementDto.setUserDto(UserDto.fromUser(announcement.getUser()));
        announcementDto.setDateOfCreating(announcement.getDateOfCreating());
        announcementDto.setDateOfClosing(announcement.getDateOfClosing());
        announcementDto.setStatus(announcement.getStatus());
        return announcementDto;
    }

}
