package com.zavadski.dao.api;

import com.zavadski.model.Grade;

import java.util.List;
import java.util.UUID;

public interface GradeDao {

    List<Grade> findAll();

    Grade findById(UUID id);

    Grade save(Grade grade);

    Grade update(Grade grade);

    void delete(UUID id);

}
