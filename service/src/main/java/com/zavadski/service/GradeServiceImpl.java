package com.zavadski.service;

import com.zavadski.dao.api.GradeDao;
import com.zavadski.model.Announcement;
import com.zavadski.model.Grade;
import com.zavadski.model.User;
import com.zavadski.model.dto.CreateAnnouncementDto;
import com.zavadski.model.enumeration.Status;
import com.zavadski.service.api.GradeService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeDao gradeDao;
    private final UserService userService;

    @Autowired
    public GradeServiceImpl(GradeDao gradeDao, UserService userService) {
        this.gradeDao = gradeDao;
        this.userService = userService;
    }


    @Override
    public List<Grade> findAllGrades() {
        return gradeDao.findAll();
    }

    @Override
    public Grade findGradeById(UUID id) {
        return gradeDao.findById(id);
    }

    @Override
    public Grade createGrade(Grade grade) {
        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        grade.setSender(author);
        grade.setReceiver(grade.getReceiver());
        grade.setGrade(grade.getGrade());
        return gradeDao.save(grade);
    }

    @Override
    public Grade updateGrade(Grade grade) throws Exception{

        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(grade.getSender())) {
            grade.setGrade(grade.getGrade());
            return gradeDao.update(grade);
        } else {
            throw new Exception("you can't delete this grade");
        }
    }


    @Override
    public void deleteGrade(UUID id) throws Exception {

        Grade grade = findGradeById(id);
        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(grade.getSender())) {
            gradeDao.delete(id);
        } else {
            throw new Exception("you can't delete this grade");
        }
    }

}
