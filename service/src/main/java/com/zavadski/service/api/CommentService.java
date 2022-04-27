package com.zavadski.service.api;

import com.zavadski.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> findAllComments();

//    Comment findCommentById(UUID id);
//
//    Comment createComment(Comment comment);
//
//    Comment updateComment(Comment comment);
//
//    void deleteComment(UUID id);

}
