package com.zavadski.rest;

import com.zavadski.model.dto.*;
import com.zavadski.service.api.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    private final CommentService commentService;

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/comments")
    public final List<CommentDto> findAllComments() {

        logger.info("find All Comments");

        return commentService.findAllComments()
                .stream().map(CommentDto::fromComment).collect(Collectors.toList());
    }

    @PostMapping(path = "/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createComment(@RequestBody CreateCommentDto createCommentDto) {

        logger.info("create Comment ({})", createCommentDto);

        commentService.createComment(createCommentDto);
    }

}
