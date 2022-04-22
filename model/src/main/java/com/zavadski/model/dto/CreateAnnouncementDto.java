package com.zavadski.model.dto;

import com.zavadski.model.Announcement;
import lombok.Data;

@Data
public class CreateAnnouncementDto {

    private String body;
    private Integer price;


    public Announcement toAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setBody(body);
        announcement.setPrice(price);
        return announcement;
    }

    public static CreateAnnouncementDto fromAnnouncement(Announcement announcement) {
        CreateAnnouncementDto createAnnouncementDto = new CreateAnnouncementDto();
        createAnnouncementDto.setBody(announcement.getBody());
        createAnnouncementDto.setPrice(announcement.getPrice());
        return createAnnouncementDto;
    }

}
