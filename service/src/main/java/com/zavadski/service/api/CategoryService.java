package com.zavadski.service.api;

import com.zavadski.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category);

    Category findById(UUID id);

    Category update(Category category);

    void delete(UUID id);
}
