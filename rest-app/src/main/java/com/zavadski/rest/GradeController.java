package com.zavadski.rest;

import com.zavadski.model.Grade;
import com.zavadski.model.dto.CreateGradeDto;
import com.zavadski.service.api.GradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    private final GradeService gradeService;

    private static final Logger logger = LogManager.getLogger(GradeController.class);

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping(value = "/admin/grades")
    public final List<Grade> findAllGrades() {

        logger.info("find All Grades");

        return gradeService.findAllGrades();
    }

    @PostMapping(path = "/grades")
    @ResponseStatus(HttpStatus.CREATED)
    public final Grade createGrade(@RequestBody CreateGradeDto createGradeDto) {

        logger.info("create Grade ({})", createGradeDto);

        return gradeService.createGrade(createGradeDto);
    }

    @PutMapping(path = "/grades")
    @ResponseStatus(HttpStatus.OK)
    public final Grade updateGrade(@RequestBody CreateGradeDto createGradeDto) {

        logger.info("update Grade ({})", createGradeDto);

        return gradeService.updateGrade(createGradeDto);
    }

}
