package com.zavadski.model.dto;

import com.zavadski.model.Announcement;
import com.zavadski.model.Category;
import com.zavadski.model.enumeration.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateAnnouncementDto {

    private String body;
    private Integer price;


    public AnnouncementDto toAnnouncementDto() {
        AnnouncementDto announcement = new AnnouncementDto();
        announcement.setBody(body);
        announcement.setPrice(price);
        return announcement;
    }

    public static CreateAnnouncementDto fromAnnouncementDto(AnnouncementDto announcementDto) {
        CreateAnnouncementDto createAnnouncementDto = new CreateAnnouncementDto();
        createAnnouncementDto.setBody(announcementDto.getBody());
        createAnnouncementDto.setPrice(announcementDto.getPrice());
        return createAnnouncementDto;
    }

}
