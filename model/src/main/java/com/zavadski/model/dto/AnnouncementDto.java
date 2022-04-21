package com.zavadski.model.dto;

import com.zavadski.model.Announcement;
import com.zavadski.model.Category;
import com.zavadski.model.User;
import com.zavadski.model.enumeration.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AnnouncementDto {

    private UUID id = UUID.randomUUID();
    private String body;
    private Integer price;
    private Category category;
    private UserDto userDto;
    private LocalDate dateOfCreating;
    private LocalDate dateOfClosing;
    private Status status;

    public Announcement toAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement.setBody(body);
        announcement.setPrice(price);
        announcement.setCategory(category);
        announcement.setUser(userDto.toUser());

        return announcement;
    }

    public static CategoryDto fromAnnouncement(Announcement announcementy) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

}
