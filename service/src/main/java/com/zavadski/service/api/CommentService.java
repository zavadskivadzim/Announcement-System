package com.zavadski.service.api;

import com.zavadski.model.Comment;
import com.zavadski.model.dto.CreateCommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> findAllComments();

    Comment findCommentById(UUID id);

    Comment createComment(CreateCommentDto createCommentDto);

    Comment updateComment(CreateCommentDto createCommentDto) throws Exception;

    void deleteComment(UUID id) throws Exception;

}
