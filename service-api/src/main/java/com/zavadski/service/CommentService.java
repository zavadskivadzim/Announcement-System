package com.zavadski.service;

import com.zavadski.model.Comment;
import com.zavadski.model.dto.CreateCommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> findAllComments();

    Comment findCommentById(UUID id);

    void createComment(CreateCommentDto createCommentDto);

    void updateComment(CreateCommentDto createCommentDto) throws Exception;

    void deleteComment(UUID id) throws Exception;

}
