package com.zavadski.service;

import com.zavadski.dao.api.CategoryDao;
import com.zavadski.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category findCategoryById(UUID id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryDao.delete(id);
    }

    @Override
    public boolean checkCategoryOnUnique(String categoryName) {
        return categoryDao.checkCategoryOnUnique(categoryName);
    }

}
