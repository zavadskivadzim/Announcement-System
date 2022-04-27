package com.zavadski.service;

import com.zavadski.dao.api.CommentDao;
import com.zavadski.model.Announcement;
import com.zavadski.model.Comment;
import com.zavadski.model.User;
import com.zavadski.model.dto.CreateCommentDto;
import com.zavadski.service.api.AnnouncementService;
import com.zavadski.service.api.CommentService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final UserService userService;
    private final AnnouncementService announcementService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, UserService userService, AnnouncementService announcementService) {
        this.commentDao = commentDao;
        this.userService = userService;
        this.announcementService = announcementService;
    }

    @Override
    public List<Comment> findAllComments() {
        return commentDao.findAll();
    }

    @Override
    public Comment createComment(CreateCommentDto createCommentDto) {
        Comment comment = new Comment();
        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        Announcement announcement = announcementService.findAnnouncementById(createCommentDto.getAnnouncement());
        comment.setUser(author);
        comment.setAnnouncement(announcement);
        comment.setBody(createCommentDto.getBody());
        comment.setDateOfCreating(LocalDateTime.now());
        return commentDao.save(comment);
    }

}
