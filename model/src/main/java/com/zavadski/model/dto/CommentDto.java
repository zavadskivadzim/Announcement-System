package com.zavadski.model.dto;

import com.zavadski.model.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentDto {

    private UUID id;
    private String body;
    private AnnouncementDto announcementDto;
    private UserDto userDto;
    private LocalDateTime dateOfCreating;
    private LocalDateTime dateOfEditing;

    public static CommentDto fromComment(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setAnnouncementDto(AnnouncementDto.fromAnnouncement(comment.getAnnouncement()));
        commentDto.setUserDto(UserDto.fromUser(comment.getUser()));
        commentDto.setDateOfCreating(comment.getDateOfCreating());
        commentDto.setDateOfEditing(comment.getDateOfEditing());
        return commentDto;
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setBody(body);
        comment.setAnnouncement(announcementDto.toAnnouncement());
        return comment;
    }

}
