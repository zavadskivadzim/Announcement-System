package com.zavadski.service;

import com.zavadski.dao.api.GradeDao;
import com.zavadski.model.Grade;
import com.zavadski.model.User;
import com.zavadski.model.dto.CreateGradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Grade createGrade(CreateGradeDto createGradeDto) {

        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));

        Grade grade = new Grade();
        grade.setSender(author);
        grade.setGrade(createGradeDto.getGrade());
        grade.setReceiver(userService.findUserById(createGradeDto.getReceiverId()));
        return gradeDao.save(grade);
    }

    @Override
    public Grade updateGrade(CreateGradeDto createGradeDto) {

        Grade grade = findGradeById(createGradeDto.getId());
        grade.setGrade(createGradeDto.getGrade());
        return gradeDao.update(grade);
    }

}
