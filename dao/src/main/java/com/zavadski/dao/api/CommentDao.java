package com.zavadski.dao.api;

import com.zavadski.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentDao {

    List<Comment> findAll();

    Comment findById(UUID id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(UUID id);

}
