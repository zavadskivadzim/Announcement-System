package com.zavadski.service;

import com.zavadski.dao.api.CommentDao;
import com.zavadski.model.Comment;
import com.zavadski.model.User;
import com.zavadski.service.api.CommentService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, UserService userService) {
        this.commentDao = commentDao;
        this.userService = userService;
    }


    @Override
    public List<Comment> findAllComments() {
        return commentDao.findAll();
    }

//
//    @Override
//    public Comment findCommentById(UUID id) {
//        return commentDao.findById(id);
//    }
//
//    @Override
//    public Comment createComment(CommentDao commentDao) {
//        Comment comment = new Comment();
//        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
//        comment.setUser(author);
//        announcement.setBody(createAnnouncementDto.getBody());
//        announcement.setPrice(createAnnouncementDto.getPrice());
//        announcement.setDateOfCreating(LocalDateTime.now());
//        announcement.setStatus(Status.ACTIVE);
//        announcement.setCategory(categoryService.findCategoryById(createAnnouncementDto.getCategory()));
//        return announcementDao.save(announcement);
//    }
//
//    @Override
//    public Announcement updateAnnouncement(CreateAnnouncementDto createAnnouncementDto) throws Exception {
//
//        Announcement announcement = findAnnouncementById(createAnnouncementDto.getId());
//
//        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
//                .equals(announcement.getUser())) {
//            announcement.setBody(createAnnouncementDto.getBody());
//            announcement.setPrice(createAnnouncementDto.getPrice());
//            announcement.setCategory(categoryService.findCategoryById(createAnnouncementDto.getCategory()));
//            return announcementDao.update(announcement);
//        } else {
//            throw new Exception("you can't update this announcement");
//        }
//    }
//
//    @Override
//    public void deleteAnnouncement(UUID id) throws Exception {
//
//        Announcement announcement = findAnnouncementById(id);
//        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
//                .equals(announcement.getUser())) {
//            announcementDao.delete(id);
//        } else {
//            throw new Exception("you can't delete this announcement");
//        }
//    }

}
