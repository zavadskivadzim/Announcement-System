package com.zavadski.model.dto;

import com.zavadski.model.Announcement;
import com.zavadski.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateCommentDto {

    private UUID id;
    private String body;
    private UserDto userDto;
    private LocalDateTime dateOfCreating;
    private Announcement announcement;

    public static CreateCommentDto fromComment(Comment comment) {
        CreateCommentDto createCommentDto = new CreateCommentDto();
        createCommentDto.setId(comment.getId());
        createCommentDto.setBody(comment.getBody());
        createCommentDto.setUserDto(UserDto.fromUser(comment.getUser()));
        createCommentDto.setDateOfCreating(comment.getDateOfCreating());
        return createCommentDto;
    }

}
