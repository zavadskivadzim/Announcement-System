package com.zavadski.service;

import com.zavadski.model.Grade;
import com.zavadski.model.dto.CreateGradeDto;

import java.util.List;
import java.util.UUID;

public interface GradeService {

    List<Grade> findAllGrades();

    Grade findGradeById(UUID id);

    Grade createGrade(CreateGradeDto createGradeDto);

    Grade updateGrade(CreateGradeDto createGradeDto);

}
