package com.zavadski.rest;

import com.zavadski.model.Announcement;
import com.zavadski.model.Comment;
import com.zavadski.model.dto.*;
import com.zavadski.service.api.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    private final CommentService commentService;

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/admin/comments")
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

    @PutMapping(path = "/comments")
    @ResponseStatus(HttpStatus.OK)
    public final void updateComment(@RequestBody CreateCommentDto createCommentDto) throws Exception {

        logger.info("update Comment ({})", createCommentDto);

        commentService.updateComment(createCommentDto);
    }

    @GetMapping(value = "/comments_to_announcement/{id}")
    public final List<CommentDto> findCommentsToAnnouncement(@PathVariable String id) {

        logger.info("find Comments To Announcement by id {}", id);

        return commentService.findAllComments()
                .stream()
                .filter(comment -> comment.getAnnouncement().getId()
                        .equals(UUID.fromString(id)))
                .sorted(Comparator.comparing(Comment::getDateOfCreating))
                .map(CommentDto::fromComment).collect(Collectors.toList());
    }
    @DeleteMapping(value = "/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable UUID id) throws Exception {

        logger.info("delete Comment by id={}", id);

        commentService.deleteComment(id);
    }
}
