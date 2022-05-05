package com.zavadski.service.api;

import com.zavadski.model.Grade;

import java.util.List;
import java.util.UUID;

public interface GradeService {

    List<Grade> findAllGrades();

    Grade findGradeById(UUID id);

    Grade createGrade(Grade grade);

    Grade updateGrade(Grade grade) throws Exception;

    void deleteGrade(UUID id) throws Exception;

}
