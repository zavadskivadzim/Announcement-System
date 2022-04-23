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

}
