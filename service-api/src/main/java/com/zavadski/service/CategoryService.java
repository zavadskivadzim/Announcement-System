package com.zavadski.service;

import com.zavadski.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAllCategories();

    Category createCategory(Category category);

    Category findCategoryById(UUID id);

    Category updateCategory(Category category);

    void deleteCategory(UUID id);

    boolean checkCategoryOnUnique(String categoryName);

}
