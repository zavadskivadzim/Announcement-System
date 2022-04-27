package com.zavadski.service.api;

import com.zavadski.model.Comment;
import com.zavadski.model.dto.CreateCommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();

    Comment createComment(CreateCommentDto createCommentDto) ;

}
