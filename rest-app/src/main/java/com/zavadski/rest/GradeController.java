package com.zavadski.rest;

import com.zavadski.dao.exception.NoAccess;
import com.zavadski.model.Grade;
import com.zavadski.model.dto.CreateGradeDto;
import com.zavadski.service.CurrentUserService;
import com.zavadski.service.GradeService;
import com.zavadski.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class GradeController {

    private final GradeService gradeService;
    private final UserService userService;

    private static final Logger logger = LogManager.getLogger(GradeController.class);

    @Autowired
    public GradeController(GradeService gradeService, UserService userService) {
        this.gradeService = gradeService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin/grades")
    public final List<Grade> findAllGrades() {

        logger.info("find All Grades");

        return gradeService.findAllGrades();
    }

    @PostMapping(path = "/grades")
    public final ResponseEntity<Grade> createGrade(@RequestBody CreateGradeDto createGradeDto) {

        logger.info("create Grade ({})", createGradeDto);

        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin())).getId()
                .equals(createGradeDto.getReceiverId())) {
            throw new NoAccess("you can't create this grade");
        } else {
            return new ResponseEntity<>(gradeService.createGrade(createGradeDto), HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/grades")
    public final ResponseEntity<Grade> updateGrade(@RequestBody CreateGradeDto createGradeDto) {

        logger.info("update Grade ({})", createGradeDto);

        if (!userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin())).getId()
                .equals(gradeService.findGradeById(createGradeDto.getId()).getSender().getId())) {
            throw new NoAccess("you can't update this grade");
        } else {
            return new ResponseEntity<>(gradeService.updateGrade(createGradeDto), HttpStatus.OK);
        }
    }
}
