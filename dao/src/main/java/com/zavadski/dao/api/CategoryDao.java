package com.zavadski.dao.api;

import com.zavadski.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryDao {

    List<Category> findAll();

    Category save(Category category);

    Category findById(UUID id);

    Category update(Category category);

    void delete(UUID id);

    boolean checkCategoryOnUnique(String categoryName);

}
